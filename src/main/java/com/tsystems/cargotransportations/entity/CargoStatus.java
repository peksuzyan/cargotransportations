package com.tsystems.cargotransportations.entity;

/**
 * Contains all available delivery statuses that can have a cargo.
 */
public enum CargoStatus {
    /**
     * Status when a cargo is ready to be include an order.
     */
    PREPARED,

    /**
     * Status when a cargo is shipped to a truck.
     */
    SHIPPED,

    /**
     * Status when a cargo is delivered to destination.
     */
    DELIVERED
}
