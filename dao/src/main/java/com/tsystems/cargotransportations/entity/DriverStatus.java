package com.tsystems.cargotransportations.entity;

/**
 * Contains all available ready statuses that can have a driver.
 */
public enum DriverStatus {
    /**
     * Status when a driver is ready to be assign an order.
     */
    FREE,

    /**
     * Status when a driver is performing an order.
     */
    BUSY,

    /**
     * Status when a driver is relaxing after performing of an order.
     */
    RELAX
}
