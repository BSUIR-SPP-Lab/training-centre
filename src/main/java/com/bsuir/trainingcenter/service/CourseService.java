package com.bsuir.trainingcenter.service;

import com.bsuir.trainingcenter.entity.CourseWithInfo;
import com.bsuir.trainingcenter.entity.view.CourseView;
import com.bsuir.trainingcenter.entity.view.CourseWithInfoView;

import java.util.List;

public interface CourseService {
    boolean addCourse(CourseView course);

    List<CourseView> findCourses();

    CourseView findCourse(long courseId);

    boolean updateCourse(CourseView course);

    boolean deleteCourse(long courseId);

    List<CourseWithInfo> findCoursesWithInfo();

    CourseWithInfoView findCourseWithInfo(long courseId);
}