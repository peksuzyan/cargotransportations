package com.tsystems.cargotransportations.service.implementations;

import com.tsystems.cargotransportations.dao.*;
import com.tsystems.cargotransportations.dao.abstracts.*;
import com.tsystems.cargotransportations.dao.implementations.*;
import com.tsystems.cargotransportations.entity.*;
import com.tsystems.cargotransportations.service.abstracts.OrderService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implements business-logic operations that bound with order.
 */
public class OrderServiceImpl implements OrderService {
    /**
     * Instance of implementation of OrderDao class.
     */
    private OrderDao orderDao = new OrderDaoImpl();

    /**
     * Instance of implementation of CargoDao class.
     */
    private CargoDao cargoDao = new CargoDaoImpl();

    /**
     * Instance of implementation of DriverDao class.
     */
    private DriverDao driverDao = new DriverDaoImpl();

    /**
     * Instance of implementation of TruckDao class.
     */
    private TruckDao truckDao = new TruckDaoImpl();

    /**
     * Instance of implementation of RouteDao class.
     */
    private RouteDao routeDao = new RouteDaoImpl();

    @Override
    public Order getByNumber(int orderNumber) {
        return orderDao.getByNumber(orderNumber);
    }

    @Override
    public void deleteByNumber(int orderNumber) {
        DaoUtils.executeInTransaction(() -> {
            orderDao.delete(getByNumber(orderNumber));
        });
    }

    @Override
    public void createOrder() {
        DaoUtils.executeInTransaction(() -> {
            Order order = new Order();
            orderDao.create(order);
            order.setStatus(OrderStatus.OPEN);
            order.setCreationDate(new Date());
            order.setNumber(order.getId() + 500);
        });
    }

    @Override
    public void addCargoByNumber(int orderNumber, int cargoNumber) {
        final Order order = getByNumber(orderNumber);
        final Cargo cargo = cargoDao.getByNumber(cargoNumber);
        DaoUtils.executeInTransaction(() -> {
            if (order.getCargoes() == null) {
                order.setCargoes(new ArrayList<>());
            }
            order.getCargoes().add(cargo);
            orderDao.update(order);
        });
    }

    @Override
    public void addDriverByNumber(int orderNumber, int driverNumber) {
        final Order order = getByNumber(orderNumber);
        final Driver driver = driverDao.getByNumber(driverNumber);
        DaoUtils.executeInTransaction(() -> {
            if (order.getDrivers() == null) {
                order.setDrivers(new ArrayList<>());
            }
            order.getDrivers().add(driver);
            orderDao.update(order);
        });
    }

    @Override
    public void assignTruckByNumber(int orderNumber, String truckNumber) {
        final Order order = getByNumber(orderNumber);
        final Truck truck = truckDao.getByNumber(truckNumber);
        DaoUtils.executeInTransaction(() -> {
            order.setTruck(truck);
            orderDao.update(order);
        });
    }

    @Override
    public void assignRouteByNumber(int orderNumber, int routeNumber) {
        final Order order = getByNumber(orderNumber);
        final Route route = routeDao.getByNumber(routeNumber);
        DaoUtils.executeInTransaction(() -> {
            order.setRoute(route);
            orderDao.update(order);
        });
    }

    @Override
    public void excludeCargoByNumber(int orderNumber, int cargoNumber) {
        final Order order = getByNumber(orderNumber);
        final Cargo cargo = cargoDao.getByNumber(cargoNumber);
        if (order.getCargoes() != null) {
            DaoUtils.executeInTransaction(() -> {
                order.getCargoes().remove(cargo);
                orderDao.update(order);
            });
        }
    }

    @Override
    public void excludeDriverByNumber(int orderNumber, int driverNumber) {
        final Order order = getByNumber(orderNumber);
        final Driver driver = driverDao.getByNumber(driverNumber);
        if (order.getDrivers() != null) {
            DaoUtils.executeInTransaction(() -> {
                order.getDrivers().remove(driver);
                orderDao.update(order);
            });
        }
    }

    @Override
    public void refuseTruck(int orderNumber) {
        final Order order = getByNumber(orderNumber);
        DaoUtils.executeInTransaction(() -> {
            order.setTruck(null);
            orderDao.update(order);
        });
    }

    @Override
    public void refuseRoute(int orderNumber) {
        final Order order = getByNumber(orderNumber);
        DaoUtils.executeInTransaction(() -> {
            order.setRoute(null);
            orderDao.update(order);
        });
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAll();
    }

    // TODO
    @Override
    public void sendOrderToPerforming(int orderNumber) {
        final Order order = getByNumber(orderNumber);
        DaoUtils.executeInTransaction(() -> {
            for (Driver driver : order.getDrivers()) {
                driver.setStatus(DriverStatus.BUSY);
                driverDao.update(driver);
            }
            for (Cargo cargo : order.getCargoes()) {
                cargo.setStatus(CargoStatus.SHIPPED);
                cargoDao.update(cargo);
            }
            order.setStatus(OrderStatus.PERFORMING);
            orderDao.update(order);
        });
    }

    @Override
    public Order getPerformingOrderByDriverNumber(int driverNumber) {
        List<Order> orders = orderDao.getAllByStatus(OrderStatus.PERFORMING);
        for (Order order : orders) {
            for (Driver driver : order.getDrivers()) {
                if (driver.getNumber() == driverNumber) {
                    return order;
                }
            }
        }
        return null;
    }
}
