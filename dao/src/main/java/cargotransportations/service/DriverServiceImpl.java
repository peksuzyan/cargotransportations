package cargotransportations.service;

import cargotransportations.dao.*;
import cargotransportations.entity.Driver;
import cargotransportations.entity.Order;
import cargotransportations.entity.Truck;
import cargotransportations.util.DriverStatus;

import java.util.ArrayList;
import java.util.List;

public class DriverServiceImpl implements DriverService {

    private DriverDao driverDao = new DriverDaoImpl();
    private TruckDao truckDao = new TruckDaoImpl();
    private OrderDao orderDao = new OrderDaoImpl();

    public void createDriver(String firstName, String lastName, String city) {
        DaoUtils.executeInTransaction(() -> {
            Driver driver = new Driver();
            driverDao.create(driver);
            driver.setFirstName(firstName);
            driver.setLastName(lastName);
            driver.setCity(city);
            driver.setHours(0);
            driver.setStatus(DriverStatus.FREE);
        });
    }

    public void removeDriver(int id) {
        DaoUtils.executeInTransaction(() -> {
            Driver driver = driverDao.read(id);
            driverDao.delete(driver);
        });
    }

    public void changeDriver(int id, String firstName, String lastName, String city,
                             int hours, DriverStatus status, int truckId, int orderId) {
        DaoUtils.executeInTransaction(() -> {

        });
    }

    public List<Driver> getAllDrivers() {
        return driverDao.getAll();
    }
}
