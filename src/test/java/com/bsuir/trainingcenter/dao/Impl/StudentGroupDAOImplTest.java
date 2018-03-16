package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.config.TestConfig;
import com.bsuir.trainingcenter.entity.StudentGroup;
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
public class StudentGroupDAOImplTest {

    private StudentGroupDAOImpl studentGroupDAO = new StudentGroupDAOImpl();

    @Autowired
    private void setDataSource(DataSource dataSource) {
        studentGroupDAO.setDataSource(dataSource);
    }

    @Test
    @Rollback
    public void addStudentGroup() {
        StudentGroup studentGroup = new StudentGroup(67, 2, false);
        assertTrue(studentGroupDAO.addStudentGroup(studentGroup));
        assertEquals(studentGroupDAO.findStudentGroups().size(), 20);
    }

    @Test
    public void findStudentGroups() {
        assertEquals(studentGroupDAO.findStudentGroups().size(), 19);
    }

    @Test
    @Rollback
    public void updateStudentGroup() {
        StudentGroup studentGroup = new StudentGroup(67, 13, false);
        assertTrue(studentGroupDAO.updateStudentGroup(studentGroup));
    }

    @Test
    @Rollback
    public void deleteStudentGroup() {
        assertTrue(studentGroupDAO.deleteStudentGroup(67, 13));
        assertEquals(studentGroupDAO.findStudentGroups().size(), 18);
    }

}
