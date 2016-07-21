package com.tsystems.cargotransportations.presentation.grids;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Container is needed for transfer of an order details to remote client.
 */
public class OrderDetails implements Serializable {

    /**
     * ID of an actual order.
     */
    private int orderId;

    /**
     * Number of an actual truck.
     */
    private String truckNumber;

    /**
     * Drivers email and full name.
     */
    private Map<String, String> drivers;

    /**
     * Cargoes ID and destination point.
     */
    private Map<Integer, String> cargoes;

    /**
     * Route points of an actual order.
     */
    private List<String> routePoints;

    /**
     * Default constructor.
     */
    public OrderDetails() {}

    /**
     * Gets orderId.
     *
     * @return orderId orderId
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Sets orderId.
     *
     * @param orderId orderId
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets truckNumber.
     *
     * @return truckNumber truckNumber
     */
    public String getTruckNumber() {
        return truckNumber;
    }

    /**
     * Sets truckNumber.
     *
     * @param truckNumber truckNumber
     */
    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
    }

    /**
     * Gets drivers.
     *
     * @return drivers drivers
     */
    public Map<String, String> getDrivers() {
        return drivers;
    }

    /**
     * Sets drivers.
     *
     * @param drivers drivers
     */
    public void setDrivers(Map<String, String> drivers) {
        this.drivers = drivers;
    }

    /**
     * Gets cargoes.
     *
     * @return cargoes cargoes
     */
    public Map<Integer, String> getCargoes() {
        return cargoes;
    }

    /**
     * Sets cargoes.
     *
     * @param cargoes cargoes
     */
    public void setCargoes(Map<Integer, String> cargoes) {
        this.cargoes = cargoes;
    }

    /**
     * Gets routePoints.
     *
     * @return routePoints routePoints
     */
    public List<String> getRoutePoints() {
        return routePoints;
    }

    /**
     * Sets routePoints.
     *
     * @param routePoints routePoints
     */
    public void setRoutePoints(List<String> routePoints) {
        this.routePoints = routePoints;
    }
}
