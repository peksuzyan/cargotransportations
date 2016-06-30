package com.tsystems.cargotransportations.dao.implementation;

import com.tsystems.cargotransportations.dao.interfaces.RouteDao;
import com.tsystems.cargotransportations.entity.Route;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Specific DAO implementation for routes management.
 */
@Repository("routeDao")
public class RouteDaoImpl extends GenericDaoImpl<Route> implements RouteDao {

    public RouteDaoImpl() {
        super(Route.class);
    }

    @Override
    public Route getByNumber(int number) {
        String query = String.format(
                "FROM %s WHERE number = %d", Route.class.getSimpleName(), number);
        List<Route> routes = getEntityManager().createQuery(query, Route.class).getResultList();
        return routes.size() != 0 ? routes.get(0) : null;
    }
}
