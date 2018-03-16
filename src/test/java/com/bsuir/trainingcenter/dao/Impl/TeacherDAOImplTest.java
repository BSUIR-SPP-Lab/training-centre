package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.config.TestConfig;
import com.bsuir.trainingcenter.entity.Teacher;
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
public class TeacherDAOImplTest {

    private TeacherDAOImpl teacherDAO = new TeacherDAOImpl();

    @Autowired
    private void setDataSource(DataSource dataSource) {
        teacherDAO.setDataSource(dataSource);
    }

    @Test
    @Rollback
    public void addTeacher() {
        Teacher teacher = new Teacher(59, 2);
        assertTrue(teacherDAO.addTeacher(teacher));
        assertEquals(teacherDAO.findTeachers().size(), 12);
    }

    @Test
    public void findTeachers() {
        assertEquals(teacherDAO.findTeachers().size(), 11);
    }

    @Test
    public void findGroupTeachers() {
        assertEquals(teacherDAO.findGroupTeachers(2).size(), 2);
    }

}
