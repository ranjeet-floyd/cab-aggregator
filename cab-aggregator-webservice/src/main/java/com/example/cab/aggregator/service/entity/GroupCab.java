package com.example.cab.aggregator.service.entity;

import com.example.cab.aggregator.service.bean.ActiveCabBean;
import com.example.cab.aggregator.service.strategy.cost.GroupCabCost;
import com.example.cab.aggregator.service.strategy.move.DefaultMoveStrategy;
import com.example.cab.aggregator.service.strategy.pickup.DefaultPickUpStrategy;

/**
 *
 * @author ranjeet
 */
public class GroupCab extends Cab {
//    private final int id;

    private static final int MAX_RIDERS = 3;
    private final ActiveCabBean cabBean;
    
    public GroupCab(final ActiveCabBean cabBean) {
        super(new GroupCabCost(), new DefaultMoveStrategy(), new DefaultPickUpStrategy());
        this.cabBean = cabBean;
    }
    
    @Override
    public boolean isAvailable() {
        return this.getRider().size() <= MAX_RIDERS;
    }
    
    @Override
    public ActiveCabBean getCab() {
        return cabBean;
    }
    
}
