package com.tsystems.cargotransportations.util;

import org.springframework.stereotype.Service;

import java.util.Calendar;

import static com.tsystems.cargotransportations.constants.mapping.ServiceMapper.TIME_CALCULATOR;
import static com.tsystems.cargotransportations.constants.values.MagicValues.HOURS_IN_DAY;

@Service(TIME_CALCULATOR)
public class TimeCalculatorImpl implements TimeCalculator {

    /**
     * Gets rest hours of month from current date.
     *
     * @return hours
     */
    public int getRestHoursOfMonth() {
        int lastDayOfMonth = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
        int currentDayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int currentHourOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return (lastDayOfMonth - currentDayOfMonth) * HOURS_IN_DAY + currentHourOfDay;
    }
}
