package com.tsystems.cargotransportations.constants;

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
     * Client needs to delete an entity.
     */
    public static final String DELETE_ACTION = "delete";

    /**
     * Client needs to check an entity.
     */
    public static final String CHECK_ACTION = "check";

    /**
     * Provide verifying an entity.
     */
    public static final String VERIFY_ACTION = "verify";

    /**
     * Client needs to clear an entity.
     */
    public static final String CLEAR_ACTION = "clear";

    /**
     * User needs to edit own profile.
     */
    public static final String USER_EDIT_ACTION = "user_edit";

    /**
     * User needs to show assignments for an order.
     */
    public static final String SHOW_ASSIGNMENTS_ACTION = "show_assignments";

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
     * Client needs to perform a route assigning to an entity.
     */
    public static final String PERFORM_ROUTE_ASSIGNING_ACTION = "perform_route_assigning";

    /**
     * Client needs to perform a route refusing from an entity.
     */
    public static final String PERFORM_ROUTE_REFUSING_ACTION = "perform_route_refusing";

    /**
     * Client needs to perform an order processing.
     */
    public static final String PERFORM_ORDER_PROCESSING_ACTION = "perform_order_processing";

    /**
     * User needs to perform editing of own profile.
     */
    public static final String PERFORM_USER_EDITING_ACTION = "perform_user_editing";

    /**
     * Client doesn't need to do anything with an entity.
     */
    public static final String NOTHING_ACTION = "";
}
