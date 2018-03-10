package com.bsuir.trainingcenter.service;

import com.bsuir.trainingcenter.entity.view.CourseView;

import java.util.List;

public interface CourseService {
    boolean addCourse(CourseView course);

    List<CourseView> findCourses();

    CourseView findCourse(long courseId);

    boolean updateCourse(CourseView course);

    boolean deleteCourse(long courseId);
}
