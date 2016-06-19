package com.tsystems.cargotransportations.dao;

import com.tsystems.cargotransportations.entity.Route;

import java.util.List;

/**
 * Specific DAO implementation for routes management.
 */
public class RouteDaoImpl extends GenericDaoImpl<Route> implements RouteDao {

    public RouteDaoImpl() {
        super(Route.class);
    }

    @Override
    public Route getByNumber(int number) {
        String query = String.format(
                "FROM %s WHERE number = %d", Route.class.getSimpleName(), number);
        List<Route> routes = getLazyEntityManager().createQuery(query, Route.class).getResultList();
        return routes.size() != 0 ? routes.get(0) : null;
    }
}
