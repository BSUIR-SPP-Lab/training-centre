package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.TaskInfoDAO;
import com.bsuir.trainingcenter.entity.TaskInfo;
import com.bsuir.trainingcenter.service.TaskInfoService;
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
public class TaskInfoServiceImplTest {

    @MockBean
    public TaskInfoDAO taskInfoDAO;

    @Autowired
    public TaskInfoService taskInfoService;

    List<TaskInfo> list;
    TaskInfo taskInfo;

    @Before
    public void setUp() {
        list = new ArrayList<>();
        taskInfo = new TaskInfo(1, "test", "testNotes");
        list.add(taskInfo);

    }

    @Test
    public void addTaskInfo() {
        given(taskInfoDAO.addTaskInfo(taskInfo)).willReturn(true);
        assertTrue(taskInfoService.addTaskInfo(taskInfo));
    }

    @Test
    public void findTasksInfo() {
        given(taskInfoDAO.findTasksInfo()).willReturn(list);
        assertEquals(taskInfoService.findTasksInfo(),list);
    }

    @Test
    public void findTaskInfo() {
        given(taskInfoDAO.findTaskInfo(1)).willReturn(Optional.of(taskInfo));
        assertEquals(taskInfoService.findTaskInfo(1),taskInfo);
    }

    @Test
    public void updateTaskInfo() {
        given(taskInfoDAO.updateTaskInfo(taskInfo)).willReturn(true);
        assertTrue(taskInfoService.updateTaskInfo(taskInfo));
    }

    @Test
    public void deleteTaskInfo() {
        given(taskInfoDAO.deleteTaskInfo(1)).willReturn(true);
        assertTrue(taskInfoService.deleteTaskInfo(1));
    }
}