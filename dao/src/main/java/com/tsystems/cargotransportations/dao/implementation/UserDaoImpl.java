package com.tsystems.cargotransportations.dao.implementation;

import com.tsystems.cargotransportations.dao.interfaces.UserDao;
import com.tsystems.cargotransportations.entity.User;
import com.tsystems.cargotransportations.entity.UserRole;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static com.tsystems.cargotransportations.constants.DaoMapping.USER_DAO;

/**
 * Specific DAO implementation for users management.
 */
@Repository(USER_DAO)
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    /**
     * Default constructor.
     */
    public UserDaoImpl() {
        super(User.class);
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
    public List<User> getUsersByRole(UserRole role) {
        String query = String.format("SELECT u FROM User AS u WHERE u.userRole = '%s'", role);
        return getEntityManager().createQuery(query, User.class).getResultList();
    }

    @Override
    public User getUserByEmail(String email) {
        String query = String.format("SELECT u FROM User AS u WHERE u.email = '%s'", email);
        List<User> users = getEntityManager().createQuery(query, User.class).getResultList();
        return users.isEmpty() ? null : users.get(0);
    }
}
