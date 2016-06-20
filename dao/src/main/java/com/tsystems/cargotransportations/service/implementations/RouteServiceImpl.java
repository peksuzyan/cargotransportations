package com.tsystems.cargotransportations.service.implementations;

import com.tsystems.cargotransportations.dao.DaoUtils;
import com.tsystems.cargotransportations.dao.abstracts.RouteDao;
import com.tsystems.cargotransportations.dao.implementations.RouteDaoImpl;
import com.tsystems.cargotransportations.entity.Route;
import com.tsystems.cargotransportations.service.abstracts.RouteService;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements business-logic operations that bound with route.
 */
public class RouteServiceImpl implements RouteService {
    /**
     * Instance of implementation of RouteDao class.
     */
    private RouteDao routeDao = new RouteDaoImpl();

    @Override
    public Route getByNumber(int routeNumber) {
        return routeDao.getByNumber(routeNumber);
    }

    @Override
    public void deleteByNumber(int routeNumber) {
        DaoUtils.executeInTransaction(() -> {
            routeDao.delete(getByNumber(routeNumber));
        });
    }

    @Override
    public void createRoute(String... cities) {
        if (cities != null) {
            List<String> citiesList = new ArrayList<>();
            for (String city : cities) {
                if (city != null) {
                    citiesList.add(city.trim());
                }
            }
            DaoUtils.executeInTransaction(() -> {
                Route route = new Route();
                routeDao.create(route);
                route.setCities(citiesList);
                route.setNumber(route.getId() + 1000);
            });
        }
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeDao.getAll();
    }
}
