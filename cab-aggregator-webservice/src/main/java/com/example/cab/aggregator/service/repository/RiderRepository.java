package com.example.cab.aggregator.service.repository;

import com.example.cab.aggregator.service.bean.RiderBean;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton
 *
 * @author ranjeet
 */
public class RiderRepository implements Repository<RiderBean> {

    private final Map<Integer, RiderBean> riderDB;

    private RiderRepository() {
        this.riderDB = new HashMap<>();
    }

    public static RiderRepository getInstance() {
        return RiderRepositoryHelper.INSTANCE;
    }



    @Override
    public boolean delete(int id) {
        if (riderDB.containsKey(id)) {
            riderDB.remove(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean create(RiderBean rider) {
        if (!riderDB.containsKey(rider.getId())) {
            riderDB.put(rider.getId(), rider);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(int id, RiderBean newRider) {
        if (riderDB.containsKey(id)) {
            riderDB.put(id, newRider);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public RiderBean get(int id) {
        if (riderDB.containsKey(id)) {
            return riderDB.get(id);
        }

        throw new RuntimeException("User " + id + "is not present");

    }

    @Override
    public Collection<RiderBean> getAll() {
        return riderDB.values();
    }

    private static class RiderRepositoryHelper {

        public static final RiderRepository INSTANCE = new RiderRepository();
    }


}
