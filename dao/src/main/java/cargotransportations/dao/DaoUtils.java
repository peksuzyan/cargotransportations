package cargotransportations.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoUtils {
    private final static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("cargotransportations");
    private static EntityManager entityManager;

    private DaoUtils() {}

    public static EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    public static void executeInTransaction(Transaction transaction) {
        getEntityManager().getTransaction().begin();
        transaction.execute();
        getEntityManager().getTransaction().commit();
    }

    public static void close() {
        getEntityManager().close();
    }
}
