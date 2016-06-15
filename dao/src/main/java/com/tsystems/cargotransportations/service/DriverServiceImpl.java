package com.tsystems.cargotransportations.service;

import com.tsystems.cargotransportations.dao.*;
import com.tsystems.cargotransportations.entity.Driver;
import com.tsystems.cargotransportations.entity.DriverStatus;

import java.util.List;

/**
 * Implements business-logic operations that bound with driver.
 */
public class DriverServiceImpl implements DriverService {

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


    @Override
    public List<Driver> getSuitableDrivers(int orderNumber) {
        // It's need to implement.
        return null;
    }
}
