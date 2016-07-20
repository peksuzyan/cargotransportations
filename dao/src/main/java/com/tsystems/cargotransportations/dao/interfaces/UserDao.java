package com.tsystems.cargotransportations.dao.interfaces;

import com.tsystems.cargotransportations.entity.User;
import com.tsystems.cargotransportations.entity.UserRole;

import java.util.List;

/**
 * Specific DAO interface for user entity.
 * Contains specific operations over user entity.
 */
public interface UserDao extends GenericDao<User> {

    /**
     * Gets all users by a given role.
     * @param role role
     * @return users list
     */
    List<User> getUsersByRole(UserRole role);

    /**
     * Gets user by email.
     * @param email email
     * @return user
     */
    User getUserByEmail(String email);

    /**
     * Gets a result of authentication given user by its email and password.
     * @param email email
     * @param password password
     * @return authenticated or not
     */
    boolean authenticate(String email, String password);
}
