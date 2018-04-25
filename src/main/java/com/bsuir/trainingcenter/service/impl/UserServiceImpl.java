package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.UserDAO;
import com.bsuir.trainingcenter.entity.Role;
import com.bsuir.trainingcenter.entity.User;
import com.bsuir.trainingcenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if (userDAO.isLoginUnique(user.getLogin())) {
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
    public List<User> findUsersWithRole(Role role) {
        return userDAO.findUsers().stream().filter(user -> {
            return user.getRole() == role;
        }).collect(Collectors.toList());
    }

    @Override
    public List<User> findTeachers() {
        return findUsersWithRole(Role.TEACHER);
    }

    @Override
    public User findUser(long id) {
        Optional<User> user = userDAO.findUser(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    @Override
    public User findUser(String login) {
        Optional<User> user = userDAO.findUser(login);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }

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

    @Override
    public User login(String login, String password) {
        Optional<User> user = userDAO.findUser(login);
        return user.map(user1 -> passwordEncoder.matches(password, user1.getPassword()) ? user1 : null).orElse(null);
    }

    @Override
    public List<User> findUsersByCourseId(long courseId,boolean finish) {
        return userDAO.findUsersByCourseId(courseId, finish);
    }
}
