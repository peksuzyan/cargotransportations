package com.tsystems.cargotransportations.service.implementation.implementation;

import com.tsystems.cargotransportations.entity.*;
import com.tsystems.cargotransportations.exception.*;
import com.tsystems.cargotransportations.service.implementation.OrderServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class OrderServiceImplTest {

    private OrderServiceImpl orderService;

    @Before
    public void setup() {
        orderService = new OrderServiceImpl();
    }

    /* has CARGO */

    @Test(expected = OrderNotExistServiceException.class)
    public void hasCargoes_ThrowsOnNullOrder() {
        orderService.hasCargoes(null);
    }

    @Test(expected = CargoesNotAddedServiceException.class)
    public void hasCargoes_ThrowsOnNullList() {
        Order order = new Order();
        orderService.hasCargoes(order);
    }

    @Test(expected = CargoesNotAddedServiceException.class)
    public void hasCargoes_ThrowsOnEmptyList() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        orderService.hasCargoes(order);
    }

    @Test
    public void hasCargoes_ReturnsTrue() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        order.getCargoes().add(new Cargo());
        boolean result = orderService.hasCargoes(order);
        Assert.assertTrue(result);
    }

    /* has DRIVER */

    @Test(expected = OrderNotExistServiceException.class)
    public void hasDrivers_ThrowsOnNullOrder() {
        orderService.hasDrivers(null);
    }

    @Test(expected = DriversNotAddedServiceException.class)
    public void hasDrivers_ThrowsOnNullList() {
        Order order = new Order();
        orderService.hasDrivers(order);
    }

    @Test(expected = DriversNotAddedServiceException.class)
    public void hasDrivers_ThrowsOnEmptyList() {
        Order order = new Order();
        order.setDrivers(new ArrayList<>());
        orderService.hasDrivers(order);
    }

    @Test
    public void hasDrivers_ReturnsTrue() {
        Order order = new Order();
        order.setDrivers(new ArrayList<>());
        order.getDrivers().add(new Driver());
        boolean result = orderService.hasDrivers(order);
        Assert.assertTrue(result);
    }

    /* has ROUTE */

    @Test(expected = OrderNotExistServiceException.class)
    public void hasRoute_ThrowsOnNullOrder() {
        orderService.hasRoute(null);
    }

    @Test(expected = RouteNotAssignedServiceException.class)
    public void hasRoute_ThrowsOnNullEntity() {
        Order order = new Order();
        orderService.hasRoute(order);
    }

    @Test
    public void hasRoute_ReturnsTrue() {
        Order order = new Order();
        order.setRoute(new Route());
        boolean result = orderService.hasRoute(order);
        Assert.assertTrue(result);
    }

    /* has TRUCK */

    @Test(expected = OrderNotExistServiceException.class)
    public void hasTruck_ThrowsOnNullOrder() {
        orderService.hasTruck(null);
    }

    @Test(expected = TruckNotAssignedServiceException.class)
    public void hasTruck_ThrowsOnNullEntity() {
        Order order = new Order();
        orderService.hasTruck(order);
    }

    @Test
    public void hasTruck_ReturnsTrue() {
        Order order = new Order();
        order.setTruck(new Truck());
        boolean result = orderService.hasTruck(order);
        Assert.assertTrue(result);
    }

    /* is TRUCK */

    @Test(expected = OrderNotExistServiceException.class)
    public void isActiveTruck_ThrowsOnNullOrder() {
        orderService.isActiveTruck(null);
    }

    @Test(expected = TruckNotAssignedServiceException.class)
    public void isActiveTruck_ThrowsOnNullEntity() {
        Order order = new Order();
        orderService.isActiveTruck(order);
    }

    @Test(expected = TruckNotActiveServiceException.class)
    public void isActiveTruck_ThrowsOnInactiveEntity() {
        Order order = new Order();
        order.setTruck(new Truck());
        order.getTruck().setActive(false);
        orderService.isActiveTruck(order);
    }

    @Test
    public void isActiveTruck_ReturnsTrue() {
        Order order = new Order();
        order.setTruck(new Truck());
        order.getTruck().setActive(true);
        boolean result = orderService.isActiveTruck(order);
        Assert.assertTrue(result);
    }

    /* is CARGO */

    @Test(expected = OrderNotExistServiceException.class)
    public void isPreparedCargoes_ThrowsOnNullOrder() {
        orderService.isPreparedCargoes(null);
    }

    @Test(expected = CargoesNotAddedServiceException.class)
    public void isPreparedCargoes_ThrowsOnNull() {
        Order order = new Order();
        orderService.isPreparedCargoes(order);
    }

    @Test(expected = OrderHasNullEntityServiceException.class)
    public void isPreparedCargoes_ThrowsOnListWithNull() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        order.getCargoes().add(new Cargo());
        order.getCargoes().add(null);
        orderService.isPreparedCargoes(order);
    }

    @Test(expected = CargoesNotPreparedServiceException.class)
    public void isPreparedCargoes_ThrowsOnListWithNotPrepared() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        Cargo cargo1 = new Cargo();
        cargo1.setStatus(CargoStatus.SHIPPED);
        Cargo cargo2 = new Cargo();
        cargo2.setStatus(CargoStatus.DELIVERED);
        Cargo cargo3 = new Cargo();
        cargo3.setStatus(CargoStatus.PREPARED);
        order.getCargoes().add(cargo1);
        order.getCargoes().add(cargo2);
        order.getCargoes().add(cargo3);
        orderService.isPreparedCargoes(order);
    }

    @Test
    public void isPreparedCargoes_ReturnsTrue() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        Cargo cargo1 = new Cargo();
        cargo1.setStatus(CargoStatus.PREPARED);
        Cargo cargo2 = new Cargo();
        cargo2.setStatus(CargoStatus.PREPARED);
        order.getCargoes().add(cargo1);
        order.getCargoes().add(cargo2);
        boolean result = orderService.isPreparedCargoes(order);
        Assert.assertTrue(result);
    }




}
