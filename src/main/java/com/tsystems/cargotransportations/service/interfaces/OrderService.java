package com.tsystems.cargotransportations.service.interfaces;

import com.tsystems.cargotransportations.entity.Order;

import java.util.List;

/**
 * Contains business-logic operations that bound with order.
 */
public interface OrderService {
    /**
     * Gets order by number.
     * @param orderNumber order number
     * @return order
     */
    Order getByNumber(int orderNumber);

    /**
     * Deletes order by number.
     * @param orderNumber order number
     */
    void deleteByNumber(int orderNumber);

    /**
     * Creates a new order.
     */
    void createOrder();

    /**
     * Adds a cargo by number to list of cargoes in an order.
     * @param cargoNumber cargo number
     */
    void addCargoByNumber(int orderNumber, int cargoNumber);

    /**
     * Adds a driver by number to list of drivers in an order.
     * @param driverNumber driver number
     */
    void addDriverByNumber(int orderNumber, int driverNumber);

    /**
     * Assigns a truck by number for an order.
     * @param truckNumber truck number
     */
    void assignTruckByNumber(int orderNumber, String truckNumber);

    /**
     * Assigns to an order a route by number.
     * @param orderNumber order number
     * @param routeNumber route number
     */
    void assignRouteByNumber(int orderNumber, int routeNumber);

    /**
     * Assigns to an order a route by route points.
     * @param orderNumber order number
     * @param routePoints route points
     */
    void assignRouteByRoutePoints(int orderNumber, List<String> routePoints);

    /**
     * Excludes a cargo from a cargoes list of an order.
     * @param cargoNumber cargo number
     */
    void excludeCargoByNumber(int orderNumber, int cargoNumber);

    /**
     * Excludes a driver from a drivers list of an order.
     * @param driverNumber driver number
     */
    void excludeDriverByNumber(int orderNumber, int driverNumber);

    /**
     * Refuses truck from an order.
     * @param orderNumber order number
     */
    void refuseTruck(int orderNumber);

    /**
     * Refuses route from an order.
     * @param orderNumber order number
     */
    void refuseRoute(int orderNumber);

    /**
     * Gets a list of all existing orders.
     * @return orders list
     */
    List<Order> getAllOrders();

    /**
     * Sends an order to performing by drivers.
     * @param orderNumber order number
     */
    void sendOrderToPerforming(int orderNumber);

    /**
     * Gets order with status PERFORMING by unique driver number.
     * @param driverNumber driver number
     * @return order
     */
    Order getPerformingOrderByDriverNumber(int driverNumber);
}
