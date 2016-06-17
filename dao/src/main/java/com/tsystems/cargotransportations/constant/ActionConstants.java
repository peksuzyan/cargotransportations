package com.tsystems.cargotransportations.constant;

/**
 * Contains all kinds of client operations given by action param.
 */
public class ActionConstants {
    /**
     * Client needs to refresh entities list.
     */
    public static final String REFRESH_ACTION = "refresh";

    /**
     * Client needs to create a new entity.
     */
    public static final String ADD_ACTION = "add";

    /**
     * Client needs to edit an entity.
     */
    public static final String EDIT_ACTION = "edit";

    /**
     * Client needs to perform a new entity creating.
     */
    public static final String PERFORM_ADDING_ACTION = "perform_adding";

    /**
     * Client needs to perform an entity editing.
     */
    public static final String PERFORM_EDITING_ACTION = "perform_editing";

    /**
     * Client needs to delete an entity.
     */
    public static final String PERFORM_DELETING_ACTION = "perform_deleting";

    /**
     * Client needs to perform a driver adding to an entity.
     */
    public static final String PERFORM_DRIVER_ADDING_ACTION = "perform_driver_adding";

    /**
     * Client needs to perform a cargo adding to an entity.
     */
    public static final String PERFORM_CARGO_ADDING_ACTION = "perform_cargo_adding";

    /**
     * Client needs to perform a driver excluding from an entity.
     */
    public static final String PERFORM_DRIVER_EXCLUDING_ACTION = "perform_driver_excluding";

    /**
     * Client needs to perform a cargo excluding from an entity.
     */
    public static final String PERFORM_CARGO_EXCLUDING_ACTION = "perform_cargo_excluding";

    /**
     * Client needs to perform a truck assigning to an entity.
     */
    public static final String PERFORM_TRUCK_ASSIGNING_ACTION = "perform_truck_assigning";

    /**
     * Client needs to perform a truck refusing from an entity.
     */
    public static final String PERFORM_TRUCK_REFUSING_ACTION = "perform_truck_refusing";

    /**
     * Client needs to perform an order processing.
     */
    public static final String PERFORM_ORDER_PROCESSING_ACTION = "perform_order_processing";

    /**
     * Client doesn't need to do anything with an entity.
     */
    public static final String NOTHING_ACTION = "";
}
