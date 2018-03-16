package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.config.TestConfig;
import com.bsuir.trainingcenter.entity.Group;
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
public class GroupDAOImplTest {

    private GroupDAOImpl groupDAO = new GroupDAOImpl();

    @Autowired
    private void setDataSource(DataSource dataSource) {
        groupDAO.setDataSource(dataSource);
    }

    @Test
    @Rollback
    public void addGroup() {
        Group group = new Group(8, 45);
        assertTrue(groupDAO.addGroup(group));
        assertEquals(groupDAO.findGroups().size(), 13);
    }

    @Test
    public void findGroups() {
        assertEquals(groupDAO.findGroups().size(), 12);
    }

    @Test
    public void findGroup() {
        Group group = new Group(13, 8, 45);
        assertEquals(groupDAO.findGroup(13), group);
    }

    @Test
    @Rollback
    public void updateGroup() {
        Group group = new Group(13, 8, 50);
        assertTrue(groupDAO.updateGroup(group));
        assertEquals(groupDAO.findGroup(13), group);
    }

    @Test
    @Rollback
    public void deleteGroup() {
        assertTrue(groupDAO.deleteGroup(5));
        assertEquals(groupDAO.findGroups().size(), 11);
    }

}
