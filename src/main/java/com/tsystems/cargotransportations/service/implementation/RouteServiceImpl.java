package com.tsystems.cargotransportations.service.implementation;

import com.tsystems.cargotransportations.dao.DaoUtils;
import com.tsystems.cargotransportations.dao.abstracts.RouteDao;
import com.tsystems.cargotransportations.dao.implementation.RouteDaoImpl;
import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.entity.Route;
import com.tsystems.cargotransportations.service.interfaces.RouteService;

import java.util.*;

import static com.tsystems.cargotransportations.constants.MagicConstants.COMMA_DELIMITER;

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

    @Override
    public List<String> getRoutePoints(String routePointsString) {
        List<String> routePoints = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(
                routePointsString.substring(1, routePointsString.length() - 1), COMMA_DELIMITER);
        while (tokenizer.hasMoreTokens()) {
            routePoints.add(tokenizer.nextToken().trim());
        }
        return routePoints;
    }

    @Override
    public Set<List<String>> getRoutesCases(List<Cargo> cargoes) {
        Set<String> routePoints = new HashSet<>();
        for (Cargo cargo : cargoes) {
            routePoints.add(cargo.getDepartureCity());
            routePoints.add(cargo.getArrivalCity());
        }
        return getRoutes(routePoints);
    }

    /**
     * Gets a set of routes cases by given route points.
     * @param routePoints route points
     * @return a set of routes cases
     */
    private static Set<List<String>> getRoutes(Set<String> routePoints) {
        Set<List<String>> routes = new HashSet<>();
        routes.addAll(getRoutesCases(routePoints, new ArrayList<>()));
        return routes;
    }

    /**
     * Recursively operation to find all combinations of routes cases.
     * Takes a set of remained points and already evaluated points.
     * Proceed recursively process while a set of remained points has became empty.
     * @param remainedPoints a set of remained points
     * @param pointsSequence a list of evaluated sequence points of route
     * @return a set of routes cases
     */
    private static Set<List<String>> getRoutesCases(Set<String> remainedPoints, List<String> pointsSequence) {
        Set<List<String>> routesCases = new HashSet<>();
        if (!remainedPoints.isEmpty()) {
            List<String> routeCase;
            for (String current : remainedPoints) {
                routeCase = new ArrayList<>(pointsSequence);
                routeCase.add(current);
                Set<String> pointsWithoutCurrent = new HashSet<>(remainedPoints);
                pointsWithoutCurrent.remove(current);
                routesCases.addAll(getRoutesCases(pointsWithoutCurrent, routeCase));
            }
        } else {
            routesCases.add(pointsSequence);
        }
        return routesCases;
    }
}
