package com.tsystems.cargotransportations.service.implementation;

import com.tsystems.cargotransportations.dao.interfaces.GenericDao;
import com.tsystems.cargotransportations.dao.interfaces.UserDao;
import com.tsystems.cargotransportations.entity.User;
import com.tsystems.cargotransportations.entity.UserRole;
import com.tsystems.cargotransportations.exception.IllegalAccessException;
import com.tsystems.cargotransportations.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static com.tsystems.cargotransportations.constants.ServiceMapping.USER_SERVICE;

/**
 * Implements business-logic operations that bound with user.
 */
@Transactional
@Service(USER_SERVICE)
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {

    @Override
    GenericDao<User> getDao() {
        return userDao;
    }

    /**
     * Instance of implementation of UserDao class.
     */
    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = true)
    @Override
    public List<User> getUsersByRole(UserRole role) {
        return userDao.getUsersByRole(role);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }
}
