package com.tsystems.cargotransportations.test;

import com.tsystems.cargotransportations.entity.*;
import com.tsystems.cargotransportations.exception.*;
import com.tsystems.cargotransportations.service.implementation.OrderServiceImpl;
import com.tsystems.cargotransportations.util.TimeCalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@SuppressWarnings("Duplicates")
public class OrderServiceImplTest {

    /**
     * Service instance.
     */
    private OrderServiceImpl service;

    /**
     * Time calculator instance.
     */
    @Mock
    private TimeCalculator timeCalculator;

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
        MockitoAnnotations.initMocks(this);
        service = new OrderServiceImpl();
        service.setTimeCalculator(timeCalculator);
    }

    /* order is present */

    @Test(expected = OrderNotExistServiceException.class)
    public void isPresent_ThrowsOnNullOrder() {
        service.isPresent(null);
    }

    @Test
    public void isPresent_ReturnsTrue() {
        Order order = new Order();
        boolean result = service.isPresent(order);
        Assert.assertTrue(result);
    }

    /* has prepared cargoes */

    @Test(expected = CargoesNotAddedServiceException.class)
    public void hasPreparedCargoes_ThrowsOnNullList() {
        Order order = new Order();
        service.hasPreparedCargoes(order);
    }

    @Test(expected = CargoesNotAddedServiceException.class)
    public void hasPreparedCargoes_ThrowsOnEmptyList() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        service.hasPreparedCargoes(order);
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
        service.hasPreparedCargoes(order);
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
        boolean result = service.hasPreparedCargoes(order);
        Assert.assertTrue(result);
    }

    /* has free drivers */

    @Test(expected = DriversNotAddedServiceException.class)
    public void hasFreeDrivers_ThrowsOnNullList() {
        Order order = new Order();
        service.hasFreeDrivers(order);
    }

    @Test(expected = DriversNotAddedServiceException.class)
    public void hasFreeDrivers_ThrowsOnEmptyList() {
        Order order = new Order();
        order.setDrivers(new ArrayList<>());
        service.hasFreeDrivers(order);
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
        service.hasFreeDrivers(order);
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
        boolean result = service.hasFreeDrivers(order);
        Assert.assertTrue(result);
    }

    /* has full route */

    @Test(expected = RouteNotAssignedServiceException.class)
    public void hasFullRoute_ThrowsOnNullEntity() {
        Order order = new Order();
        service.hasFullRoute(order);
    }

    @Test(expected = RouteNotFullServiceException.class)
    public void hasFullRoute_ThrowsOnNullCitiesList() {
        Order order = new Order();
        order.setRoute(new Route());
        service.hasFullRoute(order);
    }

    @Test(expected = RouteNotFullServiceException.class)
    public void hasFullRoute_ThrowsOnNotFullCitiesList() {
        Order order = new Order();
        order.setRoute(new Route());
        order.getRoute().setCities(new ArrayList<>());
        order.getRoute().getCities().add(PARIS);
        service.hasFullRoute(order);
    }

    @Test
    public void hasFullRoute_ReturnsTrue() {
        Order order = new Order();
        order.setRoute(new Route());
        order.getRoute().setCities(new ArrayList<>());
        order.getRoute().getCities().add(PARIS);
        order.getRoute().getCities().add(MILAN);
        boolean result = service.hasFullRoute(order);
        Assert.assertTrue(result);
    }

    /* has active truck */

    @Test(expected = TruckNotAssignedServiceException.class)
    public void hasActiveTruck_ThrowsOnNullEntity() {
        Order order = new Order();
        service.hasActiveTruck(order);
    }

    @Test(expected = TruckNotActiveServiceException.class)
    public void hasActiveTruck_ThrowsOnInactive() {
        Order order = new Order();
        order.setTruck(new Truck());
        service.hasActiveTruck(order);
    }

    @Test
    public void hasActiveTruck_ReturnsTrue() {
        Order order = new Order();
        order.setTruck(new Truck());
        order.getTruck().setActive(true);
        boolean result = service.hasActiveTruck(order);
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
        service.hasNotTooManyDrivers(order);
    }

    @Test
    public void hasNotTooManyDrivers_ReturnsTrue() {
        Order order = new Order();
        order.setTruck(new Truck());
        order.getTruck().setPeople(2);
        order.setDrivers(new ArrayList<>());
        order.getDrivers().add(new Driver());
        order.getDrivers().add(new Driver());
        boolean result = service.hasNotTooManyDrivers(order);
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
        service.hasSameLocationsTruckAndRoute(order);
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
        boolean result = service.hasSameLocationsTruckAndRoute(order);
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
        service.hasSameLocationsTruckAndDrivers(order);
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
        boolean result = service.hasSameLocationsTruckAndDrivers(order);
        Assert.assertTrue(result);
    }

    /* has shipping and delivering right order */

    @Test(expected = WrongOrderDepartureAndArrivalServiceException.class)
    public void hasShippingAndDeliveringRightOrder_ThrowsOnAbsentArrival() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, MILAN, RIGA);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        service.hasShippingAndDeliveringRightOrder(order);
    }

    @Test(expected = WrongOrderDepartureAndArrivalServiceException.class)
    public void hasShippingAndDeliveringRightOrder_ThrowsOnAbsentDeparture() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, RIGA, MILAN);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        service.hasShippingAndDeliveringRightOrder(order);
    }

    @Test(expected = WrongOrderDepartureAndArrivalServiceException.class)
    public void hasShippingAndDeliveringRightOrder_ThrowsOnWrongOrder() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, MILAN, BARCELONA);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        service.hasShippingAndDeliveringRightOrder(order);
    }

    @Test
    public void hasShippingAndDeliveringRightOrder_ReturnsTrue() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, ROME, WARSAW);
        initCargo(order, BARCELONA, MILAN);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        boolean result = service.hasShippingAndDeliveringRightOrder(order);
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
        service.hasTruckWithEnoughCapacity(order);
    }

    @Test(expected = TruckWithNotEnoughCapacityServiceException.class)
    public void hasTruckWithEnoughCapacity_ThrowsWhenTwoCargoInDifferentLocationsWithFullCrossing() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, BARCELONA, MILAN, 3.0);
        initCargo(order, ROME, WARSAW, 8.0);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        initTruck(order); // capacity = 10.0
        service.hasTruckWithEnoughCapacity(order);
    }

    @Test(expected = TruckWithNotEnoughCapacityServiceException.class)
    public void hasTruckWithEnoughCapacity_ThrowsWhenTwoCargoInSameStartLocations() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, ROME, MILAN, 3.0);
        initCargo(order, ROME, WARSAW, 8.0);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        initTruck(order); // capacity = 10.0
        service.hasTruckWithEnoughCapacity(order);
    }

    @Test(expected = TruckWithNotEnoughCapacityServiceException.class)
    public void hasTruckWithEnoughCapacity_ThrowsWhenTwoCargoInSameEndLocations() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, MILAN, WARSAW, 3.0);
        initCargo(order, BARCELONA, WARSAW, 8.0);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        initTruck(order); // capacity = 10.0
        service.hasTruckWithEnoughCapacity(order);
    }

    @Test(expected = TruckWithNotEnoughCapacityServiceException.class)
    public void hasTruckWithEnoughCapacity_ThrowsWhenTwoCargoInDifferentLocationsWithPartialCrossing() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, BARCELONA, WARSAW, 3.0);
        initCargo(order, ROME, MILAN, 8.0);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        initTruck(order); // capacity = 10.0
        service.hasTruckWithEnoughCapacity(order);
    }

    @Test
    public void hasTruckWithEnoughCapacity_ReturnsTrueWhenOneCargo() {
        Order order = new Order();
        order.setCargoes(new ArrayList<>());
        initCargo(order, BARCELONA, MILAN, 3.0);
        initRoute(order); // ROME -> BARCELONA -> MILAN -> WARSAW
        initTruck(order); // capacity = 10.0
        boolean result = service.hasTruckWithEnoughCapacity(order);
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
        boolean result = service.hasTruckWithEnoughCapacity(order);
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
        boolean result = service.hasTruckWithEnoughCapacity(order);
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
        boolean result = service.hasTruckWithEnoughCapacity(order);
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
        boolean result = service.hasTruckWithEnoughCapacity(order);
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
        boolean result = service.hasTruckWithEnoughCapacity(order);
        Assert.assertTrue(result);
    }

    /* has drivers with enough working time */

    @Test(expected = DriversWithNotEnoughWorkingTimeServiceException.class)
    public void hasDriversWithEnoughWorkingTime_ThrowsOnExceedingWorkingTimeForOneWhenMonthRestIsMore() {
        when(timeCalculator.getRestHoursOfMonth()).thenReturn(100);
        Order order = new Order();
        order.setRoute(new Route());
        order.getRoute().setDuration(50);
        order.setDrivers(new ArrayList<>());
        initDriver(order, 130);
        service.hasDriversWithEnoughWorkingTime(order);
    }

    @Test
    public void hasDriversWithEnoughWorkingTime_ReturnsTrueWhenOneAndMonthRestIsLess() {
        when(timeCalculator.getRestHoursOfMonth()).thenReturn(35);
        Order order = new Order();
        order.setRoute(new Route());
        order.getRoute().setDuration(50);
        order.setDrivers(new ArrayList<>());
        initDriver(order, 130);
        boolean result = service.hasDriversWithEnoughWorkingTime(order);
        Assert.assertTrue(result);
    }

    @Test(expected = DriversWithNotEnoughWorkingTimeServiceException.class)
    public void hasDriversWithEnoughWorkingTime_ThrowsOnExceedingWorkingTimeForTwoWhenMonthRestIsMore() {
        when(timeCalculator.getRestHoursOfMonth()).thenReturn(100);
        Order order = new Order();
        order.setRoute(new Route());
        order.getRoute().setDuration(50);
        order.setDrivers(new ArrayList<>());
        initDriver(order, 110);
        initDriver(order, 155);
        service.hasDriversWithEnoughWorkingTime(order);
    }

    @Test
    public void hasDriversWithEnoughWorkingTime_ReturnsTrueWhenTwoAndMonthRestIsLess() {
        when(timeCalculator.getRestHoursOfMonth()).thenReturn(35);
        Order order = new Order();
        order.setRoute(new Route());
        order.getRoute().setDuration(50);
        order.setDrivers(new ArrayList<>());
        initDriver(order, 110);
        initDriver(order, 155);
        boolean result = service.hasDriversWithEnoughWorkingTime(order);
        Assert.assertTrue(result);
    }

    @Test(expected = DriversWithNotEnoughWorkingTimeServiceException.class)
    public void hasDriversWithEnoughWorkingTime_ThrowsOnExceedingWorkingTimeForThreeWhenMonthRestIsMore() {
        when(timeCalculator.getRestHoursOfMonth()).thenReturn(100);
        Order order = new Order();
        order.setRoute(new Route());
        order.getRoute().setDuration(50);
        order.setDrivers(new ArrayList<>());
        initDriver(order, 110);
        initDriver(order, 161);
        initDriver(order, 85);
        service.hasDriversWithEnoughWorkingTime(order);
    }

    @Test
    public void hasDriversWithEnoughWorkingTime_ReturnsTrueWhenThreeAndMonthRestIsLess() {
        when(timeCalculator.getRestHoursOfMonth()).thenReturn(35);
        Order order = new Order();
        order.setRoute(new Route());
        order.getRoute().setDuration(50);
        order.setDrivers(new ArrayList<>());
        initDriver(order, 110);
        initDriver(order, 161);
        initDriver(order, 85);
        boolean result = service.hasDriversWithEnoughWorkingTime(order);
        Assert.assertTrue(result);
    }

    @Test
    public void hasDriversWithEnoughWorkingTime_ReturnsTrueWhenOneAndMonthRestIsMore() {
        when(timeCalculator.getRestHoursOfMonth()).thenReturn(100);
        Order order = new Order();
        order.setRoute(new Route());
        order.getRoute().setDuration(50);
        order.setDrivers(new ArrayList<>());
        initDriver(order, 110);
        boolean result = service.hasDriversWithEnoughWorkingTime(order);
        Assert.assertTrue(result);
    }

    @Test
    public void hasDriversWithEnoughWorkingTime_ReturnsTrueWhenTwoAndMonthRestIsMore() {
        when(timeCalculator.getRestHoursOfMonth()).thenReturn(100);
        Order order = new Order();
        order.setRoute(new Route());
        order.getRoute().setDuration(50);
        order.setDrivers(new ArrayList<>());
        initDriver(order, 110);
        initDriver(order, 150);
        boolean result = service.hasDriversWithEnoughWorkingTime(order);
        Assert.assertTrue(result);
    }

    @Test
    public void hasDriversWithEnoughWorkingTime_ReturnsTrueWhenThreeAndMonthRestIsMore() {
        when(timeCalculator.getRestHoursOfMonth()).thenReturn(100);
        Order order = new Order();
        order.setRoute(new Route());
        order.getRoute().setDuration(50);
        order.setDrivers(new ArrayList<>());
        initDriver(order, 110);
        initDriver(order, 160);
        initDriver(order, 85);
        boolean result = service.hasDriversWithEnoughWorkingTime(order);
        Assert.assertTrue(result);
    }

    /* initializer-s */

    private void initDriver(Order order, int hours) {
        Driver driver = new Driver();
        driver.setHours(hours);
        order.getDrivers().add(driver);
    }

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
