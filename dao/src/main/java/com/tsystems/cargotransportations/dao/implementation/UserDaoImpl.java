package com.tsystems.cargotransportations.dao.implementation;

import com.tsystems.cargotransportations.dao.interfaces.UserDao;
import com.tsystems.cargotransportations.entity.User;
import com.tsystems.cargotransportations.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Specific DAO implementation for users management.
 */
@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User getByName(String name) {
        String query = String.format(
                "FROM %s WHERE name = '%s'", User.class.getSimpleName(), name);
        List<User> users = getEntityManager().createQuery(query, User.class).getResultList();
        return users.size() != 0 ? users.get(0) : null;
    }

    @Override
    public List<User> getAllByRole(UserRole role) {
        String query = String.format("SELECT u FROM User u WHERE u.userRole = '%s'", role);
        return getEntityManager().createQuery(query, User.class).getResultList();
    }
}
