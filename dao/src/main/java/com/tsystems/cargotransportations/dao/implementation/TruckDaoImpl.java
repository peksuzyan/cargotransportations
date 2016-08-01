package com.tsystems.cargotransportations.dao.implementation;

import com.tsystems.cargotransportations.dao.interfaces.TruckDao;
import com.tsystems.cargotransportations.entity.Truck;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.tsystems.cargotransportations.constants.DaoMapper.TRUCK_DAO;

/**
 * Specific DAO implementation for trucks management.
 */
@Repository(TRUCK_DAO)
public class TruckDaoImpl extends GenericDaoImpl<Truck> implements TruckDao {

    /**
     * Default constructor.
     */
    public TruckDaoImpl() {
        super(Truck.class);
    }

    /**
     * Injected instance of entity manager.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Getter of the entity manager.
     * @return entityManager
     */
    @Override
    EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Gets a list with active and free trucks.
     * @return trucks list
     */
    @Override
    public List<Truck> getActiveAndFreeTrucks() {
        String query =
                "SELECT t FROM Truck t " +
                        "LEFT JOIN Order AS o ON t.id = o.truck.id " +
                        "WHERE t.active = true AND " +
                        "(o.truck IS NULL OR o.status = 'DONE')";
        return getEntityManager().createQuery(query, Truck.class).getResultList();
    }

    /**
     * Gets truck by given number.
     * @param number truck number
     * @return truck
     */
    @Override
    public Truck getByNumber(String number) {
        String query = String.format(
                "SELECT t FROM Truck t WHERE t.number = '%s'", number);
        return getEntityManager().createQuery(query, Truck.class).getSingleResult();
    }

}
