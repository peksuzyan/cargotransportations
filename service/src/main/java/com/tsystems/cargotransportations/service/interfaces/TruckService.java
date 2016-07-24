package com.tsystems.cargotransportations.service.interfaces;

import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.entity.Truck;

import java.util.List;

/**
 * Contains business-logic operations that bound with truck.
 */
public interface TruckService extends GenericService<Truck> {

    /**
     * Gets truck by given number.
     * @param number truck number
     * @return truck
     */
    Truck getByNumber(String number);

}
