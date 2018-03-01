package com.bsuir.trainingcenter.service;

import com.bsuir.trainingcenter.entity.User;

import java.util.List;

public interface UserService {
    boolean addUser(User user);

    List<User> findUsers();

    User findUser(long id);

    boolean updateUser(User user);

    boolean deleteUser(long id);
}
