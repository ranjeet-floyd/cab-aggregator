package com.example.cab.aggregator.client.bean;

import java.io.Serializable;

/**
 *
 * @author ranjeet
 */
public class PositionBean implements Serializable {

    private static final long serialVersionUID = 752033656287983421L;

    private int x;
    private int y;

    public PositionBean() {
    }

    public PositionBean(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.x;
        hash = 59 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PositionBean other = (PositionBean) obj;
        if (this.x != other.x) {
            return false;
        }
        return this.y == other.y;
    }


    @Override
    public String toString() {
        return "Position{" + "x=" + x + ", y=" + y + '}';
    }

}
