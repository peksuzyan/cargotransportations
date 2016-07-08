package com.tsystems.cargotransportations.dao.implementation;

import com.tsystems.cargotransportations.dao.interfaces.DriverDao;
import com.tsystems.cargotransportations.entity.Driver;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Specific DAO implementation for drivers management.
 */
@Repository("driverDao")
public class DriverDaoImpl extends GenericDaoImpl<Driver> implements DriverDao {

    public DriverDaoImpl() {
        super(Driver.class);
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
    public Driver getByNumber(int number) {
        String query = String.format(
                "FROM %s WHERE number = %d", Driver.class.getSimpleName(), number);
        List<Driver> drivers = getEntityManager().createQuery(query, Driver.class).getResultList();
        return drivers.size() != 0 ? drivers.get(0) : null;
    }

    @Override
    public List<Driver> getFreeDrivers() {
        String query = "SELECT d FROM Driver AS d WHERE d.status = 'FREE'";
        return getEntityManager().createQuery(query, Driver.class).getResultList();
    }
}
