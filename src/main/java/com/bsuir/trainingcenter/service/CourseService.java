package com.bsuir.trainingcenter.service;

import com.bsuir.trainingcenter.entity.Course;

import java.util.List;

public interface CourseService {
    boolean addCourse(Course course);

    List<Course> findCourses();

    Course findCourse(long courseId);

    boolean updateCourse(Course course);

    boolean deleteCourse(long courseId);
}
