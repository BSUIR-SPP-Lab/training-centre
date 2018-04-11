package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.TaskDAO;
import com.bsuir.trainingcenter.entity.Task;
import com.bsuir.trainingcenter.entity.view.TaskView;
import com.bsuir.trainingcenter.service.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskServiceImplTest {

    @MockBean
    public TaskDAO taskDAO;

    @Autowired
    public TaskService taskService;

    List<Task> list;
    Task task;
    List<TaskView> listResult;
    TaskView taskView;

    @Before
    public void setUp() {
        list = new ArrayList<>();
        LocalDateTime l = LocalDateTime.now();
        task = new Task(1, 1,1,1, l);
        list.add(task);
        listResult = new ArrayList<>();
        taskView = new TaskView(1, 1,1,1,l.toString());
        listResult.add(taskView);

    }

    @Test
    public void addTask() {
        given(taskDAO.addTask(task)).willReturn(true);
        assertTrue(taskService.addTask(taskView));
    }

    @Test
    public void findTasks() {
        given(taskDAO.findTasks()).willReturn(list);
        assertEquals(taskService.findTasks(),listResult);
    }

    @Test
    public void findTask() {
        given(taskDAO.findTask(1)).willReturn(Optional.of(task));
        assertEquals(taskService.findTask(1),taskView);
    }

    @Test
    public void updateTask() {
        given(taskDAO.updateTask(task)).willReturn(true);
        assertTrue(taskService.updateTask(taskView));
    }

    @Test
    public void deleteTask() {
        given(taskDAO.updateTask(task)).willReturn(true);
        assertTrue(taskService.updateTask(taskView));
    }
}