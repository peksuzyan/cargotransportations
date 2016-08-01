package com.tsystems.cargotransportations.constants;

/**
 * Represents validation message codes for localization properties.
 */
public final class ValidationCodes {

    /**
     * Default private constructor.
     */
    public ValidationCodes() {}

    /* CARGO ENTITY */

    /**
     * Represents validation code when cargo name is empty.
     */
    public static final String CARGO_NAME_IS_EMPTY = "{validation_cargo_name_NotEmpty}";

    /**
     * Represents validation code when cargo departure city is empty.
     */
    public static final String CARGO_DEPARTURE_CITY_IS_EMPTY = "{validation_cargo_departure_NotEmpty}";

    /**
     * Represents validation code when cargo arrival city is empty.
     */
    public static final String CARGO_ARRIVAL_CITY_IS_EMPTY = "{validation_cargo_arrival_NotEmpty}";

    /**
     * Represents validation code when cargo weight is more max.
     */
    public static final String CARGO_WEIGHT_IS_MORE_MAX = "{validation_cargo_weight_DecimalMax}";

    /**
     * Represents validation code when cargo weight is less min.
     */
    public static final String CARGO_WEIGHT_IS_LESS_MIN = "{validation_cargo_weight_DecimalMin}";

    /* DRIVER ENTITY */

    /**
     * Represents validation code when driver email is empty.
     */
    public static final String DRIVER_EMAIL_IS_EMPTY = "{validation_driver_email_NotEmpty}";

    /**
     * Represents validation code when driver email isn't email.
     */
    public static final String DRIVER_EMAIL_IS_NOT_EMAIL = "{validation_driver_email_Email}";

    /**
     * Represents validation code when driver first name is empty.
     */
    public static final String DRIVER_FIRST_NAME_IS_EMPTY = "{validation_driver_first_NotEmpty}";

    /**
     * Represents validation code when driver last name is empty.
     */
    public static final String DRIVER_LAST_NAME_IS_EMPTY = "{validation_driver_last_NotEmpty}";

    /**
     * Represents validation code when driver city is empty.
     */
    public static final String DRIVER_CITY_IS_EMPTY = "{validation_driver_city_NotEmpty}";

    /* USER ENTITY */

    /**
     * Represents validation code when user email is empty.
     */
    public static final String USER_EMAIL_IS_EMPTY = "{validation_user_email_NotEmpty}";

    /**
     * Represents validation code when user email isn't email.
     */
    public static final String USER_EMAIL_IS_NOT_EMAIL = "{validation_user_email_Email}";

    /**
     * Represents validation code when user password is empty.
     */
    public static final String USER_PASSWORD_IS_EMPTY = "{validation_user_password_NotEmpty}";

    /* TRUCK ENTITY */

    /**
     * Represents validation code when truck people is more max.
     */
    public static final String TRUCK_PEOPLE_IS_MORE_MAX = "{validation_truck_people_Max}";

    /**
     * Represents validation code when truck people is less min.
     */
    public static final String TRUCK_PEOPLE_IS_LESS_MIN = "{validation_truck_people_Min}";

    /**
     * Represents validation code when truck capacity is more max.
     */
    public static final String TRUCK_CAPACITY_IS_MORE_MAX = "{validation_truck_capacity_DecimalMax}";

    /**
     * Represents validation code when truck capacity is less min.
     */
    public static final String TRUCK_CAPACITY_IS_LESS_MIN = "{validation_truck_capacity_DecimalMin}";

    /**
     * Represents validation code when truck capacity is less min.
     */
    public static final String TRUCK_CITY_IS_EMPTY = "{validation_truck_city_NotEmpty}";

    /**
     * Represents validation code when truck number pattern don't matches with pattern.
     */
    public static final String TRUCK_NUMBER_NOT_MATCH_PATTERN = "{validation_truck_number_Pattern}";

}