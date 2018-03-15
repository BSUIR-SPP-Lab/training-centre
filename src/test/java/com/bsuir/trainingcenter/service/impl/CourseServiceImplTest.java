package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.CourseDAO;
import com.bsuir.trainingcenter.entity.Course;
import com.bsuir.trainingcenter.entity.view.CourseView;
import com.bsuir.trainingcenter.service.CourseService;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CourseServiceImplTest {

    @MockBean
    public CourseDAO courseDAO;

    @Autowired
    public CourseService courseService;

    List<Course> list;
    Course course;
    List<CourseView> listResult;
    CourseView courseView;

    @Before
    public void setUp(){
        list = new ArrayList<>();
        LocalDateTime l = LocalDateTime.now();
        course = new Course(1,1,1, l,l);
        list.add(course);
        listResult = new ArrayList<>();
        courseView = new CourseView(1,1,1, l.toString(),l.toString());
        listResult.add(courseView);

    }
    @Test
    public void addCourse() {
         given(courseDAO.addCourse(course)).willReturn(true);
         assertTrue(courseService.addCourse(courseView));
    }

    @Test
    public void findCourses() {

        given(courseDAO.findCourses()).willReturn(list);
        assertEquals(courseService.findCourses(),listResult);
    }

    @Test
    public void findCourse() {
        given(courseDAO.findCourse(1)).willReturn(course);
        assertEquals(courseService.findCourse(1),courseView);
    }

    @Test
    public void updateCourse() {
        given(courseDAO.updateCourse(course)).willReturn(true);
        assertTrue(courseService.updateCourse(courseView));
    }

    @Test
    public void deleteCourse() {
        given(courseDAO.deleteCourse(1)).willReturn(true);
        assertTrue(courseService.deleteCourse(1));
    }
}