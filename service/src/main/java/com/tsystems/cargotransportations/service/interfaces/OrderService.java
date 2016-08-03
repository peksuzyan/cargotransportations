package com.tsystems.cargotransportations.service.interfaces;

import com.tsystems.cargotransportations.entity.*;
import com.tsystems.cargotransportations.util.TimeCalculator;

import java.util.List;

/**
 * Contains business-logic operations that bound with order.
 */
public interface OrderService extends GenericService<Order> {

    /**
     * Gets an order by given status and truck.
     *
     * @param status order status
     * @param truck  truck
     * @return order
     */
    Order getByStatusAndTruck(OrderStatus status, Truck truck);

    /**
     * Checks whether order is ready to modifying or not in accordance to a business-logic.
     *
     * @param order order
     */
    boolean isReadyToModifying(Order order);

    /**
     * Checks that order is ready to performing in according with business-logic.
     *
     * @param order order
     */
    boolean isReadyToPerforming(Order order);

    /**
     * Checks whether order is ready to deleting or not in accordance to a business-logic.
     *
     * @param order order
     */
    void checkAndDelete(Order order);

    /**
     * Creates order after filling all needed fields.
     *
     * @param orderId orderId
     */
    void createOrder(int orderId);

    /**
     * Adds cargo by id to a given order.
     *
     * @param orderId order id
     * @param cargoId cargo id
     * @return is added or not
     */
    boolean addCargoById(int orderId, int cargoId);

    /**
     * Clears cargoes list of a given order.
     *
     * @param orderId orderId
     * @return is cleared or not
     */
    boolean clearCargoes(int orderId);

    /**
     * Assigns truck by id to a given order.
     *
     * @param orderId orderId
     * @param truckId truckId
     * @return is assigned or not
     */
    boolean assignCargoById(int orderId, int truckId);

    /**
     * Rejects truck from a given order.
     *
     * @param orderId orderId
     * @return is rejected or not
     */
    boolean rejectTruck(int orderId);

    /**
     * Adds driver by id to a given order.
     *
     * @param orderId  orderId
     * @param driverId driverId
     * @return is added or not
     */
    boolean addDriverById(int orderId, int driverId);

    /**
     * Clears drivers list of a given order.
     *
     * @param orderId orderId
     * @return is cleared or not
     */
    boolean clearDrivers(int orderId);

    /**
     * Assigns route by id to a given order.
     *
     * @param orderId orderId
     * @param routeId routeId
     * @return is assigned or not
     */
    boolean assignRoute(int orderId, int routeId);

    /**
     * Rejects route from a given order.
     *
     * @param orderId orderId
     * @return is rejected or not
     */
    boolean rejectRoute(int orderId);

    /**
     * Returns a list of cargoes by suitable conditions.
     *
     * @return cargoes list
     */
    List<Cargo> getSuitableCargoes();

    /**
     * Returns a list of drivers by suitable conditions.
     *
     * @return drivers list
     */
    List<Driver> getSuitableDrivers();

    /**
     * Returns a list of trucks by suitable conditions.
     * @return trucks list
     */
    List<Truck> getSuitableTrucks();

    /**
     * Returns a list of routes by suitable conditions.
     * @return routes list
     */
    List<Route> getSuitableRoutes();

    /**
     * Returns a truck of the order by a given driver.
     * @param driver driver
     * @return truck
     */
    Truck getTruckByDriver(Driver driver);
}
