package com.tsystems.cargotransportations.dao.implementation;

import com.tsystems.cargotransportations.dao.interfaces.CargoDao;
import com.tsystems.cargotransportations.entity.Cargo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Specific DAO implementation for cargoes management.
 */
@Repository("cargoDao")
public class CargoDaoImpl extends GenericDaoImpl<Cargo> implements CargoDao {

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

    @Deprecated
    @Override
    public Cargo getByNumber(int number) {
        String query = String.format(
                "FROM %s WHERE number = %d", Cargo.class.getSimpleName(), number);
        List<Cargo> cargoes = getEntityManager().createQuery(query, Cargo.class).getResultList();
        return cargoes.size() != 0 ? cargoes.get(0) : null;
    }

    @Override
    public List<Cargo> getFreeCargoes() {
        String query = "SELECT c FROM Cargo AS c WHERE c.status = 'PREPARED'";
        return getEntityManager().createQuery(query, Cargo.class).getResultList();
    }
}
