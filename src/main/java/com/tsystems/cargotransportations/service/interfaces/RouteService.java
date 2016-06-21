package com.tsystems.cargotransportations.service.interfaces;

import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.entity.Route;

import java.util.List;
import java.util.Set;

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

    /**
     * Gets all possible routes cases for given a list of cargoes.
     * @param cargoes cargoes
     * @return set of routes cases
     */
    Set<List<String>> getRoutesCases(List<Cargo> cargoes);

    /**
     * Gets route points from string representation.
     * @param routePointsString string representation of route points
     * @return route points list
     */
    List<String> getRoutePoints(String routePointsString);
}
