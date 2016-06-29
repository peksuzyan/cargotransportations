package com.tsystems.cargotransportations.dao.implementation;

import com.tsystems.cargotransportations.dao.interfaces.DriverDao;
import com.tsystems.cargotransportations.entity.Driver;

import java.util.List;

/**
 * Specific DAO implementation for drivers management.
 */
public class DriverDaoImpl extends GenericDaoImpl<Driver> implements DriverDao {

    public DriverDaoImpl() {
        super(Driver.class);
    }

    @Override
    public Driver getByNumber(int number) {
        String query = String.format(
                "FROM %s WHERE number = %d", Driver.class.getSimpleName(), number);
        List<Driver> drivers = getLazyEntityManager().createQuery(query, Driver.class).getResultList();
        return drivers.size() != 0 ? drivers.get(0) : null;
    }

    @Override
    public List<Driver> getFreeDrivers() {
        String query = "SELECT d FROM Driver AS d WHERE d.status = 'FREE'";
        return getLazyEntityManager().createQuery(query, Driver.class).getResultList();
    }
}
