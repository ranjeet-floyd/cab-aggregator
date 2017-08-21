package com.example.cab.aggregator.service.strategy.cost;

/**
 *
 * @author ranjeet
 */
public class GroupCabCost implements CostStrategy {

    /*
    Cost is 1$ per ride
     */
    @Override
    public double cost() {
        return 1.0;
    }

}
