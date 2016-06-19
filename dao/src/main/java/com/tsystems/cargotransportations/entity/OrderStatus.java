package com.tsystems.cargotransportations.entity;

/**
 * Contains all available ready statuses that can have an order.
 */
public enum OrderStatus {
    /**
     * Status when an order is ready to performing.
     */
    OPEN,

    /**
     * Status when an order is performing.
     */
    PERFORMING,

    /**
     * Status when an order is performed.
     */
    DONE
}
