package com.example.cab.aggregator.client;

import com.example.cab.aggregator.client.bean.ActiveCabBean;
import com.example.cab.aggregator.client.bean.DropBeanResource;
import com.example.cab.aggregator.client.bean.PickUpResourceBean;
import com.example.cab.aggregator.client.bean.RiderBean;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author ranjeet
 */
public class Test {

    private static final String BASE_URL = "http:localhost:8080/";

    public static void main(String[] args) throws IOException {
        CabServiceImp cabServiceImp = new CabServiceImp(BASE_URL);
        Collection<ActiveCabBean> activeCabs = cabServiceImp.getCabs();
        final int maxNumberOfTry = 3;//max try for pickup calls
        for (ActiveCabBean activeCab : activeCabs) {
            System.out.println("Pick up rider for Cab :" + activeCab);
            PickUpResourceBean pickUpResourceBean = new PickUpResourceBean(activeCab.getId());
            //while cab is available for pick, ask it.
            int tryCount = 0;
            while (tryCount < maxNumberOfTry && cabServiceImp.isAvailable(activeCab.getId())) {
                tryCount++;
                Optional<RiderBean> riderOptional = cabServiceImp.pickRider(pickUpResourceBean);
                if (Objects.nonNull(riderOptional)) {
                    if (riderOptional.isPresent()) {
                        System.out.println("Pick :" + riderOptional.get().toString());
                    } else {
                        System.out.println("No Rider available... Move cab randomly");
                    }
                } else {
                    throw new RuntimeException("Something is wrong:" + activeCab);
                }

            }
            //drop picked user
            List<RiderBean> riders = cabServiceImp.drop(new DropBeanResource(activeCab.getId()));
            System.out.println("Droped Rides.... Total:" + riders.size());
            System.out.println("\n\n ...Next Cab");
//            riders.forEach(System.out::println);
        }

        System.out.println("successful....");
    }

}
