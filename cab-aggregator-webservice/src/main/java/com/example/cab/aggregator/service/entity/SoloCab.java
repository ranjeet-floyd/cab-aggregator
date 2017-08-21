package com.example.cab.aggregator.service.entity;

import com.example.cab.aggregator.service.bean.ActiveCabBean;
import com.example.cab.aggregator.service.strategy.cost.SoloCabCost;
import com.example.cab.aggregator.service.strategy.move.DefaultMoveStrategy;
import com.example.cab.aggregator.service.strategy.pickup.DefaultPickUpStrategy;

/**
 *
 * @author ranjeet
 */
public class SoloCab extends Cab {

    private final ActiveCabBean cabBean;

    public SoloCab(final ActiveCabBean cabBean) {
        super(new SoloCabCost(), new DefaultMoveStrategy(), new DefaultPickUpStrategy());
        this.cabBean = cabBean;
    }

    @Override
    public boolean isAvailable() {
        return this.getRider().isEmpty();
    }

    @Override
    public ActiveCabBean getCab() {
        return cabBean;
    }

}
