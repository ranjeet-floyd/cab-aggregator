package com.example.cab.aggregator.service.repository;

import com.example.cab.aggregator.service.bean.ActiveCabBean;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ranjeet
 */
public class CabRepositoryImp implements Repository<ActiveCabBean> {

    private final Map<Integer, ActiveCabBean> cabDB;

    private CabRepositoryImp() {
        this.cabDB = new HashMap<>();
    }

    public static CabRepositoryImp getInstance() {
        return CabRepositoryImpHelper.INSTANCE;
    }

    private static class CabRepositoryImpHelper {

        public static final CabRepositoryImp INSTANCE = new CabRepositoryImp();
    }

    @Override
    public boolean delete(int id) {
        if (cabDB.containsKey(id)) {
            cabDB.remove(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean create(ActiveCabBean cabBean) {
        if (!cabDB.containsKey(cabBean.getId())) {
            cabDB.put(cabBean.getId(), cabBean);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(int id, ActiveCabBean newCabBean) {
        if (cabDB.containsKey(id)) {
            cabDB.put(id, newCabBean);
            return true;
        }
        return false;
    }

    /**
     * return cab for given id else return null.
     *
     * @param id : cab id
     * @return
     */
    @Override
    public ActiveCabBean get(int id) {
        if (cabDB.containsKey(id)) {
            return cabDB.get(id);
        }

        return null;

    }

    @Override
    public Collection<ActiveCabBean> getAll() {
        return cabDB.values();
    }

}
