package com.tsystems.cargotransportations.service.interfaces;

import com.tsystems.cargotransportations.entity.User;
import com.tsystems.cargotransportations.entity.UserRole;
import com.tsystems.cargotransportations.exception.IllegalAccessException;

import java.util.List;

/**
 * Contains business-logic operations that bound with user.
 */
public interface UserService extends GenericService<User>  {
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
     * Changes user password.
     * @param name user name
     * @param password user password
     */
    void changePasswordByName(String name, String password);

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
     * @param driverNumber driver number
     */
    void createUser(String name, String password, UserRole role, int driverNumber);

    /**
     * Gets all users.
     * @return users list
     */
    List<User> getAllUsers();

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

    /**
     * Checks to respecting of driver number and user.
     * @param user user
     * @param driverNumber driver number
     * @throws IllegalAccessException
     */
    void checkUserByDriverNumber(User user, int driverNumber) throws IllegalAccessException;
}
