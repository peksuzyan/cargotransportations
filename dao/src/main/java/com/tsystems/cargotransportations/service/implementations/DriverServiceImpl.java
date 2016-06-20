package com.tsystems.cargotransportations.service.implementations;

import com.tsystems.cargotransportations.dao.*;
import com.tsystems.cargotransportations.dao.abstracts.DriverDao;
import com.tsystems.cargotransportations.dao.implementations.DriverDaoImpl;
import com.tsystems.cargotransportations.entity.Driver;
import com.tsystems.cargotransportations.entity.DriverStatus;
import com.tsystems.cargotransportations.service.abstracts.DriverService;

import java.util.List;

/**
 * Implements business-logic operations that bound with driver.
 */
public class DriverServiceImpl implements DriverService {
    /**
     * Instance of implementation of DriverDao class.
     */
    private DriverDao driverDao = new DriverDaoImpl();

    @Override
    public Driver getByNumber(int driverNumber) {
        return driverDao.getByNumber(driverNumber);
    }

    @Override
    public void deleteByNumber(int driverNumber) {
        DaoUtils.executeInTransaction(() -> {
            driverDao.delete(driverDao.getByNumber(driverNumber));
        });
    }

    @Override
    public void changeByNumber(int driverNumber, String firstName, String lastName) {
        DaoUtils.executeInTransaction(() -> {
            Driver driver = driverDao.getByNumber(driverNumber);
            driver.setFirstName(firstName);
            driver.setLastName(lastName);
            driverDao.update(driver);
        });
    }

    @Override
    public void createDriver(String firstName, String lastName, String city) {
        DaoUtils.executeInTransaction(() -> {
            Driver driver = new Driver();
            driverDao.create(driver);
            driver.setFirstName(firstName);
            driver.setLastName(lastName);
            driver.setCity(city);
            driver.setHours(0);
            driver.setStatus(DriverStatus.FREE);
            driver.setNumber(driver.getId() + 100);
        });
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverDao.getAll();
    }

    //TODO
    @Override
    public List<Driver> getSuitableDriversByOrder(int orderNumber) {
        return null;
    }
}
