package cargotransportations.dao;

import cargotransportations.entity.Route;

public class RouteDaoImpl extends GenericDaoImpl<Route> implements RouteDao {

    public RouteDaoImpl() {
        super(Route.class);
    }
}
