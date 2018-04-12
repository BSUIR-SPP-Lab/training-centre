package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Role;
import com.bsuir.trainingcenter.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    boolean addUser(User user);

    boolean isLoginUnique(String login);

    List<User> findUsers();

    Optional<User> findUser(long userId);

    Optional<User> findUser(String login);

    boolean updateUser(User user);

    boolean updateUserRole(long userId, Role newRole);

    boolean deleteUser(long userId);

}
