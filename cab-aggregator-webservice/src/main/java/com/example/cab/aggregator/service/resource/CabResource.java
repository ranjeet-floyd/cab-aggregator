package com.example.cab.aggregator.service.resource;

import com.codahale.metrics.annotation.Timed;
import com.example.cab.aggregator.service.CabService;
import com.example.cab.aggregator.service.bean.ActiveCabBean;
import com.example.cab.aggregator.service.bean.DropBeanResource;
import com.example.cab.aggregator.service.bean.PickUpResourceBean;
import com.example.cab.aggregator.service.bean.RiderBean;
import com.example.cab.aggregator.service.config.ServiceConfiguration;
import com.example.cab.aggregator.service.exception.CabEmptyExeption;
import com.example.cab.aggregator.service.exception.CabNotAvailableException;
import com.example.cab.aggregator.service.exception.NoCabExistException;
import com.example.cab.aggregator.service.utils.RiderUtils;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST apis for Cab
 *
 * @author ranjeet
 */
@Path("/cab")
@Produces(MediaType.APPLICATION_JSON)
public class CabResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CabResource.class);
    private final CabService cabService;

    public CabResource(final ServiceConfiguration configuration) {
        cabService = new CabService(configuration);
        //create sample riders
        new RiderUtils().createSomeRiders(configuration.getNumOfCabs());
        cabService.createRandomCabs();
        LOGGER.info("Init service :" + configuration.toString());

    }

    @GET
    @Timed
    public Collection<ActiveCabBean> getCabs() {
        return cabService.getAllActiveCabs();

    }

    @GET
    @Path("/{cabId}")
    public boolean isAvailable(@PathParam("cabId") int cabId) throws NoCabExistException {
        boolean isAvailable = cabService.isCabAvailable(cabId);
        LOGGER.info("isAvailable cabId:" + cabId + " isAvailable :" + isAvailable);
        return isAvailable;
    }

    @POST
    @Path("/pick")
    @Timed
    public RiderBean pickRider(PickUpResourceBean pickUpResourceBean) throws NoCabExistException, InterruptedException, CabNotAvailableException {
        LOGGER.info("PickUpResourceBean :" + pickUpResourceBean);
        Optional<RiderBean> riderOp = cabService.pick(pickUpResourceBean.getCabId());
        if (riderOp.isPresent()) {
            return riderOp.get();
        }
        return null;

    }

    @POST
    @Path("/drop")
    public List<RiderBean> drop(DropBeanResource dropBeanResource) throws NoCabExistException, CabEmptyExeption, InterruptedException {
        LOGGER.info("DropBeanResource :" + dropBeanResource);
        return cabService.drop(dropBeanResource.getCabId());
    }

}
