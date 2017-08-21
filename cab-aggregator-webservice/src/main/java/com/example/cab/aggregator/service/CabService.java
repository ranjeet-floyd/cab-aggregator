package com.example.cab.aggregator.service;

import com.example.cab.aggregator.service.bean.*;
import com.example.cab.aggregator.service.config.ServiceConfiguration;
import com.example.cab.aggregator.service.entity.Cab;
import com.example.cab.aggregator.service.exception.*;
import com.example.cab.aggregator.service.factory.CabFactory;
import com.example.cab.aggregator.service.repository.*;
import com.example.cab.aggregator.service.utils.MoveHelperUtils;
import static com.example.cab.aggregator.service.utils.ServiceUtils.SERVICE_UTILS;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cab related service
 * <P>
 * 1. Picks up riders which are within a manhattan distance M
 * <P>
 * 2. If no riders within M, the cab will meander in a random direction
 *
 * @author ranjeet
 */
public class CabService {

    private static final MoveHelperUtils MOVE_HELPER = new MoveHelperUtils();
    private final Repository<RiderBean> riderRepository;
    private final Repository<ActiveCabBean> cabRepository;
    private final ServiceConfiguration config;
    private static final Logger LOGGER = LoggerFactory.getLogger(CabService.class);

    public CabService(final ServiceConfiguration config) {
        this.config = config;
        this.cabRepository = CabRepositoryImp.getInstance();
        riderRepository = RiderRepository.getInstance();
    }

    /**
     * create randomly type of cab with id (0 - number of cabs) with position
     * (0,0).
     */
    public void createRandomCabs() {
        int numOfsoloCabs = SERVICE_UTILS.getRandom(config.getNumOfCabs());
        LOGGER.info("Create random Cabs :" + config.getNumOfCabs());
        int id = 1;
        for (int i = 0; i < numOfsoloCabs; i++) {
            cabRepository.create(new ActiveCabBean(id, CabType.SOLO, new PositionBean(SERVICE_UTILS.getRandom(config.getNumOfCabs()),
                    SERVICE_UTILS.getRandom(config.getNumOfCabs()))));
            id += 1;
        }

        int numOfGroupCabs = config.getNumOfCabs() - numOfsoloCabs;
        for (int i = 0; i < numOfGroupCabs; i++) {
            cabRepository.create(new ActiveCabBean(id, CabType.GROUP, new PositionBean(SERVICE_UTILS.getRandom(config.getNumOfCabs()),
                    SERVICE_UTILS.getRandom(config.getNumOfCabs()))));
            id += 1;
        }

    }

    public Collection<ActiveCabBean> getAllActiveCabs() {
        return cabRepository.getAll();
    }

    public boolean isCabAvailable(int cabId) throws NoCabExistException {
        ActiveCabBean cabBean = cabRepository.get(cabId);
        if (Objects.isNull(cabBean)) {
            throw new NoCabExistException("No Cab exist :" + cabId);
        }

        Cab cab = CabFactory.getCab(cabBean);
        return cab.isAvailable();
    }

    public Optional<RiderBean> pick(int cabId) throws CabNotAvailableException, InterruptedException, NoCabExistException {
        ActiveCabBean cabBean = cabRepository.get(cabId);
        if (Objects.nonNull(cabBean)) {
            Cab cab = CabFactory.getCab(cabBean);
            if (cab.isAvailable()) {
                int manhattanDistance = config.getM();
                //if cab type is group and more than 0 zero rider is there..then check manhattan distance for 2nd rider. 
                if (cab.getCab().getCabType().equals(CabType.GROUP) && cab.getRider().size() > 0) {
                    manhattanDistance = config.getN();
                }

                final int finalManhattanDistance = manhattanDistance;

                //check available riders within manhattan distance M
                Optional<RiderBean> pickableRider = riderRepository.getAll().stream()
                        .filter(rider -> rider.isAvailable())
                        .filter(rider -> cab.isPickable(rider.getCurrPosition(), finalManhattanDistance))
                        .findFirst();
                if (pickableRider.isPresent()) {
                    RiderBean rider = pickableRider.get();
                    rider.setAvailable(false);
                    cab.addRider(rider);

                    // move every 1000 ms == 1 sec to rider current position
                    MOVE_HELPER.run(cab, rider.getCurrPosition());
                    return Optional.of(rider);

                } else {

                    PositionBean newPos = cab.randomMove(config.getGridSize());
                    LOGGER.info("Move random to find rider: New Position:" + newPos);
                    return Optional.empty();
                }
            } else {
                String msg = "Cab Id : [" + cabId + " ] is not available";
                LOGGER.error("CabNotAvailableException :" + msg);
                throw new CabNotAvailableException(msg);
            }

        } else {
            String msg = "No cab exit for id :" + cabId;
            LOGGER.error("NoCabExistException :" + msg);
            throw new NoCabExistException(msg);
        }
    }

    public List<RiderBean> drop(int cabId) throws NoCabExistException, CabEmptyExeption, InterruptedException {
        LOGGER.info("Drop rider from Cab :" + cabId);
        ActiveCabBean cabBean = cabRepository.get(cabId);
        if (Objects.nonNull(cabBean)) {
            Cab cab = CabFactory.getCab(cabBean);
            List<RiderBean> riders = cab.getRider();

            if (riders.isEmpty()) {
                throw new CabEmptyExeption("Cab :" + cabBean + " is empty.");
            }

            List<RiderBean> dropRiders = new ArrayList<>(riders.size());
            for (RiderBean rider : riders) {
                // move every 1000 ms == 1 sec to rider dist position
                MOVE_HELPER.run(cab, rider.getDistPosition());
                rider.setAvailable(true);
                LOGGER.info("Drop Rider : " + rider.toString() + " Total Cost:" + cab.cost());
                dropRiders.add(rider);
            }
            cab.getRider().clear();//again available for rider
            return dropRiders;
        }
        String msg = "No cab available for id :" + cabId;
        LOGGER.error("NoCabExistException :" + msg);
        throw new NoCabExistException(msg);
    }

}
