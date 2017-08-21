package com.example.cab.aggregator.client.bean;

/**
 * Drop users for cab id
 *
 * @author ranjeet
 */
public class DropBeanResource {

    private final int cabId;

    public DropBeanResource(int cabId) {
        this.cabId = cabId;
    }

    public int getCabId() {
        return cabId;
    }

    @Override
    public String toString() {
        return "DropBeanResource{" + "cabId=" + cabId + '}';
    }

}
