package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.config.TestConfig;
import com.bsuir.trainingcenter.entity.CourseInfo;
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
public class CourseInfoDAOImplTest {

    private CourseInfoDAOImpl courseInfoDAO = new CourseInfoDAOImpl();

    @Autowired
    private void setDataSource(DataSource dataSource) {
        courseInfoDAO.setDataSource(dataSource);
    }

    @Test
    @Rollback
    public void addCourseInfo() {
        CourseInfo courseInfo = new CourseInfo("Я тестовое название курса", "Я тестовое описание курса");
        assertTrue(courseInfoDAO.addCourseInfo(courseInfo));
        assertEquals(12, courseInfoDAO.findCoursesInfo().size());
    }

    @Test
    public void findCoursesInfo() {
        assertEquals(11, courseInfoDAO.findCoursesInfo().size());
    }

    @Test
    public void findCourseInfo() {
        CourseInfo courseInfo = new CourseInfo(5, "Курс смекалки",
                "Курс развивающий вашу смекалку.");
        assertEquals(courseInfo, courseInfoDAO.findCourseInfo(5).get());
    }

    @Test
    public void findCourseInfoNegative() {
        assertEquals(false, courseInfoDAO.findCourseInfo(20).isPresent());
    }

    @Test
    @Rollback
    public void updateCourseInfo() {
        CourseInfo courseInfo = new CourseInfo(5, "Курс смекалки",
                "Курс развивающий вашу смекалистость.");
        assertTrue(courseInfoDAO.updateCourseInfo(courseInfo));
        assertEquals(courseInfo, courseInfoDAO.findCourseInfo(5).get());
    }

    @Test
    @Rollback
    public void deleteCourseInfo() {
        assertTrue(courseInfoDAO.deleteCourseInfo(5));
        assertEquals(10, courseInfoDAO.findCoursesInfo().size());
    }

}
