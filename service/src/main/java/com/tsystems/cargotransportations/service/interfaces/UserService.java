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

}
