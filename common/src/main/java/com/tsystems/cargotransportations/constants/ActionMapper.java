package com.tsystems.cargotransportations.constants;

/**
 * Contains all kinds of client operations given by action param.
 */
public final class ActionMapper {

    /**
     * Default private constructor.
     */
    private ActionMapper() {}

    /**
     * Client needs to create a new entity.
     */
    public static final String ADD_ACTION = "add";

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
}
