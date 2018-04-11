package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.StudentGroupDAO;
import com.bsuir.trainingcenter.entity.StudentGroup;
import com.bsuir.trainingcenter.service.StudentGroupService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StudentGroupServiceImplTest {

    @MockBean
    public StudentGroupDAO studentGroupDAO;

    @Autowired
    public StudentGroupService studentGroupService;

    List<StudentGroup> list;
    StudentGroup studentGroup;

    @Before
    public void setUp() {
        list = new ArrayList<>();
        studentGroup = new StudentGroup(1, 1,true);
        list.add(studentGroup);

    }

    @Test
    public void addStudentGroup() {
        given(studentGroupDAO.addStudentGroup(studentGroup)).willReturn(true);
        assertTrue(studentGroupService.addStudentGroup(studentGroup));
    }

    @Test
    public void findStudentGroups() {
        given(studentGroupDAO.findStudentGroups()).willReturn(list);
        assertEquals(studentGroupService.findStudentGroups(),list);
    }

    @Test
    public void updateStudentGroup() {
        given(studentGroupDAO.updateStudentGroup(studentGroup)).willReturn(true);
        assertTrue(studentGroupService.updateStudentGroup(studentGroup));
    }

    @Test
    public void deleteStudentGroup() {
        given(studentGroupDAO.deleteStudentGroup(1,1)).willReturn(true);
        assertTrue(studentGroupService.deleteStudentGroup(1,1));
    }
}