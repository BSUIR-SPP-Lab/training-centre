package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.CourseInfoDAO;
import com.bsuir.trainingcenter.entity.CourseInfo;
import com.bsuir.trainingcenter.service.CourseInfoService;
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
public class CourseInfoServiceImplTest {

    @MockBean
    public CourseInfoDAO courseInfoDAO;

    @Autowired
    public CourseInfoService courseInfoService;

    @Test
    public void addCourseInfo() {
        CourseInfo courseInfo = new CourseInfo();
        given(courseInfoDAO.addCourseInfo(courseInfo)).willReturn(true);
        assertTrue(courseInfoService.addCourseInfo(courseInfo));
    }

    @Test
    public void findCoursesInfo() {
        List<CourseInfo> list = new ArrayList<>();
        CourseInfo courseInfo = new CourseInfo();
        list.add(courseInfo);
        given(courseInfoDAO.findCoursesInfo()).willReturn(list);
        assertEquals(courseInfoService.findCoursesInfo(),list);
    }

    @Test
    public void findCourseInfo() {
        CourseInfo courseInfo = new CourseInfo();
        given(courseInfoDAO.findCourseInfo(1)).willReturn(Optional.of(courseInfo));
        assertEquals(courseInfoService.findCourseInfo(1),courseInfo);
    }

    @Test
    public void updateCourseInfo() {
        CourseInfo courseInfo = new CourseInfo();
        given(courseInfoDAO.updateCourseInfo(courseInfo)).willReturn(true);
        assertTrue(courseInfoService.updateCourseInfo(courseInfo));

    }

    @Test
    public void deleteCourseInfo() {
        CourseInfo courseInfo = new CourseInfo();
        given(courseInfoDAO.deleteCourseInfo(1)).willReturn(true);
        assertTrue(courseInfoService.deleteCourseInfo(1));
    }
}