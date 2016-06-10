package cargotransportations.dao;

import cargotransportations.entity.Truck;

import java.util.List;

public class TruckDaoImpl extends GenericDaoImpl<Truck> implements TruckDao {

    public TruckDaoImpl() {
        super(Truck.class);
    }

    public List<Truck> getAppropriatedTrucks() {
        String query =
                "SELECT t FROM Truck t " +
                "LEFT JOIN Order o ON t = o.truck " +
                "WHERE t.active = true AND " +
                        "(t.order IS NULL OR o.active = false)";
        return null;//entityManager.createQuery(query, Truck.class).getResultList();
    }
}
