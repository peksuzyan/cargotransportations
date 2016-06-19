package com.tsystems.cargotransportations;

import com.tsystems.cargotransportations.dao.*;
import com.tsystems.cargotransportations.entity.*;
import com.tsystems.cargotransportations.service.DriverService;
import com.tsystems.cargotransportations.service.DriverServiceImpl;

import java.util.ArrayList;
import java.util.Date;

public class Simulator {
    private static TruckDao truckDao = new TruckDaoImpl();
    private static OrderDao orderDao = new OrderDaoImpl();
    private static DriverDao driverDao = new DriverDaoImpl();

    public static void main(String[] args) throws InterruptedException {

        DriverService driverService = new DriverServiceImpl();

        driverService.createDriver("Tomas", "Irvin", "New York");
        driverService.createDriver("Vasya", "Korolenko", "Moscow");
        driverService.createDriver("Kirill", "Matroskin", "Ufa");
        driverService.createDriver("Zhenya", "Efimov", "Vladivostok");

/*        Driver driver = driverDao.read(1);
        Driver driver2 = driverDao.read(2);
        Truck truck = truckDao.read(1);
        Order order = orderDao.read(1);

        driverDao.delete(driver);
        driverDao.delete(driver2);
        orderDao.delete(order);
        truckDao.delete(truck);*/
/*        createDriverOrderTruck();
        createTruck();*/


        //System.out.println(DaoGenerator.getMaxId());
        //printDrivers(1);
        //removeDriver(1);
        //DaoUtils.close();
        //printDrivers(1);

/*        DriverService driverService = new DriverServiceImpl();
        for (Driver driver : driverService.getAllDrivers()) {
            String rowData = String.format(
                    "%d, %s, %s, %d, %s",
                    driver.getId(),
                    driver.getFirstName(),
                    driver.getLastName(),
                    driver.getHours(),
                    driver.getCity());
            System.out.println(rowData);
        }*/
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
            truck.setNumber("ta215s");
            truck.setPeople(2);
            truck.setCity("Moscow");
            truck.setActive(true);
            truck.setCapacity(125.0);
            truckDao.update(truck);

            Order order = new Order();
            order.setStatus(OrderStatus.OPEN);
            order.setCreationDate(new Date());
            //order.setNumber(orderDao.getLastId() + 1);
            orderDao.update(order);

            Driver driver = new Driver();
            driver.setFirstName("Ivan");
            driver.setLastName("Ivanov");
            driver.setStatus(DriverStatus.FREE);
            driver.setCity("Saint-Petersburg");
            driver.setHours(15);
            //driver.setNumber(orderDao.getLastId() + 1);
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
