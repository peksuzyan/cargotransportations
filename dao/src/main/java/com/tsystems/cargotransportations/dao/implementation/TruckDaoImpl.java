package com.tsystems.cargotransportations.dao.implementation;

import com.tsystems.cargotransportations.dao.interfaces.TruckDao;
import com.tsystems.cargotransportations.entity.Truck;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Specific DAO implementation for trucks management.
 */
@Repository("truckDao")
public class TruckDaoImpl extends GenericDaoImpl<Truck> implements TruckDao {

    public TruckDaoImpl() {
        super(Truck.class);
    }

    /**
     * Injected instance of entity manager.
     */
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Truck getByNumber(String number) {
        String query = String.format(
                "FROM %s WHERE number = '%s'", Truck.class.getSimpleName(), number);
        List<Truck> trucks = getEntityManager().createQuery(query, Truck.class).getResultList();
        return trucks.size() != 0 ? trucks.get(0) : null;
    }

    @Override
    public List<Truck> getActiveAndFreeTrucks() {
        String query =
                "SELECT t FROM Truck t " +
                "LEFT JOIN Order AS o ON t.id = o.truck.id " +
                "WHERE t.active = true AND " +
                        "(o.truck IS NULL OR o.status = 'DONE')";
        return getEntityManager().createQuery(query, Truck.class).getResultList();
    }
}
