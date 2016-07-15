package com.tsystems.cargotransportations.service.interfaces;

import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.entity.Route;

import java.util.List;
import java.util.Set;

/**
 * Contains business-logic operations that bound with route.
 */
public interface RouteService extends GenericService<Route>  {

    /**
     * Gets routes for given order.
     * @param order order
     * @return routes list
     */
    List<Route> getSuitableRoutesByOrder(Order order);

}
