package com.tsystems.cargotransportations.dao;

import com.tsystems.cargotransportations.entity.Truck;

import java.util.List;

/**
 * Specific DAO implementation for trucks management.
 */
public class TruckDaoImpl extends GenericDaoImpl<Truck> implements TruckDao {

    public TruckDaoImpl() {
        super(Truck.class);
    }

    @Override
    public Truck getByNumber(String number) {
        String query = String.format(
                "FROM %s WHERE number = '%s'", Truck.class.getSimpleName(), number);
        List<Truck> trucks = getLazyEntityManager().createQuery(query, Truck.class).getResultList();
        return trucks.size() != 0 ? trucks.get(0) : null;
    }

    /*
    public List<Truck> getAppropriatedTrucks() {
        String query =
                "SELECT t FROM Truck t " +
                "LEFT JOIN Order o ON t = o.truck " +
                "WHERE t.active = true AND " +
                        "(t.order IS NULL OR o.active = false)";
        return null;//entityManager.createQuery(query, Truck.class).getResultList();
    }
    */
}
