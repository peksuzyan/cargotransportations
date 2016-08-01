package com.tsystems.cargotransportations.dao.implementation;

import com.tsystems.cargotransportations.dao.interfaces.CargoDao;
import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.entity.CargoStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.tsystems.cargotransportations.constants.DaoMapper.CARGO_DAO;

/**
 * Specific DAO implementation for cargoes management.
 */
@Repository(CARGO_DAO)
public class CargoDaoImpl extends GenericDaoImpl<Cargo> implements CargoDao {

    /**
     * Default constructor.
     */
    public CargoDaoImpl() {
        super(Cargo.class);
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

    @Override
    public List<Cargo> getCargoesByStatus(CargoStatus status) {
        String query = String.format("SELECT c FROM Cargo AS c WHERE c.status = '%s'", status);
        return getEntityManager().createQuery(query, Cargo.class).getResultList();
    }
}
