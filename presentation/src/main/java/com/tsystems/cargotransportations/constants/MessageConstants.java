package com.tsystems.cargotransportations.constants;

/**
 * Contains all messages are performed by known reasons/exceptions.
 */
public class MessageConstants {

    /* <-----  Security Messages ----->*/

    /**
     * Message represents when user didn't pass an authentication.
     */
    public static final String SECURITY_WRONG_CREDENTIALS = "security_wrong_credentials";

    /**
     * Message represents when permission to resource denied.
     */
    public static final String SECURITY_PERMISSION_DENIED = "security_permission_denied";

    /**
     * Message represents when user sign out.
     */
    public static final String SECURITY_LOGOUT = "security_logout";

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
    /**
     * Represents code of property-file when process is success passed.
     */
    public static final String CODE_OPERATION_SUCCESS = "operation_success";
    /**
     * Represents code of property-file when process isn't passed.
     */
    public static final String CODE_OPERATION_ERROR = "operation_error";

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

    /* TRUCK */

    /**
     * Represents code of property-file when truck editing is error.
     */
    public static final String CODE_TRUCK_EDIT_ERROR = "truck_edit_error";
    /**
     * Represents code of property-file when truck editing is success.
     */
    public static final String CODE_TRUCK_EDIT_SUCCESS = "truck_edit_success";
    /**
     * Represents code of property-file when truck deleting is error.
     */
    public static final String CODE_TRUCK_DELETE_ERROR = "truck_delete_error";
    /**
     * Represents code of property-file when truck deleting is success.
     */
    public static final String CODE_TRUCK_DELETE_SUCCESS = "truck_delete_success";
    /**
     * Represents code of property-file when truck adding is error.
     */
    public static final String CODE_TRUCK_ADD_ERROR = "truck_add_error";
    /**
     * Represents code of property-file when truck adding is success.
     */
    public static final String CODE_TRUCK_ADD_SUCCESS = "truck_add_success";

    /* USER */

    /**
     * Represents code of property-file when user editing is error.
     */
    public static final String CODE_USER_EDIT_ERROR = "user_edit_error";
    /**
     * Represents code of property-file when user editing is success.
     */
    public static final String CODE_USER_EDIT_SUCCESS = "user_edit_success";
    /**
     * Represents code of property-file when user deleting is error.
     */
    public static final String CODE_USER_DELETE_ERROR = "user_delete_error";
    /**
     * Represents code of property-file when user deleting is success.
     */
    public static final String CODE_USER_DELETE_SUCCESS = "user_delete_success";
    /**
     * Represents code of property-file when user adding is error.
     */
    public static final String CODE_USER_ADD_ERROR = "user_add_error";
    /**
     * Represents code of property-file when user adding is success.
     */
    public static final String CODE_USER_ADD_SUCCESS = "user_add_success";

     /* ROUTE */

    /**
     * Represents code of property-file when route adding is error.
     */
    public static final String CODE_ROUTE_ADD_ERROR = "route_add_error";
    /**
     * Represents code of property-file when route adding is success.
     */
    public static final String CODE_ROUTE_ADD_SUCCESS = "route_add_success";

    /* ORDER */

    /**
     * Represents code of property-file when order editing is success.
     */
    public static final String CODE_ORDER_EDIT_SUCCESS = "order_edit_success";
    /**
     * Represents code of property-file when order editing is error.
     */
    public static final String CODE_ORDER_EDIT_ERROR = "order_edit_error";
    /**
     * Represents code of property-file when order adding is success.
     */
    public static final String CODE_ORDER_ADD_SUCCESS = "order_add_success";
    /**
     * Represents code of property-file when order adding is error.
     */
    public static final String CODE_ORDER_ADD_ERROR = "order_add_error";
    /**
     * Represents code of property-file when order deleting is success.
     */
    public static final String CODE_ORDER_DELETE_SUCCESS = "order_delete_success";

    /**
     * Represents code of property-file when adding cargo to an order is passed successfully.
     */
    public static final String CODE_ORDER_ADD_CARGO_PASSED = "order_add_cargo_passed";
    /**
     * Represents code of property-file when adding cargo to an order is passed unsuccessfully.
     */
    public static final String CODE_ORDER_ADD_CARGO_FAILURE = "order_add_cargo_failure";
    /**
     * Represents code of property-file when clearing cargoes list of an order is passed successfully.
     */
    public static final String CODE_ORDER_CLEAR_CARGO_PASSED = "order_clear_cargo_passed";
    /**
     * Represents code of property-file when clearing cargoes list of an order is passed unsuccessfully.
     */
    public static final String CODE_ORDER_CLEAR_CARGO_FAILURE = "order_clear_cargo_failure";


}
