package com.tsystems.cargotransportations.constant;

/**
 * Contains all messages are performed by known reasons/exceptions.
 */
public class MessageConstant {
    /**
     * String representation of driver entity.
     */
    private static final String DRIVER = "driver";

    /**
     * String representation of truck entity.
     */
    private static final String TRUCK = "truck";

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

}
