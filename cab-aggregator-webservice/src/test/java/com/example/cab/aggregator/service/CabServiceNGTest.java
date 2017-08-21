package com.example.cab.aggregator.service;

import com.example.cab.aggregator.service.bean.ActiveCabBean;
import com.example.cab.aggregator.service.bean.RiderBean;
import com.example.cab.aggregator.service.config.ServiceConfiguration;
import com.example.cab.aggregator.service.exception.CabEmptyExeption;
import com.example.cab.aggregator.service.exception.CabNotAvailableException;
import com.example.cab.aggregator.service.exception.NoCabExistException;
import com.example.cab.aggregator.service.utils.RiderUtils;
import static com.example.cab.aggregator.service.utils.ServiceUtils.SERVICE_UTILS;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

/**
 *
 * @author ranjeet
 */
public class CabServiceNGTest {

    private static final ServiceConfiguration CONFIGURATION = new ServiceConfiguration(5, 2, 1, 5);
    private final CabService instance;

    public CabServiceNGTest() {
        instance = new CabService(CONFIGURATION);
        //test users
        new RiderUtils().createSomeRiders(CONFIGURATION.getNumOfCabs());
    }

    /**
     * Test of createRandomCabs method, of class CabService.
     */
    @Test(priority = 0)
    public void testCreateRandomCabs() {
        System.out.println("com.example.cab.aggregator.service.CabServiceNGTest.testCreateRandomCabs()");
        instance.createRandomCabs();
    }

    /**
     * Test of getAllActiveCabs method, of class CabService.
     */
    @Test(priority = 1)
    public void testGetAllActiveCabs() {
        System.out.println("com.example.cab.aggregator.service.CabServiceNGTest.testGetAllActiveCabs()");
        Collection<ActiveCabBean> result = instance.getAllActiveCabs();
        assertEquals(CONFIGURATION.getNumOfCabs(), result.size());
    }

    /**
     * Test of isCabAvailable method, of class CabService.
     */
    @Test(expectedExceptions = NoCabExistException.class, priority = 1)
    public void testIsCabAvailable_NoCabExistException() throws Exception {
        System.out.println("com.example.cab.aggregator.service.CabServiceNGTest.testIsCabAvailable_NoCabExistException()");
        int cabId = -1;
        boolean expResult = false;
        boolean result = instance.isCabAvailable(cabId);
    }

    @Test(priority = 1)
    public void testIsCabAvailable() throws Exception {
        System.out.println("com.example.cab.aggregator.service.CabServiceNGTest.testIsCabAvailable()");
        int cabId = SERVICE_UTILS.getRandom(CONFIGURATION.getNumOfCabs());;
        boolean expResult = true;
        boolean result = instance.isCabAvailable(cabId);
        assertEquals(expResult, result);
    }

    /**
     * Test of pick method, of class CabService.
     */
    @Test(expectedExceptions = CabNotAvailableException.class, priority = 1)
    public void testPick_CabNotAvailableException() throws Exception {
        System.out.println("com.example.cab.aggregator.service.CabServiceNGTest.testPick_CabNotAvailableException()");
        int cabId = SERVICE_UTILS.getRandom(CONFIGURATION.getNumOfCabs());
        //pick rider till full
        Optional<RiderBean> riderOp = instance.pick(cabId);
        int maxTry = CONFIGURATION.getNumOfCabs();
        int count = 0;
        while (count < maxTry && instance.isCabAvailable(cabId)) {
            riderOp = instance.pick(cabId);
            count++;
        }
        if (!instance.isCabAvailable(cabId)) {
            Optional result = instance.pick(cabId);//no cab 
        }
    }

    @Test(expectedExceptions = NoCabExistException.class, priority = 1)
    public void testPick_NoCabExistException() throws Exception {
        System.out.println("com.example.cab.aggregator.service.CabServiceNGTest.testPick_NoCabExistException()");
        int cabId = -1;//no cab exist
        Optional expResult = null;
        Optional result = instance.pick(cabId);
    }

    @Test(priority = 1)
    public void testPick() throws Exception {
        System.out.println("com.example.cab.aggregator.service.CabServiceNGTest.testPick()");
        int cabId = 1;//cab exist
        Optional<RiderBean> result = instance.pick(cabId);
        Assert.assertNotNull(result);
    }

    /**
     * Test of drop method, of class CabService.
     */
    @Test(priority = 1)
    public void testDrop() throws Exception {
        System.out.println("com.example.cab.aggregator.service.CabServiceNGTest.testDrop()");
        int cabId = SERVICE_UTILS.getRandom(CONFIGURATION.getNumOfCabs());
        //pick 1 rider
        Optional<RiderBean> riderOp = instance.pick(cabId);
        int maxTry = CONFIGURATION.getNumOfCabs();
        int count = 0;
        while (count < maxTry && !riderOp.isPresent()) {
            riderOp = instance.pick(cabId);
            count++;
        }
        if (riderOp.isPresent()) {
            List<RiderBean> result = instance.drop(cabId);
            assertEquals(1, result.size());////picked 1 rider
        }
    }

    @Test(priority = 1, expectedExceptions = NoCabExistException.class)
    public void testDrop_NoCabExistException() throws Exception {
        System.out.println("com.example.cab.aggregator.service.CabServiceNGTest.testDrop_NoCabExistException()");
        int cabId = -1;
        List<RiderBean> result = instance.drop(cabId);
    }

    @Test(priority = 1, expectedExceptions = CabEmptyExeption.class)
    public void testDrop_CabEmptyExeption() throws Exception {
        System.out.println("com.example.cab.aggregator.service.CabServiceNGTest.testDrop_NoCabExistException()");
        int cabId = SERVICE_UTILS.getRandom(CONFIGURATION.getNumOfCabs());
        instance.drop(cabId);
        instance.drop(cabId);//cab is empty
    }

}
