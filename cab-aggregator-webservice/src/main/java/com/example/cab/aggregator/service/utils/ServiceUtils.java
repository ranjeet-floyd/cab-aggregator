package com.example.cab.aggregator.service.utils;

import com.example.cab.aggregator.service.bean.PositionBean;
import java.util.Random;

/**
 *
 * @author ranjeet
 */
public class ServiceUtils {

    private ServiceUtils() {
    }

    public static final ServiceUtils SERVICE_UTILS = new ServiceUtils();

    public int getRandom(int bound) {
        Random r = new Random();
        int low = 1;
        int high = bound;
        return r.nextInt(high - low) + low;
    }

    public int getMahattanDistance(PositionBean currPos, PositionBean nextPos) {

        return (Math.abs(currPos.getX() - nextPos.getX()) + Math.abs(currPos.getY() - nextPos.getY()));
    }

    public boolean isRiderWithinDistance(PositionBean pos1, PositionBean pos2, int M) {

        return this.getMahattanDistance(pos1, pos2) <= M;

    }

    /*
    Move cab in random direction from current pos
     */
    public PositionBean randomDirection(int g) {
        int randX = this.getRandom(g);
        int randY = this.getRandom(g);
        return new PositionBean(randX, randY);
    }

}
