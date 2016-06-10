package cargotransportations;

import cargotransportations.dao.*;
import cargotransportations.entity.Driver;
import cargotransportations.entity.Order;
import cargotransportations.entity.Truck;
import cargotransportations.service.DriverService;
import cargotransportations.service.DriverServiceImpl;
import cargotransportations.util.DriverStatus;

import java.util.ArrayList;
import java.util.Date;

public class Simulator {
    private static TruckDao truckDao = new TruckDaoImpl();
    private static OrderDao orderDao = new OrderDaoImpl();
    private static DriverDao driverDao = new DriverDaoImpl();

    public static void main(String[] args) throws InterruptedException {
/*        createDriverOrderTruck();
        createDriver();
        printDrivers(1);
        //removeDriver(1);
        DaoUtils.close();
        printDrivers(1);*/

        DriverService driverService = new DriverServiceImpl();
        for (Driver driver : driverService.getAllDrivers()) {
            String rowData = String.format(
                    "%d, %s, %s, %d, %s",
                    driver.getId(),
                    driver.getFirstName(),
                    driver.getLastName(),
                    driver.getHours(),
                    driver.getCity());
            System.out.println(rowData);
        }
    }

    private static void printDrivers(int orderId) {
        Order order = orderDao.read(orderId);
        for (Driver driver : order.getDrivers()) {
            System.out.println(driver.getFirstName() + " " + driver.getLastName());
        }
    }

    private static void removeDriver(int id) {
        DaoUtils.executeInTransaction(() -> {
            Driver driver = driverDao.read(id);
            driverDao.delete(driver);
        });
    }

    private static void createDriver() {
        DaoUtils.executeInTransaction(() -> {
            Driver driver = new Driver();
            driver.setFirstName("Petr");
            driver.setLastName("Petrov");
            driver.setStatus(DriverStatus.FREE);
            driver.setCity("Saint-Petersburg");
            driver.setHours(64);
            driverDao.update(driver);

            Truck truck = truckDao.read(1);
            driver.setTruck(truck);

            Order order = orderDao.read(1);
            if (order.getDrivers() == null) {
                order.setDrivers(new ArrayList<>());
            }
            order.getDrivers().add(driver);
        });
    }

    private static void createDriverOrderTruck() {
        DaoUtils.executeInTransaction(() -> {
            Truck truck = new Truck();
            truck.setNumber("c292kt");
            truck.setPeople(2);
            truck.setCity("Moscow");
            truck.setActive(true);
            truck.setCapacity(125.0);
            truckDao.update(truck);

            Order order = new Order();
            order.setActive(true);
            order.setCreationDate(new Date());
            orderDao.update(order);

            Driver driver = new Driver();
            driver.setFirstName("Ivan");
            driver.setLastName("Ivanov");
            driver.setStatus(DriverStatus.FREE);
            driver.setCity("Saint-Petersburg");
            driver.setHours(15);
            driverDao.update(driver);

            driver.setTruck(truck);

            order.setTruck(truck);
            if (order.getDrivers() == null) {
                order.setDrivers(new ArrayList<>());
            }
            order.getDrivers().add(driver);
        });
    }
}
