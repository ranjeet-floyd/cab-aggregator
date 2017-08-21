package com.example.cab.aggregator.client.bean;

/**
 *
 * @author ranjeet
 */
public class PickUpResourceBean {

    private final int cabId;

    public PickUpResourceBean(int cabId) {
        this.cabId = cabId;
    }

    public int getCabId() {
        return cabId;
    }

    @Override
    public String toString() {
        return "PickUpResourceBean{" + "cabId=" + cabId + '}';
    }


}
