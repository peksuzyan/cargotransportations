package cargotransportations.dao.manager;

import cargotransportations.dao.entity.Driver;
import cargotransportations.dao.entity.Order;
import cargotransportations.dao.entity.Truck;
import cargotransportations.dao.util.DriverStatus;

public class DriverManager extends AbstractManager<Driver> {

    @Override
    public Class<Driver> getItemClass() {
        return Driver.class;
    }

    public Driver create(String firstName, String lastName, String city) {
        Driver driver = create();
        getEntityManager().getTransaction().begin();
        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setCity(city);
        driver.setHours(0);
        driver.setStatus(DriverStatus.FREE);
        getEntityManager().getTransaction().commit();
        return driver;
    }

    public void update(int driverId, String firstName, String lastName, String city) {
        Driver driver = read(driverId);
        getEntityManager().getTransaction().begin();
        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setCity(city);
        getEntityManager().getTransaction().commit();
    }

    public void updateTruck(int driverId, Truck truck) {
        Driver driver = read(driverId);
        getEntityManager().getTransaction().begin();
        driver.setTruck(truck);
        getEntityManager().getTransaction().commit();
    }

    public void updateOrder(int driverId, Order order) {
        Driver driver = read(driverId);
        getEntityManager().getTransaction().begin();
        driver.setOrder(order);
        getEntityManager().getTransaction().commit();
    }

    public void updateHours(int driverId, int hours) {
        Driver driver = read(driverId);
        getEntityManager().getTransaction().begin();
        driver.setHours(driver.getHours() + hours);
        getEntityManager().getTransaction().commit();
    }

    public void updateStatus(int driverId, DriverStatus status) {
        Driver driver = read(driverId);
        getEntityManager().getTransaction().begin();
        driver.setStatus(status);
        getEntityManager().getTransaction().commit();
    }
}
