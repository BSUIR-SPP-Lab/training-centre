package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Course;
import com.bsuir.trainingcenter.entity.CourseWithInfo;

import java.util.List;
import java.util.Optional;

public interface CourseDAO {

    boolean addCourse(Course course);

    List<Course> findCourses();

    List<CourseWithInfo> findCoursesWithInfo();

    List<CourseWithInfo> findCoursesWithInfoByUserId(long userId);

    Optional<Course> findCourse(long courseId);

    Optional<CourseWithInfo> findCourseWithInfo(long courseId);

    boolean updateCourse(Course course);

    boolean deleteCourse(long courseId);

}
