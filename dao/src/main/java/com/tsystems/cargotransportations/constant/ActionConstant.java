package com.tsystems.cargotransportations.constant;

/**
 * Contains all kinds of client operations given by action param.
 */
public class ActionConstant {
    /**
     * Client needs to refresh entities list.
     */
    public static final String REFRESH_ACTION = "refresh";

    /**
     * Client needs to create a new entity.
     */
    public static final String ADD_ACTION = "add";

    /**
     * Client needs to perform a new entity creating.
     */
    public static final String PERFORM_ADDING_ACTION = "perform_adding";

    /**
     * Client needs to edit an entity.
     */
    public static final String EDIT_ACTION = "edit";

    /**
     * Client needs to perform an entity editing.
     */
    public static final String PERFORM_EDITING_ACTION = "perform_editing";

    /**
     * Client needs to delete an entity.
     */
    public static final String DELETE_ACTION = "delete";
}
