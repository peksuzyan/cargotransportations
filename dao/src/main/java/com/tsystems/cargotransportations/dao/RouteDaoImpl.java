package com.tsystems.cargotransportations.dao;

import com.tsystems.cargotransportations.entity.Route;

public class RouteDaoImpl extends GenericDaoImpl<Route> implements RouteDao {

    public RouteDaoImpl() {
        super(Route.class);
    }
}
