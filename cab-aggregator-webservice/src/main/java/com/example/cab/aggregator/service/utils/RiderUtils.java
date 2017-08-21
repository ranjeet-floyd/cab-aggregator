package com.example.cab.aggregator.service.utils;

import com.example.cab.aggregator.service.bean.PositionBean;
import com.example.cab.aggregator.service.bean.RiderBean;
import com.example.cab.aggregator.service.repository.Repository;
import com.example.cab.aggregator.service.repository.RiderRepository;
import static com.example.cab.aggregator.service.utils.ServiceUtils.SERVICE_UTILS;

/**
 * create random riders
 *
 * @author ranjeet
 */
public class RiderUtils {

    /**
     * Add some rider in grid with random curr position and dis position.
     *
     * @param n : number of riders
     */
    public void createSomeRiders(int n) {
        Repository<RiderBean> repository = RiderRepository.getInstance();
        for (int i = 0; i < n; i++) {
            repository.create(new RiderBean(i, new PositionBean(SERVICE_UTILS.getRandom(n), SERVICE_UTILS.getRandom(n)),
                    new PositionBean(SERVICE_UTILS.getRandom(n), SERVICE_UTILS.getRandom(n))));
        }
    }

}
