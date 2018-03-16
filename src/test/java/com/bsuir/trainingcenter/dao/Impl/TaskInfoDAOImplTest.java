package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.config.TestConfig;
import com.bsuir.trainingcenter.entity.TaskInfo;
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
public class TaskInfoDAOImplTest {

    private TaskInfoDAOImpl taskInfoDAO = new TaskInfoDAOImpl();

    @Autowired
    private void setDataSource(DataSource dataSource) {
        taskInfoDAO.setDataSource(dataSource);
    }

    @Test
    @Rollback
    public void addTaskInfo() {
        TaskInfo taskInfo = new TaskInfo("", "");
        assertTrue(taskInfoDAO.addTaskInfo(taskInfo));
        assertEquals(taskInfoDAO.findTasksInfo().size(), 14);
    }

    @Test
    public void findTasksInfo() {
        assertEquals(taskInfoDAO.findTasksInfo().size(), 13);
    }

    @Test
    public void findTaskInfo() {
        TaskInfo taskInfo = new TaskInfo(2, "Проверка знаний по курсу тестирования. вопрос 1",
                "Что такое тест?");
        assertEquals(taskInfoDAO.findTaskInfo(2), taskInfo);
    }

    @Test
    @Rollback
    public void updateTaskInfo() {
        TaskInfo taskInfo = new TaskInfo(2, "Проверка знаний по курсу тестирования. вопрос 0001",
                "Что такое тест?");
        assertTrue(taskInfoDAO.updateTaskInfo(taskInfo));
        assertEquals(taskInfoDAO.findTaskInfo(2), taskInfo);
    }

    @Test
    @Rollback
    public void deleteTaskInfo() {
        assertTrue(taskInfoDAO.deleteTaskInfo(5));
        assertEquals(taskInfoDAO.findTasksInfo().size(), 12);
    }

}
