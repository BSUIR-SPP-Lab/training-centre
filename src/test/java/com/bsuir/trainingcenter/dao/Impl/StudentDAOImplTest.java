package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.config.TestConfig;
import com.bsuir.trainingcenter.entity.Student;
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
public class StudentDAOImplTest {

    private StudentDAOImpl studentDAO = new StudentDAOImpl();

    @Autowired
    private void setDataSource(DataSource dataSource) {
        studentDAO.setDataSource(dataSource);
    }

    @Test
    @Rollback
    public void addStudent() {
        Student student = new Student(49);
        assertEquals(studentDAO.findStudents().size(), 10);
    }

    @Test
    public void findStudents() {
        assertEquals(studentDAO.findStudents().size(), 10);
    }

    @Test
    public void findStudent() {
        Student student = new Student(61);
        assertEquals(studentDAO.findStudent(61), student);
    }

    @Test
    @Rollback
    public void updateStudent() {
        Student student = new Student(61);
        assertTrue(studentDAO.updateStudent(student));
        assertEquals(studentDAO.findStudent(61), student);
    }

    @Test
    @Rollback
    public void deleteStudent() {
        assertTrue(studentDAO.deleteStudent(61));
        assertEquals(studentDAO.findStudents().size(), 9);
    }

}
