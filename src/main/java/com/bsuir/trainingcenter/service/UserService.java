package com.bsuir.trainingcenter.service;

import com.bsuir.trainingcenter.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    boolean addUser(User user);

    List<User> findUsers();

    User findUser(long id);

    boolean updateUser(User user);

    boolean deleteUser(long id);
}
