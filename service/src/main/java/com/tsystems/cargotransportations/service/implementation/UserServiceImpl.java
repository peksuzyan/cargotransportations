package com.tsystems.cargotransportations.service.implementation;

import com.tsystems.cargotransportations.dao.DaoUtils;
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

/**
 * Implements business-logic operations that bound with user.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    /**
     * Instance of implementation of UserDao class.
     */
    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void deleteByName(String name) {
        userDao.delete(getByName(name));
    }

    @Transactional
    @Override
    public void changeByName(String name, String password, UserRole role) {
        User user = userDao.getByName(name);
        user.setPassword(password);
        user.setUserRole(role);
        userDao.update(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

    @Transactional
    @Override
    public void createUser(String name, String password, UserRole role, int driverNumber) {
        User user = new User();
        userDao.create(user);
        user.setName(name);
        user.setPassword(password);
        user.setUserRole(role);
        user.setCreationDate(new Date());
        user.setDriverNumber(driverNumber);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllByRole(UserRole role) {
        return userDao.getAllByRole(role);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isAuthenticatedUser(String name, String password) {
        User user = userDao.getByName(name);
        return user != null && user.getPassword().equals(password);
    }

    @Transactional
    @Override
    public void changePasswordByName(String name, String password) {
        User user = userDao.getByName(name);
        user.setPassword(password);
        userDao.update(user);
    }

    @Override
    public void checkUserByDriverNumber(User user, int driverNumber) throws IllegalAccessException {
        if (user.getDriverNumber() != driverNumber) throw new IllegalAccessException();
    }
}
