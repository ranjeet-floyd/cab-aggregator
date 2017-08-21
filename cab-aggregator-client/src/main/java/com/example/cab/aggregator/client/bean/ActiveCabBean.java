package com.example.cab.aggregator.client.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Active Cab
 *
 * @author ranjeet
 */
public class ActiveCabBean {

    private PositionBean position;
    private List<RiderBean> riderBeans;
    private int id;
    private CabType cabType;

    public ActiveCabBean() {
    }

    public ActiveCabBean(int id, CabType cabType, PositionBean position) {
        this.id = id;
        this.cabType = cabType;
        this.position = position;
        this.riderBeans = riderBeans = new ArrayList<>();
    }

    public void setRiderBeans(List<RiderBean> riderBeans) {
        this.riderBeans = riderBeans;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCabType(CabType cabType) {
        this.cabType = cabType;
    }

    public int getId() {
        return id;
    }

    public CabType getCabType() {
        return cabType;
    }

    public PositionBean getPosition() {
        return position;
    }

    public void setPosition(PositionBean position) {
        this.position = position;
    }

    public List<RiderBean> getRiderBeans() {
        return riderBeans;
    }

    public void addRider(RiderBean riderBean) {
        this.riderBeans.add(riderBean);
    }

    @Override
    public String toString() {
        return "ActiveCabBean{" + "position=" + position + ", riderBeans=" + riderBeans + ", id=" + id + ", cabType=" + cabType + '}';
    }

}
