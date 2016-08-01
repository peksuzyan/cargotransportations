package com.tsystems.cargotransportations.constants;

/**
 * Contains all localize properties codes for business-operations.
 */
public final class ExceptionCodes {

    /**
     * Default private constructor.
     */
    public ExceptionCodes() {}

    /**
     * Localization code for processes when cargo is already shipped.
     */
    public static final String CARGO_ALREADY_SHIPPED = "exception_cargo_shipped";

    /**
     * Localization code for processes when cargo is already delivered.
     */
    public static final String CARGO_ALREADY_DELIVERED = "exception_cargo_delivered";

    /**
     * Localization code for processes when driver is busy.
     */
    public static final String DRIVER_IS_BUSY = "exception_driver_busy";

    /**
     * Localization code for processes when order is performing.
     */
    public static final String ORDER_IS_PERFORMING = "exception_order_performing";

    /**
     * Localization code for processes when order is done.
     */
    public static final String ORDER_IS_DONE = "exception_order_done";

    /**
     * Localization code for processes when order isn't exist.
     */
    public static final String ORDER_NOT_EXIST = "exception_order_not_exist";

    /**
     * Localization code for processes when order has null entity in one of him collections.
     */
    public static final String ORDER_HAS_NULL_ENTITY = "exception_order_has_null_entity";

    /**
     * Localization code for processes when order without assigned truck.
     */
    public static final String ORDER_WITHOUT_TRUCK = "exception_order_without_truck";

    /**
     * Localization code for processes when order without assigned route.
     */
    public static final String ORDER_WITHOUT_ROUTE = "exception_order_without_route";

    /**
     * Localization code for processes when order without added cargoes.
     */
    public static final String ORDER_WITHOUT_CARGOES = "exception_order_without_cargoes";

    /**
     * Localization code for processes when order without added drivers.
     */
    public static final String ORDER_WITHOUT_DRIVERS = "exception_order_without_drivers";

    /**
     * Localization code for processes when order has inactive truck.
     */
    public static final String ORDER_TRUCK_INACTIVE = "exception_order_truck_inactive";

    /**
     * Localization code for processes when order has no prepared cargoes.
     */
    public static final String ORDER_CARGOES_NOT_PREPARE = "exception_cargoes_not_prepared";

    /**
     * Localization code for processes when order has drivers no free.
     */
    public static final String ORDER_DRIVERS_NOT_FREE = "exception_drivers_not_free";

    /**
     * Localization code for processes when order has too many drivers.
     */
    public static final String ORDER_TOO_MANY_DRIVERS = "exception_too_many_drivers";

    /**
     * Localization code for processes when truck and start point of route have different locations.
     */
    public static final String ORDER_DIFFERENT_LOCATIONS_TRUCK_AND_ROUTE
            = "exception_different_location_truck_and_route";

    /**
     * Localization code for processes when truck and at least one driver have different locations.
     */
    public static final String ORDER_DIFFERENT_LOCATIONS_TRUCK_AND_DRIVERS
            = "exception_different_location_truck_and_drivers";

    /**
     * Localization code for processes when order has at least one cargo with wrong
     * departure and arrival order in this route.
     */
    public static final String ORDER_WRONG_DEPARTURE_AND_ARRIVAL_ORDER
            = "exception_wrong_departure_and_arrival_order";

    /**
     * Localization code for processes when order has truck with not enough capacity.
     */
    public static final String ORDER_TRUCK_WITH_NOT_ENOUGH_CAPACITY
            = "exception_truck_with_not_enough_capacity";

    /**
     * Localization code for processes when order has drivers with not enough working time.
     */
    public static final String ORDER_DRIVERS_WITH_NOT_ENOUGH_WORKING_TIME
            = "exception_drivers_with_not_enough_working_time";

}
