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

    @Override
    public List<Truck> getActiveAndFreeTrucks() {
        String query =
                "SELECT t FROM Truck t " +
                "JOIN Order AS o ON t.id = o.truck.id " +
                "WHERE t.active = true AND " +
                        "(o.truck IS NULL OR o.status = 'DONE')";
        return getLazyEntityManager().createQuery(query, Truck.class).getResultList();
    }
}
