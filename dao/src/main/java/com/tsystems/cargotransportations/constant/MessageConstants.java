package com.tsystems.cargotransportations.constant;

/**
 * Contains all messages are performed by known reasons/exceptions.
 */
public class MessageConstants {
    /**
     * String representation of driver classname.
     */
    private static final String DRIVER = "driver";

    /**
     * String representation of truck classname.
     */
    private static final String TRUCK = "truck";

    /**
     * String representation of cargo classname.
     */
    private static final String CARGO = "cargo";

    /**
     * String representation of order classname.
     */
    private static final String ORDER = "order";

    /**
     * String representation of route classname.
     */
    private static final String ROUTE = "route";

    /**
     * Pattern for representing of success messages after operations over the entity.
     */
    private static final String OPERATION_OVER_ENTITY = "This %s is %s successfully!";

    /**
     * Pattern for representing of error message when the entity is not found.
     */
    private static final String ENTITY_IS_NOT_FOUND = "This %s is not found!";

    /**
     * Message represents when driver is created.
     */
    public static final String DRIVER_IS_CREATED = String.format(OPERATION_OVER_ENTITY, DRIVER, "created");

    /**
     * Message represents when driver is edited.
     */
    public static final String DRIVER_IS_EDITED = String.format(OPERATION_OVER_ENTITY, DRIVER, "edited");

    /**
     * Message represents when driver is deleted.
     */
    public static final String DRIVER_IS_DELETED = String.format(OPERATION_OVER_ENTITY, DRIVER, "deleted");

    /**
     * Message represents when driver is not found.
     */
    public static final String DRIVER_IS_NOT_FOUND = String.format(ENTITY_IS_NOT_FOUND, DRIVER);

    /**
     * Message represents when cargo is created.
     */
    public static final String CARGO_IS_CREATED = String.format(OPERATION_OVER_ENTITY, CARGO, "created");

    /**
     * Message represents when cargo is edited.
     */
    public static final String CARGO_IS_EDITED = String.format(OPERATION_OVER_ENTITY, CARGO, "edited");

    /**
     * Message represents when cargo is deleted.
     */
    public static final String CARGO_IS_DELETED = String.format(OPERATION_OVER_ENTITY, CARGO, "deleted");

    /**
     * Message represents when cargo is not found.
     */
    public static final String CARGO_IS_NOT_FOUND = String.format(ENTITY_IS_NOT_FOUND, CARGO);

    /**
     * Message represents when order is created.
     */
    public static final String ORDER_IS_CREATED = String.format(OPERATION_OVER_ENTITY, ORDER, "created");

    /**
     * Message represents when order is deleted.
     */
    public static final String ORDER_IS_DELETED = String.format(OPERATION_OVER_ENTITY, ORDER, "deleted");

    /**
     * Message represents when order is not found.
     */
    public static final String ORDER_IS_NOT_FOUND = String.format(ENTITY_IS_NOT_FOUND, ORDER);

    /**
     * Message represents when order is sent to performing.
     */
    public static final String ORDER_IS_PERFORMING = "Order is sent to performing!";

    /**
     * Message represents when route is created.
     */
    public static final String ROUTE_IS_CREATED = String.format(OPERATION_OVER_ENTITY, ROUTE, "created");

    /**
     * Message represents when route is deleted.
     */
    public static final String ROUTE_IS_DELETED = String.format(OPERATION_OVER_ENTITY, ROUTE, "deleted");

    /**
     * Message represents when this cities count isn't existed.
     */
    public static final String CITIES_COUNT_IS_NOT_EXISTED = "This cities count isn't existed!";

    /**
     * Message represents when route is not found.
     */
    public static final String ROUTE_IS_NOT_FOUND = String.format(ENTITY_IS_NOT_FOUND, ROUTE);

    /**
     * Message represents when truck is created.
     */
    public static final String TRUCK_IS_CREATED = String.format(OPERATION_OVER_ENTITY, TRUCK, "created");

    /**
     * Message represents when truck is edited.
     */
    public static final String TRUCK_IS_EDITED = String.format(OPERATION_OVER_ENTITY, TRUCK, "edited");

    /**
     * Message represents when truck is deleted.
     */
    public static final String TRUCK_IS_DELETED = String.format(OPERATION_OVER_ENTITY, TRUCK, "deleted");

    /**
     * Message represents when passed data aren't correct.
     */
    public static final String DATA_ARE_NOT_CORRECT = "Passed data aren't correct!";

    /**
     * Message represents when passed data aren't correct.
     */
    public static final String ACTION_IS_NOT_EXISTED = "The action isn't existed!";

    /**
     * Message represents when user didn't pass an authentication.
     */
    public static final String WRONG_USERNAME_OR_PASSWORD = "Either user name or password is wrong.";

}
