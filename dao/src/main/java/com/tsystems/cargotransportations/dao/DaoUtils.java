package com.tsystems.cargotransportations.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.tsystems.cargotransportations.constants.ConfConstants.UNIT_NAME;

// TODO exceptions
/**
 * Provides convenient methods for thread-safe getting EntityManager's instances from factory.
 */
public class DaoUtils {
    /**
     * Initializes a new entity manager factory by given unit-name through Persistence class.
     */
    private final static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory(UNIT_NAME);

    /**
     * Map keeps all created entity managers and returns its to associated thread.
     */
    private static Map<Thread, EntityManager> entityManagersMap = getEntityManagerMap();

    /**
     * Provides of initializing entity managers map.
     * @return entity manager map
     */
    private static Map<Thread,EntityManager> getEntityManagerMap() {
        return new ConcurrentHashMap<>();
    }

    /**
     * Hides a possibility to create any other class instance.
     */
    private DaoUtils() {}

    /**
     * Provides a getting of entity manager associated with current thread.
     * When entity manager bound with thread doesn't exist already or yet then it's creating.
     * Otherwise returns existing one.
     * @return entity manager associated with current thread
     */
    public static EntityManager getEntityManager() {
        Thread current = Thread.currentThread();
        if (!entityManagersMap.containsKey(current) || !entityManagersMap.get(current).isOpen()) {
            entityManagersMap.put(current, entityManagerFactory.createEntityManager());
        }
        return entityManagersMap.get(current);
    }

    /**
     * Provides a conducting of DAO-depended operations within transaction.
     * @param transaction functional interface
     */
    public static void executeInTransaction(Transaction transaction) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            transaction.execute();
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
