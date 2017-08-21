package com.example.cab.aggregator.service.bean;

/**
 * Drop users for cab id
 *
 * @author ranjeet
 */
public class DropBeanResource {

    private int cabId;

    public DropBeanResource(int cabId) {
        this.cabId = cabId;
    }

    public DropBeanResource() {
    }

    public void setCabId(int cabId) {
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
