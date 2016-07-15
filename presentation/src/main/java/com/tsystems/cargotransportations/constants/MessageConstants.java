package com.tsystems.cargotransportations.constants;

/**
 * Contains all messages are performed by known reasons/exceptions.
 */
public class MessageConstants {

    /**
     * Message represents when user didn't pass an authentication.
     */
    public static final String WRONG_USERNAME_OR_PASSWORD = "Either user name or password is wrong.";

    /**
     * Message represents when permission to resource denied.
     */
    public static final String PERMISSION_DENIED = "The resource is unreachable - permission denied.";

    /* <-----  Codes of Properties ----->*/

    /**
     * Represents code of property-file when process is success.
     */
    public static final String CODE_SUCCESS = "success";
    /**
     * Represents code of property-file when process is failure.
     */
    public static final String CODE_FAILURE = "failure";
    /**
     * Represents code of property-file when process is passed.
     */
    public static final String CODE_PASSED = "passed";
    /**
     * Represents code of property-file when process is error.
     */
    public static final String CODE_ERROR = "error";

    /* CARGO */

    /**
     * Represents code of property-file when cargo editing is success.
     */
    public static final String CODE_CARGO_EDIT_SUCCESS = "cargo_edit_success";
    /**
     * Represents code of property-file when cargo editing is error.
     */
    public static final String CODE_CARGO_EDIT_ERROR = "cargo_edit_error";
    /**
     * Represents code of property-file when cargo adding is success.
     */
    public static final String CODE_CARGO_ADD_SUCCESS = "cargo_add_success";
    /**
     * Represents code of property-file when cargo adding is error.
     */
    public static final String CODE_CARGO_ADD_ERROR = "cargo_add_error";
    /**
     * Represents code of property-file when cargo deleting is success.
     */
    public static final String CODE_CARGO_DELETE_SUCCESS = "cargo_delete_success";

    /* DRIVER */

    /**
     * Represents code of property-file when driver editing is error.
     */
    public static final String CODE_DRIVER_EDIT_ERROR = "driver_edit_error";
    /**
     * Represents code of property-file when driver editing is success.
     */
    public static final String CODE_DRIVER_EDIT_SUCCESS = "driver_edit_success";
    /**
     * Represents code of property-file when driver deleting is error.
     */
    public static final String CODE_DRIVER_DELETE_ERROR = "driver_delete_error";
    /**
     * Represents code of property-file when driver deleting is success.
     */
    public static final String CODE_DRIVER_DELETE_SUCCESS = "driver_delete_success";
    /**
     * Represents code of property-file when driver adding is error.
     */
    public static final String CODE_DRIVER_ADD_ERROR = "driver_add_error";
    /**
     * Represents code of property-file when driver adding is success.
     */
    public static final String CODE_DRIVER_ADD_SUCCESS = "driver_add_success";

}
