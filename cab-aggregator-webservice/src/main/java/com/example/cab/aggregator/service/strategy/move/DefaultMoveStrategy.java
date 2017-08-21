package com.example.cab.aggregator.service.strategy.move;

import com.example.cab.aggregator.service.bean.PositionBean;

/**
 *
 * @author ranjeet
 */
public class DefaultMoveStrategy implements MoveStrategy {

    /**
     * Simple move logic. In each move, increase X or Y 1 unit towards dst.
     * <p>
     * Move curr pos X till equal to dst X.
     * <p>
     * then Move curr pos Y till equal to dst Y.
     *
     * @param curr : curr position
     * @param dst : Distination position
     * @return
     */
    @Override
    public PositionBean move(PositionBean curr, PositionBean dst) {

        if (!curr.equals(dst)) {

            if (curr.getX() > dst.getX()) {
                curr.setX(curr.getX() - 1);
                return curr;

            } else {
                if (curr.getX() < dst.getX()) {
                    curr.setX(curr.getX() + 1);
                    return curr;
                    //if curr X and dst X are equal
                } else {
                    if (curr.getY() > dst.getY()) {
                        curr.setY(curr.getY() - 1);
                        return curr;
                    } else {
                        if (curr.getY() < dst.getY()) {
                            curr.setY(curr.getY() + 1);
                        }
                        return curr;
                    }
                }
            }
        }

        return curr;

    }

}
