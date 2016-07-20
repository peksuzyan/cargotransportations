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
     * Gets users by role.
     * @param role role
     * @return users list according with given role
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
