package com.tsystems.cargotransportations.util;

/**
 * It's a helper tool that validates different input data and return a result of analysis.
 */
public class Validator {
    /**
     * Validates input data that contains of only digits by given pattern.
     * @return passed validating or not
     */
    public static boolean isPositiveIntegerNumber(String input) {
        return input.matches("\\d+");
    }

    /**
     * Validates input data that contains of digits and dot symbol by given pattern.
     * @return passed validating or not
     */
    public static boolean isPositiveDoubleNumber(String input) {
        return input.matches("\\d+(\\.\\d+)?");
    }

    /**
     * Validates input data that contains of 2 first letters and 5 last digits by given pattern.
     * @return passed validating or not
     */
    public static boolean isTruckNumber(String input) {
        return input.matches("[a-zA-Z]{2}[\\d]{5}");
    }
}
