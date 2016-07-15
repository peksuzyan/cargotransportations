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

import static com.tsystems.cargotransportations.constants.ServiceMapping.ROUTE_SERVICE;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

/**
 * Implements business-logic operations that bound with route.
 */
@Transactional
@Service(ROUTE_SERVICE)
public class RouteServiceImpl extends GenericServiceImpl<Route> implements RouteService {

    @Override
    GenericDao<Route> getDao() {
        return routeDao;
    }

    /**
     * Instance of implementation of RouteDao class.
     */
    @Autowired
    private RouteDao routeDao;

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
    @Transactional(propagation = SUPPORTS)
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
    @Transactional(propagation = SUPPORTS)
    private boolean isSuitableRoutePart(String cityFrom, String cityTo, Cargo cargo) {
        return cityFrom.equalsIgnoreCase(cargo.getDepartureCity())
                && cityTo.equalsIgnoreCase(cargo.getArrivalCity());
    }
}
