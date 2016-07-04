package com.tsystems.cargotransportations.service.implementation;

import com.tsystems.cargotransportations.dao.interfaces.GenericDao;
import com.tsystems.cargotransportations.dao.interfaces.RouteDao;
import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.entity.Route;
import com.tsystems.cargotransportations.service.interfaces.RouteService;

import com.tsystems.cargotransportations.constants.MagicConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Implements business-logic operations that bound with route.
 */
@Service("routeService")
public class RouteServiceImpl extends GenericServiceImpl<Route> implements RouteService {
    /**
     * Instance of implementation of RouteDao class.
     */
    @Autowired
    private RouteDao routeDao;

    @Override
    GenericDao<Route> getDao() {
        return routeDao;
    }

    @Transactional(readOnly = true)
    @Override
    public Route getByNumber(int routeNumber) {
        return routeDao.getByNumber(routeNumber);
    }

    @Transactional
    @Override
    public void deleteByNumber(int routeNumber) {
        routeDao.delete(getByNumber(routeNumber));
    }

    @Transactional
    @Override
    public void createRoute(int duration, String... cities) {
        if (cities != null) {
            List<String> citiesList = new ArrayList<>();
            for (String city : cities) {
                if (city != null) {
                    citiesList.add(city.trim());
                }
            }
            Route route = new Route();
            routeDao.create(route);
            route.setCities(citiesList);
            route.setDuration(duration);
            route.setNumber(route.getId() + 1000);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Route> getAllRoutes() {
        return routeDao.getAll();
    }

    @Override
    public List<String> getRoutePoints(String routePointsString) {
        List<String> routePoints = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(
                routePointsString.substring(1, routePointsString.length() - 1), MagicConstants.COMMA_DELIMITER);
        while (tokenizer.hasMoreTokens()) {
            routePoints.add(tokenizer.nextToken().trim());
        }
        return routePoints;
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    @Override
    public List<Route> getSuitableRoutesByOrder(Order order) {
        if (order == null) return Collections.emptyList();
        if (order.getCargoes() == null) return Collections.emptyList();
        if (order.getCargoes().size() == 0) return Collections.emptyList();
        List<Cargo> cargoes = order.getCargoes();
        List<Route> routes = routeDao.getAll();
        List<Route> suitableRoutes = new ArrayList<>();
        for (Route route : routes) {
            if (isSuitableRoute(cargoes, route.getCities())) {
                suitableRoutes.add(route);
            }
        }
        return suitableRoutes;
    }

    /**
     * Return true when order has suitable route points for given route or not otherwise.
     * @param orderCargoes cargoes
     * @param routePoints route points
     * @return is same route points or not
     */
    private boolean isSuitableRoute(List<Cargo> orderCargoes, List<String> routePoints) {
        List<Cargo> cargoes = new ArrayList<>(orderCargoes);
        for (int i = 0; i < routePoints.size() - 1; i++) {
            Iterator<Cargo> cargoIterator = cargoes.iterator();
            while (cargoIterator.hasNext()) {
                if (isSuitableRoutePart(routePoints.get(i), routePoints.get(i + 1), cargoIterator.next())) {
                    cargoIterator.remove();
                }
            }
        }
        return cargoes.isEmpty();
    }

    /**
     * Return true when departure city and arrival city of cargo
     * equal first and second root point of route respectively.
     * @param cityFrom first point of route
     * @param cityTo second point of route
     * @param cargo cargo
     * @return equals or not
     */
    private boolean isSuitableRoutePart(String cityFrom, String cityTo, Cargo cargo) {
        return cityFrom.equalsIgnoreCase(cargo.getDepartureCity())
                && cityTo.equalsIgnoreCase(cargo.getArrivalCity());
    }
}
