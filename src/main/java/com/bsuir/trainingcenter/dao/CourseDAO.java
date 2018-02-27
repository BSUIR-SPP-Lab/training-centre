package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Course;

import java.util.List;

public interface CourseDAO {

    boolean addCourse(Course course);

    List<Course> findCourses();

    Course findCourse(long id);

    boolean updateCourse(Course course);

    boolean deleteCourse(long id);
    
}
