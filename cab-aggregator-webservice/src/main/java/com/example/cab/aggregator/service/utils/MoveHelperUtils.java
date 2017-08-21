package com.example.cab.aggregator.service.utils;

import com.example.cab.aggregator.service.bean.PositionBean;
import com.example.cab.aggregator.service.entity.Cab;

/**
 * Move cab every X second to distination position.
 *
 * @author ranjeet
 */
public class MoveHelperUtils {

    /**
     * Move till not reached to dist position.Cancel move if reached. sleep 1
     * sec after each move
     *
     * @param cab
     * @param distPosition
     * @throws java.lang.InterruptedException
     */
    public void run(Cab cab, PositionBean distPosition) throws InterruptedException {
        System.out.println("Move cab From: " + cab.getCab().getPosition() + " To :" + distPosition);
//        Thread.sleep(1000);
        boolean reached = cab.move(distPosition);
        while (!reached) {
            System.out.println("Move cab From: " + cab.getCab().getPosition() + " To :" + distPosition);
            Thread.sleep(1000);
            reached = cab.move(distPosition);
        }
        System.out.println("Reached cab From: " + cab.getCab().getPosition() + " To :" + distPosition);
    }

}
