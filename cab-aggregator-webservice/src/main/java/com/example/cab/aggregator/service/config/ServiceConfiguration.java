package com.example.cab.aggregator.service.config;

import io.dropwizard.Configuration;

/**
 * Server config
 *
 * @author ranjeet
 */
public class ServiceConfiguration extends Configuration {

    private int gridSize;
    private int m; //manhattan distance
    private int n; // manhattan distance for 2nd rider
    private int numOfCabs;

    public ServiceConfiguration(int gridSize, int m, int n, int numOfCabs) {
        this.gridSize = gridSize;
        this.m = m;
        this.n = n;
        this.numOfCabs = numOfCabs;
    }

    public ServiceConfiguration() {
    }

    public int getGridSize() {
        return gridSize;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    /**
     * manhattan distance for cab
     *
     * @return
     */
    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    /**
     * manhattan distance for 2nd rider for cab
     *
     * @return
     */
    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getNumOfCabs() {
        return numOfCabs;
    }

    public void setNumOfCabs(int numOfCabs) {
        this.numOfCabs = numOfCabs;
    }

    @Override
    public String toString() {
        return "ServiceConfiguration {" + "gridSize=" + gridSize + ", m=" + m + ", n=" + n + ", numOfCabs=" + numOfCabs + '}';
    }


}
