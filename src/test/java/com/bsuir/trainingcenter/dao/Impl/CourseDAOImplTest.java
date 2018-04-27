package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.config.TestConfig;
import com.bsuir.trainingcenter.entity.Course;
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
public class CourseDAOImplTest {

    private CourseDAOImpl courseDAO = new CourseDAOImpl();

    @Autowired
    private void setDataSource(DataSource dataSource) {
        courseDAO.setDataSource(dataSource);
    }

    @Test
    @Rollback
    public void addCourse() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Course course = new Course(9, 45,
                LocalDateTime.parse("2018-01-01 00:00", formatter),
                LocalDateTime.parse("2018-02-01 00:00", formatter));
        assertTrue(courseDAO.addCourse(course));
        assertEquals(13, courseDAO.findCourses().size());
    }

    @Test
    public void findCourses() {
        assertEquals(12, courseDAO.findCourses().size());
    }

    @Test
    public void findCourse() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Course course = new Course(9, 2, 50,
                LocalDateTime.parse("2012-02-12 00:00", formatter),
                LocalDateTime.parse("2013-02-18 00:00", formatter));
        assertEquals(course, courseDAO.findCourse(9).get());
    }

    @Test
    @Rollback
    public void updateCourse() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Course course = new Course(10, 9, 50,
                LocalDateTime.parse("2018-01-01 00:00", formatter),
                LocalDateTime.parse("2018-02-01 00:00", formatter));
        assertTrue(courseDAO.updateCourse(course));
        assertEquals(course, courseDAO.findCourse(10).get());
    }

    @Test
    @Rollback
    public void deleteCourse() {
        assertTrue(courseDAO.deleteCourse(5));
        assertEquals(11, courseDAO.findCourses().size());
    }

}
