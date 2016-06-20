package com.tsystems.cargotransportations.service;

import com.tsystems.cargotransportations.dao.DaoUtils;
import com.tsystems.cargotransportations.dao.UserDao;
import com.tsystems.cargotransportations.dao.UserDaoImpl;
import com.tsystems.cargotransportations.entity.User;
import com.tsystems.cargotransportations.entity.UserRole;

import java.util.Date;
import java.util.List;

/**
 * Implements business-logic operations that bound with user.
 */
public class UserServiceImpl implements UserService {
    /**
     * Instance of implementation of UserDao class.
     */
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void deleteByName(String name) {
        DaoUtils.executeInTransaction(() -> {
            userDao.delete(getByName(name));
        });
    }

    @Override
    public void changeByName(String name, String password, UserRole role) {
        DaoUtils.executeInTransaction(() -> {
            User user = userDao.getByName(name);
            user.setPassword(password);
            user.setUserRole(role);
            userDao.update(user);
        });
    }

    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public void createUser(String name, String password, UserRole role) {
        DaoUtils.executeInTransaction(() -> {
            User user = new User();
            userDao.create(user);
            user.setName(name);
            user.setPassword(password);
            user.setUserRole(role);
            user.setCreationDate(new Date());
        });
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public List<User> getAllByRole(UserRole role) {
        return userDao.getAllByRole(role);
    }

    @Override
    public boolean isAuthenticatedUser(String name, String password) {
        User user = userDao.getByName(name);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public void changePasswordByName(String name, String password) {
        DaoUtils.executeInTransaction(() -> {
            User user = userDao.getByName(name);
            user.setPassword(password);
            userDao.update(user);
        });
    }
}
