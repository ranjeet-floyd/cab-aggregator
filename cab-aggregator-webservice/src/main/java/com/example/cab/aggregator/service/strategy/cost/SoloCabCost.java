package com.example.cab.aggregator.service.strategy.cost;

/**
 *
 * @author ranjeet
 */
public class SoloCabCost implements CostStrategy {

    /*
    Cost is $ 1.50 
     */
    @Override
    public double cost() {
        return 1.5;
    }

}
