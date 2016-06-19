package com.tsystems.cargotransportations.service;

import com.tsystems.cargotransportations.entity.Route;

import java.util.List;

/**
 * Contains business-logic operations that bound with route.
 */
public interface RouteService {
    /**
     * Gets route by number.
     * @param routeNumber route number
     * @return route
     */
    Route getByNumber(int routeNumber);

    /**
     * Deletes route by number.
     * @param routeNumber route number
     */
    void deleteByNumber(int routeNumber);

    /**
     * Creates route by a set of cities.
     * @param cities cities
     */
    void createRoute(String... cities);

    /**
     * Gets a list of all existing routes.
     * @return routes list
     */
    List<Route> getAllRoutes();
}
