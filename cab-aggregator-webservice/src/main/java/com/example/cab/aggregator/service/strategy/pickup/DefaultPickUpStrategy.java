package com.example.cab.aggregator.service.strategy.pickup;

import com.example.cab.aggregator.service.bean.PositionBean;
import static com.example.cab.aggregator.service.utils.ServiceUtils.SERVICE_UTILS;

/**
 *
 * @author ranjeet
 */
public class DefaultPickUpStrategy implements PickUpStrategy {

    public DefaultPickUpStrategy() {
    }

    /**
     * Pick rider if within manhattan distance M from cab position.
     *
     * @param cabPosition
     * @param riderPosition
     * @return true if within manhanttan distance M
     */
    @Override
    public boolean isPickable(PositionBean cabPosition, PositionBean riderPosition, int M) {
        boolean isPickable = SERVICE_UTILS.isRiderWithinDistance(cabPosition, riderPosition, M);
        return isPickable;

    }

}
