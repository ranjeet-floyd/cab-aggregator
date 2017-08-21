package com.example.cab.aggregator.service.factory;

import com.example.cab.aggregator.service.bean.ActiveCabBean;
import static com.example.cab.aggregator.service.bean.CabType.GROUP;
import static com.example.cab.aggregator.service.bean.CabType.SOLO;
import com.example.cab.aggregator.service.entity.Cab;
import com.example.cab.aggregator.service.entity.GroupCab;
import com.example.cab.aggregator.service.entity.SoloCab;

/**
 * Create Solo, group cab
 *
 * @author ranjeet
 */
public class CabFactory {

    private CabFactory() {
    }

    /**
     * Get either solo or group cab, Default is solo.
     *
     * @param bean
     * @return Cab based on cab type.
     */
    public static Cab getCab(ActiveCabBean bean) {
        Cab cab;
        switch (bean.getCabType()) {
            case SOLO:
                cab = new SoloCab(bean);
                break;
            case GROUP:
                cab = new GroupCab(bean);
                break;
            default:
                cab = new SoloCab(bean);
                break;

        }

        return cab;

    }

}
