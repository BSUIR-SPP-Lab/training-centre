package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.User;

import java.util.List;


public interface UserDAO {

    boolean addUser(User user);

    List<User> findUsers();

    User findUser(long userId);

    boolean updateUser(User user);

    boolean deleteUser(long userId);

}
