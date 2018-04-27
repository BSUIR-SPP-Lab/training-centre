package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.GroupDAO;
import com.bsuir.trainingcenter.entity.Group;
import com.bsuir.trainingcenter.service.GroupService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GroupServiceImplTest {

    @MockBean
    public GroupDAO groupDAO;

    @Autowired
    public GroupService groupService;

    List<Group> list;
    Group group;

    @Before
    public void setUp(){
        list = new ArrayList<>();
        group = new Group(1,1,1);
        list.add(group);

    }


    @Test
    public void addGroup() {
        given(groupDAO.addGroup(group)).willReturn(true);
        assertTrue(groupService.addGroup(group));
    }

    @Test
    public void findGroups() {
        given(groupDAO.findGroups()).willReturn(list);
        assertEquals(groupService.findGroups(),list);
    }

    @Test
    public void findGroup() {
        given(groupDAO.findGroup(1)).willReturn(Optional.of(group));
        assertEquals(groupService.findGroup(1),group);
    }

    @Test
    public void updateGroup() {
        given(groupDAO.updateGroup(group)).willReturn(true);
        assertTrue(groupService.updateGroup(group));
    }

    @Test
    public void deleteGroup() {
        given(groupDAO.deleteGroup(1)).willReturn(true);
        assertTrue(groupService.deleteGroup(1));
    }
}