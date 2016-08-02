package com.tsystems.cargotransportations.constants.values;

/**
 * Represents validation values that met in a validation process.
 */
public final class ValidationValues {

    /**
     * Default private constructor.
     */
    private ValidationValues() {}

    /**
     * Represents cargo max weight.
     */
    public static final String CARGO_MAX_WEIGHT = "100";

    /**
     * Represents cargo min weight.
     */
    public static final String CARGO_MIN_WEIGHT = "0";

    /**
     * Represents truck max people.
     */
    public static final long TRUCK_MAX_PEOPLE = 3L;

    /**
     * Represents truck min people.
     */
    public static final long TRUCK_MIN_PEOPLE = 1L;

    /**
     * Represents truck max capacity.
     */
    public static final String TRUCK_MAX_CAPACITY = "100";

    /**
     * Represents truck min capacity.
     */
    public static final String TRUCK_MIN_CAPACITY = "50";

    /**
     * Represents truck number pattern.
     */
    public static final String TRUCK_NUMBER_PATTERN = "[a-zA-Z]{2}[\\d]{5}";

}
