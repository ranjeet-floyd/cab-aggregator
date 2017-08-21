package com.example.cab.aggregator.service.bean;

/**
 *
 * @author ranjeet
 */
public class RiderBean {

    private PositionBean distPosition; //distination postion
    private PositionBean currPosition; //current postion
    private boolean available = true;
    private final int id;

    public RiderBean(int id, PositionBean curr, PositionBean dist) {
        this.id = id;
        this.distPosition = dist;
        this.currPosition = curr;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public PositionBean getCurrPosition() {
        return currPosition;
    }

    public void setCurrPosition(PositionBean currPosition) {
        this.currPosition = currPosition;
    }

    public PositionBean getDistPosition() {
        return distPosition;
    }

    public void setDistPosition(PositionBean distPosition) {
        this.distPosition = distPosition;
    }

    @Override
    public String toString() {
        return "RiderBean{" + "distPosition=" + distPosition + ", currPosition=" + currPosition + ", available=" + available + '}';
    }

}
