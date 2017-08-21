package com.example.cab.aggregator.service.entity;

import com.example.cab.aggregator.service.bean.ActiveCabBean;
import com.example.cab.aggregator.service.bean.PositionBean;
import com.example.cab.aggregator.service.bean.RiderBean;
import com.example.cab.aggregator.service.exception.CabOverflowException;
import com.example.cab.aggregator.service.repository.CabRepositoryImp;
import com.example.cab.aggregator.service.repository.Repository;
import com.example.cab.aggregator.service.strategy.cost.CostStrategy;
import com.example.cab.aggregator.service.strategy.move.MoveStrategy;
import com.example.cab.aggregator.service.strategy.pickup.PickUpStrategy;
import static com.example.cab.aggregator.service.utils.ServiceUtils.SERVICE_UTILS;
import java.util.List;

/**
 *
 * @author ranjeet
 */
public abstract class Cab {

    private CostStrategy costStrategy;
    private MoveStrategy moveStrategy;
    private PickUpStrategy pickStrategy;
    protected final Repository<ActiveCabBean> cabRepository;

    public Cab(CostStrategy costBehaviour, MoveStrategy moveBehaviour, PickUpStrategy pickBehaviour) {
        this.costStrategy = costBehaviour;
        this.moveStrategy = moveBehaviour;
        this.pickStrategy = pickBehaviour;
        this.cabRepository = CabRepositoryImp.getInstance();
    }

    public abstract boolean isAvailable();

    public abstract ActiveCabBean getCab();

    public void addRider(RiderBean rider) {
        if (isAvailable()) {
            this.getCab().addRider(rider);
            this.cabRepository.update(this.getCab().getId(), this.getCab());

        } else {
            throw new CabOverflowException("Cab :" + this.getCab().toString() + " is full.");
        }
    }

    public List<RiderBean> getRider() {
        return this.getCab().getRiderBeans();
    }


    public boolean isPickable(PositionBean riderPosition, int M) {
        return pickStrategy.isPickable(this.getCab().getPosition(), riderPosition, M);
    }

    /**
     * Move if cab current position is not equal to dis position.
     *
     * @param dst : where we want to go
     * @return true if move else false
     */
    public boolean move(PositionBean dst) {
        PositionBean cabPosition = this.getCab().getPosition();
        if (!cabPosition.equals(dst)) {
            cabPosition = moveStrategy.move(cabPosition, dst);
            this.getCab().setPosition(cabPosition);
            this.cabRepository.update(this.getCab().getId(), this.getCab());
            return false;
        }
        return true;
    }

    public double cost() {
//        int totalRider = this.getCab().getRiderBeans().size();
        return costStrategy.cost();
    }

    public CostStrategy getCostStrategy() {
        return costStrategy;
    }

    public void setCostStrategy(CostStrategy costStrategy) {
        this.costStrategy = costStrategy;
    }

    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }


    public Repository<ActiveCabBean> getRepository() {
        return cabRepository;
    }

    /**
     * if there are no riders within a manhattan distance of M, the Cab will
     * randomMove in a random direction
     *
     * @return : Next Random PositionBean
     */
    public PositionBean randomMove(int gridSize) {
        PositionBean randomPos = SERVICE_UTILS.randomDirection(gridSize);
        this.getCab().setPosition(randomPos);
        this.cabRepository.update(this.getCab().getId(), this.getCab());
        System.out.println("Moving randomly to :" + randomPos);
        return randomPos;

    }

}
