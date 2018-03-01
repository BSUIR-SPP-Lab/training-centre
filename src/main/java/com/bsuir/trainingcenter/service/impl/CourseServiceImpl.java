package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.CourseDAO;
import com.bsuir.trainingcenter.entity.Course;
import com.bsuir.trainingcenter.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDAO courseDAO;

    @Override
    public boolean addCourse(Course course) {
        return courseDAO.addCourse(course);
    }

    @Override
    public List<Course> findCourses() {
        return courseDAO.findCourses();
    }

    @Override
    public Course findCourse(long courseId) {
        return courseDAO.findCourse(courseId);
    }

    @Override
    public boolean updateCourse(Course course) {
        return courseDAO.updateCourse(course);
    }

    @Override
    public boolean deleteCourse(long courseId) {
        return courseDAO.deleteCourse(courseId);
    }
}
