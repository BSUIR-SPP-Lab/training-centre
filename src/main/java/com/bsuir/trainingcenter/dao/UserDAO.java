package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Role;
import com.bsuir.trainingcenter.entity.User;

import java.util.List;

public interface UserDAO {

    boolean addUser(User user);

    boolean isLoginUnique(String login);

    List<User> findUsers();

    User findUser(long userId);

    boolean updateUser(User user);

    boolean updateUserRole(long userId, Role newRole);

    boolean deleteUser(long userId);

}
