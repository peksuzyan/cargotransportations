package com.tsystems.cargotransportations.service;

import com.tsystems.cargotransportations.entity.User;
import com.tsystems.cargotransportations.entity.UserRole;

import java.util.List;

/**
 * Contains business-logic operations that bound with user.
 */
public interface UserService {
    /**
     * Deletes user by name.
     * @param name name
     */
    void deleteByName(String name);

    /**
     * Changes user by name.
     * @param name name
     */
    void changeByName(String name, String password, UserRole role);

    /**
     * Gets user by name.
     * @param name name
     * @return user
     */
    User getByName(String name);

    /**
     * Creates user by passed parameters.
     * @param name user name
     * @param password password
     * @param role user role
     */
    void createUser(String name, String password, UserRole role);

    /**
     * Gets all users.
     * @return users list
     */
    List<User> getAll();

    /**
     * Gets users by role.
     * @param role role
     * @return users list according with given role
     */
    List<User> getAllByRole(UserRole role);

    /**
     * Checks user whether is authenticated user or not.
     * @param name user name
     * @param password user password
     * @return whether is authenticated user or not
     */
    boolean isAuthenticatedUser(String name, String password);
}
