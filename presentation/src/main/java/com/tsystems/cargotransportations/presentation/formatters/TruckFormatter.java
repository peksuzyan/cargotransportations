package com.tsystems.cargotransportations.presentation.formatters;

import com.tsystems.cargotransportations.entity.Truck;
import com.tsystems.cargotransportations.service.interfaces.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

/**
 * Formatter determines a way to print and to parse object.
 */
@Component
public class TruckFormatter implements Formatter<Truck> {

    /**
     * Service instance for manipulating truck entities.
     */
    @Autowired
    TruckService truckService;

    /**
     * Converts truck number to a truck object.
     * @param truckNumber truck number
     * @param locale locale
     * @return truck
     * @throws ParseException
     */
    @Override
    public Truck parse(String truckNumber, Locale locale) throws ParseException {
        System.out.println("formatter parse ....");
        if (truckNumber == null || truckNumber.equals("")) return new Truck();
        return truckService.getByNumber(truckNumber);
    }

    /**
     * Converts truck object to a truck number.
     * @param truck truck
     * @param locale locale
     * @return truck number
     */
    @Override
    public String print(Truck truck, Locale locale) {
        System.out.println("formatter print ....");
        return truck.getNumber();
    }
}
