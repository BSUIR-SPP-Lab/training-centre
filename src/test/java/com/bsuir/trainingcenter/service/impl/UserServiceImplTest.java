package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.UserDAO;
import com.bsuir.trainingcenter.entity.Role;
import com.bsuir.trainingcenter.entity.User;
import com.bsuir.trainingcenter.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @MockBean
    public UserDAO userDAO;

    @Autowired
    public UserService userService;

    @Autowired
    public PasswordEncoder passwordEncoder;

    List<User> list;
    User user;

    @Before
    public void setUp() {
        list = new ArrayList<>();
        LocalDateTime l = LocalDateTime.now();
        user = new User(1, "test", "testPass", Role.ADMIN, "testEmail", "testPhone","firstName","lastName");
        list.add(user);
    }

    @Test
    public void addUser() {
        User result=user;
        result.setPassword(passwordEncoder.encode(result.getPassword()));
        given(userDAO.isLoginUnique("test")).willReturn(true);
        given(userDAO.addUser(result)).willReturn(true);
        assertTrue(userService.addUser(user));
    }

    @Test
    public void addUser_Negative_0() {
        User result=user;
        result.setPassword(passwordEncoder.encode(result.getPassword()));
        given(userDAO.isLoginUnique("test")).willReturn(false);
        given(userDAO.addUser(result)).willReturn(true);
        assertFalse(userService.addUser(user));
    }

    @Test
    public void addUser_Negative_1() {
        User result=user;
        result.setPassword(passwordEncoder.encode(result.getPassword()));
        given(userDAO.isLoginUnique("test")).willReturn(true);
        given(userDAO.addUser(result)).willReturn(false);
        assertFalse(userService.addUser(user));
    }

    @Test
    public void findUsers() {
        given(userDAO.findUsers()).willReturn(list);
        assertEquals(userService.findUsers(),list);
    }

    @Test
    public void findUser() {
        given(userDAO.findUser(1)).willReturn(Optional.of(user));
        assertEquals(userService.findUser(1),user);
    }

    @Test
    public void updateUser() {
        given(userDAO.updateUser(user)).willReturn(true);
        assertTrue(userService.updateUser(user));
    }

    @Test
    public void updateUserRole() {
        given(userDAO.updateUserRole(1,Role.COORDINATOR)).willReturn(true);
        assertTrue(userService.updateUserRole(1,Role.COORDINATOR));
    }

    @Test
    public void deleteUser() {
        given(userDAO.deleteUser(1)).willReturn(true);
        assertTrue(userService.deleteUser(1));
    }

    @Test
    public void login() {
        String password = user.getPassword();
        user.setPassword(  passwordEncoder.encode(password));
        given(userDAO.findUser("test")).willReturn(Optional.of(user));
        assertEquals(userService.login("test",password),user);
    }
}