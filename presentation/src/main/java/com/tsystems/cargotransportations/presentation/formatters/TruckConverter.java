package com.tsystems.cargotransportations.presentation.formatters;

import com.tsystems.cargotransportations.entity.Truck;
import com.tsystems.cargotransportations.service.interfaces.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TruckConverter implements Converter<String, Truck> {

    /**
     * Service instance for manipulating truck entities.
     */
    @Autowired
    TruckService truckService;

    @Override
    public Truck convert(String s) {
        System.out.println("truck converter is launched...");
        return truckService.getByNumber(s);
    }
}
