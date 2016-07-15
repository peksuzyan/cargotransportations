package com.tsystems.cargotransportations.service.interfaces;

import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.entity.Truck;

import java.util.List;

/**
 * Contains business-logic operations that bound with truck.
 */
public interface TruckService extends GenericService<Truck> {

    /**
     * Gets all trucks that suitable for assigning to the order.
     * @param order order
     * @return trucks list
     */
    List<Truck> getSuitableTrucksByOrder(Order order);

}
