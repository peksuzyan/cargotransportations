package com.tsystems.cargotransportations.dao.abstracts;

import com.tsystems.cargotransportations.entity.Route;

/**
 * Specific DAO interface for route entity.
 * Contains specific operations over route entity.
 */
public interface RouteDao extends GenericDao<Route> {
    /**
     * Gets route by number.
     * @param number number
     * @return route
     */
    Route getByNumber(int number);
}
