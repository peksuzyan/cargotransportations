package cargotransportations.dao.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerGenerator {
    private static EntityManagerFactory entityManagerFactory;

    private EntityManagerGenerator() {}

    public static EntityManager getNewEntityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("cargotransportations");
        }
        return entityManagerFactory.createEntityManager();
    }
}
