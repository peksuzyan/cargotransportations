package com.tsystems.cargotransportations.entity;

/**
 * Contains all available ready statuses that can have a driver.
 */
public enum DriverStatus {

    /**
     * Represents status when a driver is ready to be assign an order.
     */
    FREE,

    /**
     * Represents status when a driver is assigned to an order.
     */
    ASSIGNED,

    /**
     * Represents status when a driver is performing an order and drives a truck.
     */
    BUSY,

    /**
     * Represents status when a driver is performing an order and rests in a way.
     */
    REST,

    /**
     * Represents status when a driver is relaxing after performing of an order.
     */
    RELAX
}
