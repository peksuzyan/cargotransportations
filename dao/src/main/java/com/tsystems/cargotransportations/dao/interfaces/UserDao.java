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

}
