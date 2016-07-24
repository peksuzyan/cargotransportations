package com.tsystems.cargotransportations.presentation.grids;

import com.tsystems.cargotransportations.entity.Route;

public class RouteWrapper extends Route {

    private Route route;

    private String routePoints;

    public RouteWrapper(Route route) {
        this.route = route;
        this.routePoints =
                route.getCities() == null
                        ? null : route.getCities().toString();
    }

    /**
     * Gets routePoints.
     *
     * @return routePoints routePoints
     */
    public String getRoutePoints() {
        return routePoints;
    }

    /**
     * Gets id.
     *
     * @return id id
     */
    @Override
    public int getId() {
        return route.getId();
    }

    /**
     * Gets duration.
     *
     * @return duration duration
     */
    @Override
    public int getDuration() {
        return route.getDuration();
    }

    /**
     * Gets distance.
     *
     * @return distance distance
     */
    @Override
    public int getDistance() {
        return route.getDistance();
    }
}
