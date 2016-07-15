package com.tsystems.cargotransportations.dao.implementation;

import com.tsystems.cargotransportations.dao.interfaces.DriverDao;
import com.tsystems.cargotransportations.entity.Driver;
import com.tsystems.cargotransportations.entity.DriverStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.tsystems.cargotransportations.constants.DaoMapping.DRIVER_DAO;

/**
 * Specific DAO implementation for drivers management.
 */
@Repository(DRIVER_DAO)
public class DriverDaoImpl extends GenericDaoImpl<Driver> implements DriverDao {

    /**
     * Default constructor.
     */
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
    public List<Driver> getDriversByStatus(DriverStatus status) {
        String query = String.format("SELECT d FROM Driver AS d WHERE d.status = '%s'", status);
        return getEntityManager().createQuery(query, Driver.class).getResultList();
    }

}
