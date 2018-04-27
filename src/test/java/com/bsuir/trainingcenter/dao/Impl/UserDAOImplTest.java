package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.config.TestConfig;
import com.bsuir.trainingcenter.entity.Role;
import com.bsuir.trainingcenter.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@SpringBootTest
@Transactional
public class UserDAOImplTest {

    private UserDAOImpl userDAO = new UserDAOImpl();

    @Autowired
    private void setDataSource(DataSource dataSource) {
        userDAO.setDataSource(dataSource);
    }

    @Test
    @Rollback
    public void addUser() {
        User user = new User("logj", "$2a$10$4E6uT21RneB9FkHJ.2DZP.vwLo/GgXHAtCNORPCqxpUVvyXD4Qoqe",
                Role.USER, "email@email.com", null, null, null);
        assertTrue(userDAO.addUser(user));
        assertEquals(27, userDAO.findUsers().size());
    }

    @Test
    public void findUsers() {
        assertEquals(26, userDAO.findUsers().size());
    }

    @Test
    public void findUser() {
        User user = new User(48, "cat1566", "$2a$10$4E6uT21RneB9FkHJ.2DZP.vwLo/GgXHAtCNORPCqxpUVvyXD4Qoqe",
                Role.USER, "cat@ya.ru", "1688442", "Катя", "Катина");
        assertEquals(user, userDAO.findUser(48).get());
    }

    @Test
    @Rollback
    public void updateUser() {
        User user = new User(48, "cat1566", "$2a$10$4E6uT21RneB9FkHJ.2DZP.vwLo/GgXHAtCNORPCqxpUVvyXD4Qoqe",
                Role.USER, "cat@ya.ru", "1688442", "Катя", "Катрина");
        assertTrue(userDAO.updateUser(user));
        assertEquals(user, userDAO.findUser(48).get());
    }

    @Test
    @Rollback
    public void deleteUser() {
        assertTrue(userDAO.deleteUser(63));
        assertEquals(25, userDAO.findUsers().size());
    }

}
