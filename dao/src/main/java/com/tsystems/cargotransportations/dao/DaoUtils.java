package com.tsystems.cargotransportations.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static com.tsystems.cargotransportations.constant.ConfConstants.UNIT_NAME;

// TODO
public class DaoUtils {
    private final static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory(UNIT_NAME);
    private static EntityManager entityManager;

    private DaoUtils() {}

    public static EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    public static void executeInTransaction(Transaction transaction) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            transaction.execute();
            em.getTransaction().commit();
        } catch (Exception ex) {
            //handle exception
            ex.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
