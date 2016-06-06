package cargotransportations.dao.manager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class AbstractManager<T> implements Manager<T> {
    private EntityManager entityManager = EntityManagerGenerator.getNewEntityManager();

    public abstract Class<T> getItemClass();

    public T read(int id) {
        return entityManager.find(getItemClass(), id);
    }

    public List<T> readAll() {
        String queryLine = String.format("SELECT %1$s FROM %1$s", getItemClass().getSimpleName());
        TypedQuery<T> query = entityManager.createQuery(queryLine, getItemClass());
        return query.getResultList();
    }

    public void delete(int id) {
        T object = read(id);
        entityManager.getTransaction().begin();
        entityManager.remove(object);
        entityManager.getTransaction().commit();
    }

    public T create() {
        T object = null;
        try {
            object = getItemClass().newInstance();
            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return object;
    }

    public void close() {
        if (entityManager.isOpen()) entityManager.close();
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
