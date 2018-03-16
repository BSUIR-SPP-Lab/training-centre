package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.config.TestConfig;
import com.bsuir.trainingcenter.entity.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@SpringBootTest
@Transactional
public class TaskDAOImplTest {

    private TaskDAOImpl taskDAO = new TaskDAOImpl();

    @Autowired
    private void setDataSource(DataSource dataSource) {
        taskDAO.setDataSource(dataSource);
    }

    @Test
    @Rollback
    public void addTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Task task = new Task(55, 7, 5,
                LocalDateTime.parse("2018-01-01 00:00", formatter));
        assertTrue(taskDAO.addTask(task));
        assertEquals(taskDAO.findTasks().size(), 12);
    }

    @Test
    public void findTasks() {
        assertEquals(taskDAO.findTasks().size(), 11);
    }

    @Test
    public void findTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Task task = new Task(26, 55, 7, 3,
                LocalDateTime.parse("0034-03-21 04:01", formatter));
        assertEquals(taskDAO.findTask(26), task);
    }

    @Test
    @Rollback
    public void updateTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Task task = new Task(26, 55, 7, 5,
                LocalDateTime.parse("2018-01-01 00:00", formatter));
        assertTrue(taskDAO.updateTask(task));
        assertEquals(taskDAO.findTask(26), task);
    }

    @Test
    @Rollback
    public void deleteTask() {
        assertTrue(taskDAO.deleteTask(14));
        assertEquals(taskDAO.findTasks().size(), 10);
    }

}
