package com.tsystems.cargotransportations.util;

/**
 * Contains business-logic operations that bound with time.
 */
public interface TimeCalculator {

    /**
     * Gets rest hours of month from current date.
     *
     * @return hours
     */
    int getRestHoursOfMonth();
}
