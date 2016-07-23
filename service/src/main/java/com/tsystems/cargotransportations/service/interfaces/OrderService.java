package com.tsystems.cargotransportations.service.interfaces;

import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.entity.OrderStatus;
import com.tsystems.cargotransportations.entity.Truck;

import java.util.List;

/**
 * Contains business-logic operations that bound with order.
 */
public interface OrderService extends GenericService<Order>  {

    /**
     * Gets an order by given status and truck.
     * @param status order status
     * @param truck truck
     * @return order
     */
    Order getByStatusAndTruck(OrderStatus status, Truck truck);

    /**
     * Checks whether order is ready to modifying or not in accordance to a business-logic.
     * @param order order
     */
    boolean isReadyToModifying(Order order);

    /**
     * Checks whether order is ready to deleting or not in accordance to a business-logic.
     * @param order order
     */
    void checkAndDelete(Order order);

}
