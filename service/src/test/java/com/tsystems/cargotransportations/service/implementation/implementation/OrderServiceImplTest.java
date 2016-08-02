package com.tsystems.cargotransportations.service.implementation.implementation;

import com.tsystems.cargotransportations.entity.*;
import com.tsystems.cargotransportations.exception.*;
import com.tsystems.cargotransportations.service.implementation.OrderServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class OrderServiceImplTest {

    /**
     * Service instance.
     */
    private OrderServiceImpl orderService;

    /**
     * Route cities.
     */
    private static final String MILAN = "MILAN";
    private static final String WARSAW = "WARSAW";
    private static final String BARCELONA = "BARCELONA";
    private static final String ROME = "ROME";
    private static final String RIGA = "RIGA";
    private static final String PARIS = "PARIS";

    @Before
    public void setup() {
        orderService = new OrderServiceImpl();
    }

    /* order is present */

    @Test(expected = OrderNotExistServiceException.class)
    public void isPresent_ThrowsOnNullOrder() {
        orderService.isPresent(null);
    }

    @Test
    public void isPresent_ReturnsTrue() {
        Order order = new Order();
        boolean result = orderService.isPresent(order);
        Assert.assertTrue(result);
    }

    /* has prepared cargoes */

    @Test(expected = CargoesNotAddedServiceException.class)
    public void hasPreparedCargoes_ThrowsOnNullList() {
        Order order = new Order();
        orderService.hasPreparedCargoes(order);
    }

    @Test(expected = CargoesNotAddedServiceException.class)
    public void hasPreparedCargoes_ThrowsOnEmptyList() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        orderService.hasPreparedCargoes(order);
    }

    @Test(expected = CargoesNotPreparedServiceException.class)
    public void hasPreparedCargoes_ThrowsOnListWithNotPrepared() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        Cargo cargo1 = new Cargo();
        Cargo cargo2 = new Cargo();
        Cargo cargo3 = new Cargo();
        cargo1.setStatus(CargoStatus.SHIPPED);
        cargo2.setStatus(CargoStatus.DELIVERED);
        cargo3.setStatus(CargoStatus.PREPARED);
        order.getCargoes().add(cargo1);
        order.getCargoes().add(cargo2);
        order.getCargoes().add(cargo3);
        orderService.hasPreparedCargoes(order);
    }

    @Test
    public void hasPreparedCargoes_ReturnsTrue() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        Cargo cargo1 = new Cargo();
        Cargo cargo2 = new Cargo();
        Cargo cargo3 = new Cargo();
        cargo1.setStatus(CargoStatus.PREPARED);
        cargo2.setStatus(CargoStatus.PREPARED);
        cargo3.setStatus(CargoStatus.PREPARED);
        order.getCargoes().add(cargo1);
        order.getCargoes().add(cargo2);
        order.getCargoes().add(cargo3);
        boolean result = orderService.hasPreparedCargoes(order);
        Assert.assertTrue(result);
    }

    /* has free drivers */

    @Test(expected = DriversNotAddedServiceException.class)
    public void hasFreeDrivers_ThrowsOnNullList() {
        Order order = new Order();
        orderService.hasFreeDrivers(order);
    }

    @Test(expected = DriversNotAddedServiceException.class)
    public void hasFreeDrivers_ThrowsOnEmptyList() {
        Order order = new Order();
        order.setDrivers(new ArrayList<>());
        orderService.hasFreeDrivers(order);
    }

    @Test(expected = DriversNotFreeServiceException.class)
    public void hasFreeDrivers_ThrowsOnListWithNotFree() {
        Order order = new Order();
        order.setDrivers(new ArrayList<>());
        Driver driver1 = new Driver();
        Driver driver2 = new Driver();
        Driver driver3 = new Driver();
        driver1.setStatus(DriverStatus.BUSY);
        driver2.setStatus(DriverStatus.FREE);
        driver3.setStatus(DriverStatus.ASSIGNED);
        order.getDrivers().add(driver1);
        order.getDrivers().add(driver2);
        order.getDrivers().add(driver3);
        orderService.hasFreeDrivers(order);
    }

    @Test
    public void hasFreeDrivers_ReturnsTrue() {
        Order order = new Order();
        order.setDrivers(new ArrayList<>());
        Driver driver1 = new Driver();
        Driver driver2 = new Driver();
        Driver driver3 = new Driver();
        driver1.setStatus(DriverStatus.FREE);
        driver2.setStatus(DriverStatus.FREE);
        driver3.setStatus(DriverStatus.FREE);
        order.getDrivers().add(driver1);
        order.getDrivers().add(driver2);
        order.getDrivers().add(driver3);
        boolean result = orderService.hasFreeDrivers(order);
        Assert.assertTrue(result);
    }

    /* has full route */

    @Test(expected = RouteNotAssignedServiceException.class)
    public void hasFullRoute_ThrowsOnNullEntity() {
        Order order = new Order();
        orderService.hasFullRoute(order);
    }

    @Test(expected = RouteNotFullServiceException.class)
    public void hasFullRoute_ThrowsOnNullCitiesList() {
        Order order = new Order();
        order.setRoute(new Route());
        orderService.hasFullRoute(order);
    }

    @Test(expected = RouteNotFullServiceException.class)
    public void hasFullRoute_ThrowsOnNotFullCitiesList() {
        Order order = new Order();
        order.setRoute(new Route());
        order.getRoute().setCities(new ArrayList<>());
        order.getRoute().getCities().add(PARIS);
        orderService.hasFullRoute(order);
    }

    @Test
    public void hasFullRoute_ReturnsTrue() {
        Order order = new Order();
        order.setRoute(new Route());
        order.getRoute().setCities(new ArrayList<>());
        order.getRoute().getCities().add(PARIS);
        order.getRoute().getCities().add(MILAN);
        boolean result = orderService.hasFullRoute(order);
        Assert.assertTrue(result);
    }

    /* has active truck */

    @Test(expected = TruckNotAssignedServiceException.class)
    public void hasActiveTruck_ThrowsOnNullEntity() {
        Order order = new Order();
        orderService.hasActiveTruck(order);
    }

    @Test(expected = TruckNotActiveServiceException.class)
    public void hasActiveTruck_ThrowsOnInactive() {
        Order order = new Order();
        order.setTruck(new Truck());
        orderService.hasActiveTruck(order);
    }

    @Test
    public void hasActiveTruck_ReturnsTrue() {
        Order order = new Order();
        order.setTruck(new Truck());
        order.getTruck().setActive(true);
        boolean result = orderService.hasActiveTruck(order);
        Assert.assertTrue(result);
    }

    /* has enough drivers */

    @Test(expected = TruckHasTooManyDriversServiceException.class)
    public void hasNotTooManyDrivers_ThrowsOnTooMany() {
        Order order = new Order();
        order.setTruck(new Truck());
        order.getTruck().setPeople(1);
        order.setDrivers(new ArrayList<>());
        order.getDrivers().add(new Driver());
        order.getDrivers().add(new Driver());
        orderService.hasNotTooManyDrivers(order);
    }

    @Test
    public void hasNotTooManyDrivers_ReturnsTrue() {
        Order order = new Order();
        order.setTruck(new Truck());
        order.getTruck().setPeople(2);
        order.setDrivers(new ArrayList<>());
        order.getDrivers().add(new Driver());
        order.getDrivers().add(new Driver());
        boolean result = orderService.hasNotTooManyDrivers(order);
        Assert.assertTrue(result);
    }

    /* has same locations of truck and route */

    @Test(expected = DifferentLocationsTruckAndRouteServiceException.class)
    public void hasSameLocationsTruckAndRoute_ThrowsOnDifferentLocations() {
        Order order = new Order();
        order.setTruck(new Truck());
        order.getTruck().setCity(ROME);
        order.setRoute(new Route());
        order.getRoute().setCities(new ArrayList<>());
        order.getRoute().getCities().add(MILAN);
        order.getRoute().getCities().add(ROME);
        orderService.hasSameLocationsTruckAndRoute(order);
    }

    @Test
    public void hasSameLocationsTruckAndRoute_ReturnsTrue() {
        Order order = new Order();
        order.setTruck(new Truck());
        order.getTruck().setCity(MILAN);
        order.setRoute(new Route());
        order.getRoute().setCities(new ArrayList<>());
        order.getRoute().getCities().add(MILAN);
        order.getRoute().getCities().add(ROME);
        boolean result = orderService.hasSameLocationsTruckAndRoute(order);
        Assert.assertTrue(result);
    }

    /* has same locations of truck and all drivers */

    @Test(expected = DifferentLocationsTruckAndDriversServiceException.class)
    public void hasSameLocationsTruckAndDrivers_ThrowsOnDifferentLocations() {
        Order order = new Order();
        order.setTruck(new Truck());
        order.getTruck().setCity(PARIS);
        order.setDrivers(new ArrayList<>());
        Driver driver1 = new Driver();
        Driver driver2 = new Driver();
        Driver driver3 = new Driver();
        driver1.setCity(MILAN);
        driver2.setCity(PARIS);
        driver3.setCity(RIGA);
        order.getDrivers().add(driver1);
        order.getDrivers().add(driver2);
        order.getDrivers().add(driver3);
        orderService.hasSameLocationsTruckAndDrivers(order);
    }

    @Test
    public void hasSameLocationsTruckAndDrivers_ReturnsTrue() {
        Order order = new Order();
        order.setTruck(new Truck());
        order.getTruck().setCity(RIGA);
        order.setDrivers(new ArrayList<>());
        Driver driver1 = new Driver();
        Driver driver2 = new Driver();
        Driver driver3 = new Driver();
        driver1.setCity(RIGA);
        driver2.setCity(RIGA);
        driver3.setCity(RIGA);
        order.getDrivers().add(driver1);
        order.getDrivers().add(driver2);
        order.getDrivers().add(driver3);
        boolean result = orderService.hasSameLocationsTruckAndDrivers(order);
        Assert.assertTrue(result);
    }

    /* has shipping and delivering right order */

    @Test(expected = WrongOrderDepartureAndArrivalServiceException.class)
    public void hasShippingAndDeliveringRightOrder_ThrowsOnAbsentArrival() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, MILAN, RIGA);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        orderService.hasShippingAndDeliveringRightOrder(order);
    }

    @Test(expected = WrongOrderDepartureAndArrivalServiceException.class)
    public void hasShippingAndDeliveringRightOrder_ThrowsOnAbsentDeparture() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, RIGA, MILAN);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        orderService.hasShippingAndDeliveringRightOrder(order);
    }

    @Test(expected = WrongOrderDepartureAndArrivalServiceException.class)
    public void hasShippingAndDeliveringRightOrder_ThrowsOnWrongOrder() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, MILAN, BARCELONA);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        orderService.hasShippingAndDeliveringRightOrder(order);
    }

    @Test
    public void hasShippingAndDeliveringRightOrder_ReturnsTrue() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, ROME, WARSAW);
        initCargo(order, BARCELONA, MILAN);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        boolean result = orderService.hasShippingAndDeliveringRightOrder(order);
        Assert.assertTrue(result);
    }

    /* has truck with enough capacity */

    @Test(expected = TruckWithNotEnoughCapacityServiceException.class)
    public void hasTruckWithEnoughCapacity_ThrowsWhenOneCargo() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, BARCELONA, MILAN, 11.0);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        initTruck(order); // capacity = 10.0
        orderService.hasTruckWithEnoughCapacity(order);
    }

    @Test(expected = TruckWithNotEnoughCapacityServiceException.class)
    public void hasTruckWithEnoughCapacity_ThrowsWhenTwoCargoInDifferentLocationsWithFullCrossing() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, BARCELONA, MILAN, 3.0);
        initCargo(order, ROME, WARSAW, 8.0);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        initTruck(order); // capacity = 10.0
        orderService.hasTruckWithEnoughCapacity(order);
    }

    @Test(expected = TruckWithNotEnoughCapacityServiceException.class)
    public void hasTruckWithEnoughCapacity_ThrowsWhenTwoCargoInSameStartLocations() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, ROME, MILAN, 3.0);
        initCargo(order, ROME, WARSAW, 8.0);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        initTruck(order); // capacity = 10.0
        orderService.hasTruckWithEnoughCapacity(order);
    }

    @Test(expected = TruckWithNotEnoughCapacityServiceException.class)
    public void hasTruckWithEnoughCapacity_ThrowsWhenTwoCargoInSameEndLocations() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, MILAN, WARSAW, 3.0);
        initCargo(order, BARCELONA, WARSAW, 8.0);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        initTruck(order); // capacity = 10.0
        orderService.hasTruckWithEnoughCapacity(order);
    }

    @Test(expected = TruckWithNotEnoughCapacityServiceException.class)
    public void hasTruckWithEnoughCapacity_ThrowsWhenTwoCargoInDifferentLocationsWithPartialCrossing() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, BARCELONA, WARSAW, 3.0);
        initCargo(order, ROME, MILAN, 8.0);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        initTruck(order); // capacity = 10.0
        orderService.hasTruckWithEnoughCapacity(order);
    }

    @Test
    public void hasTruckWithEnoughCapacity_ReturnsTrueWhenOneCargo() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, BARCELONA, MILAN, 3.0);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        initTruck(order); // capacity = 10.0
        boolean result = orderService.hasTruckWithEnoughCapacity(order);
        Assert.assertTrue(result);
    }

    @Test
    public void hasTruckWithEnoughCapacity_ReturnsTrueWhenTwoCargoInSameStartLocations() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, BARCELONA, MILAN, 3.0);
        initCargo(order, BARCELONA, WARSAW, 6.0);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        initTruck(order); // capacity = 10.0
        boolean result = orderService.hasTruckWithEnoughCapacity(order);
        Assert.assertTrue(result);
    }

    @Test
    public void hasTruckWithEnoughCapacity_ReturnsTrueWhenTwoCargoInSameEndLocations() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, ROME, MILAN, 3.0);
        initCargo(order, BARCELONA, MILAN, 6.0);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        initTruck(order); // capacity = 10.0
        boolean result = orderService.hasTruckWithEnoughCapacity(order);
        Assert.assertTrue(result);
    }

    @Test
    public void hasTruckWithEnoughCapacity_ReturnsTrueWhenTwoCargoInDifferentLocationsWithFullCrossing() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, BARCELONA, MILAN, 3.0);
        initCargo(order, ROME, WARSAW, 6.0);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        initTruck(order); // capacity = 10.0
        boolean result = orderService.hasTruckWithEnoughCapacity(order);
        Assert.assertTrue(result);
    }

    @Test
    public void hasTruckWithEnoughCapacity_ReturnsTrueWhenTwoCargoInDifferentLocationsWithPartialCrossing() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, BARCELONA, WARSAW, 3.0);
        initCargo(order, ROME, MILAN, 6.0);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        initTruck(order); // capacity = 10.0
        boolean result = orderService.hasTruckWithEnoughCapacity(order);
        Assert.assertTrue(result);
    }

    @Test
    public void hasTruckWithEnoughCapacity_ReturnsTrueWhenTwoCargoInDifferentLocationsWithoutCrossing() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, MILAN, WARSAW, 3.0);
        initCargo(order, ROME, BARCELONA, 6.0);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        initTruck(order); // capacity = 10.0
        boolean result = orderService.hasTruckWithEnoughCapacity(order);
        Assert.assertTrue(result);
    }

    /* initializer-s */

    private void initRoute(Order order) {
        order.setRoute(new Route());
        order.getRoute().setCities(new ArrayList<>());
        order.getRoute().getCities().add(ROME);
        order.getRoute().getCities().add(BARCELONA);
        order.getRoute().getCities().add(MILAN);
        order.getRoute().getCities().add(WARSAW);
    }

    private void initCargo(Order order, String departure, String arrival) {
        initCargo(order, departure, arrival, 0.0);
    }

    private void initCargo(Order order, String departure, String arrival, double weight) {
        order.getCargoes().add(new Cargo());
        int index = order.getCargoes().size() - 1;
        order.getCargoes().get(index).setDepartureCity(departure);
        order.getCargoes().get(index).setArrivalCity(arrival);
        order.getCargoes().get(index).setWeight(weight);
    }

    private void initTruck(Order order) {
        order.setTruck(new Truck());
        order.getTruck().setCapacity(10.0);
    }

}
