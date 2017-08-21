package com.example.cab.aggregator.service.strategy.pickup;

import com.example.cab.aggregator.service.bean.PositionBean;

/**
 *
 * @author ranjeet
 */
public interface PickUpStrategy {

    boolean isPickable(PositionBean cabPosition, PositionBean riderPosition, int M);

}
