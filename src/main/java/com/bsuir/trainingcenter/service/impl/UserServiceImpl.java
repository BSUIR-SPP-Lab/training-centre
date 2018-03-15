package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.UserDAO;
import com.bsuir.trainingcenter.entity.Role;
import com.bsuir.trainingcenter.entity.User;
import com.bsuir.trainingcenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean addUser(User user) {
        if(userDAO.isLoginUnique(user.getLogin())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userDAO.addUser(user);
        }
        return false;
    }

    @Override
    public List<User> findUsers() {
        return userDAO.findUsers();
    }

    @Override
    public User findUser(long id) {
        return userDAO.findUser(id);
    }

    @Override
    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    @Override
    public boolean updateUserRole(long userId, Role newRole) {
        return userDAO.updateUserRole(userId, newRole);
    }

    @Override
    public boolean deleteUser(long id) {
        return userDAO.deleteUser(id);
    }
}
