package com.example.cab.aggregator.service.bean;

/**
 *
 * @author ranjeet
 */
public class PickUpResourceBean {

    private int cabId;

    public PickUpResourceBean(int cabId) {
        this.cabId = cabId;
    }

    public PickUpResourceBean() {
    }

    public int getCabId() {
        return cabId;
    }

    public void setCabId(int cabId) {
        this.cabId = cabId;
    }


    @Override
    public String toString() {
        return "PickUpResourceBean{" + "cabId=" + cabId + '}';
    }


}
