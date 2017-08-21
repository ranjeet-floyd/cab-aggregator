package com.example.cab.aggregator.service.strategy.move;

import com.example.cab.aggregator.service.bean.PositionBean;

/**
 * Based on type of cab ..move cab
 *
 * @author ranjeet
 */
public interface MoveStrategy {

    PositionBean move(PositionBean curr, PositionBean dst);


}
