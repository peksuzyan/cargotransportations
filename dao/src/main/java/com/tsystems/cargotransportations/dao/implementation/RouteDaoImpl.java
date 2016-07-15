package com.tsystems.cargotransportations.dao.implementation;

import com.tsystems.cargotransportations.dao.interfaces.RouteDao;
import com.tsystems.cargotransportations.entity.Route;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.tsystems.cargotransportations.constants.DaoMapping.ROUTE_DAO;

/**
 * Specific DAO implementation for routes management.
 */
@Repository(ROUTE_DAO)
public class RouteDaoImpl extends GenericDaoImpl<Route> implements RouteDao {

    /**
     * Default constructor.
     */
    public RouteDaoImpl() {
        super(Route.class);
    }

    /**
     * Injected instance of entity manager.
     */
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    EntityManager getEntityManager() {
        return entityManager;
    }

}
