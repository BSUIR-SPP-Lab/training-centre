package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.CourseDAO;
import com.bsuir.trainingcenter.entity.Course;
import com.bsuir.trainingcenter.entity.CourseWithInfo;
import com.bsuir.trainingcenter.entity.view.CourseView;
import com.bsuir.trainingcenter.entity.view.CourseWithInfoView;
import com.bsuir.trainingcenter.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseDAO courseDAO;

    @Autowired
    public CourseServiceImpl(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    public boolean addCourse(CourseView courseView) {
        Course course = new Course(courseView.getCourseId(), courseView.getCourseInfoId(), courseView.getCoordinatorId(), LocalDateTime.parse(courseView.getStart()), LocalDateTime.parse(courseView.getEnd()));
        return courseDAO.addCourse(course);
    }

    @Override
    public List<CourseView> findCourses() {
        List<CourseView> list = new ArrayList<>();
        for (Course course : courseDAO.findCourses()) {
            list.add(new CourseView(course.getCourseId(), course.getCourseInfoId(), course.getCoordinatorId(), course.getStart().toString(), course.getEnd().toString()));
        }
        return list;
    }

    @Override
    public CourseView findCourse(long courseId) {
        Optional<Course> foundedCourse = courseDAO.findCourse(courseId);
        CourseView view = null;
        if(foundedCourse.isPresent()){
            Course course = foundedCourse.get();
            view=new CourseView(course.getCourseId(), course.getCourseInfoId(), course.getCoordinatorId(), course.getStart().toString(), course.getEnd().toString());
        }
        return view;
    }

    @Override
    public boolean updateCourse(CourseView courseView) {

        Course course = new Course(courseView.getCourseId(), courseView.getCourseInfoId(), courseView.getCoordinatorId(), LocalDateTime.parse(courseView.getStart()), LocalDateTime.parse(courseView.getEnd()));
        return courseDAO.updateCourse(course);
    }

    @Override
    public boolean deleteCourse(long courseId) {
        return courseDAO.deleteCourse(courseId);
    }

    @Override
    public List<CourseWithInfo> findCoursesWithInfo() {
        return courseDAO.findCoursesWithInfo();
    }

    @Override
    public CourseWithInfoView findCourseWithInfo(long courseId) {
        Optional<CourseWithInfo> foundedCourse = courseDAO.findCourseWithInfo(courseId);
        CourseWithInfoView view = null;
        if(foundedCourse.isPresent()){
            CourseWithInfo course = foundedCourse.get();
            view=new CourseWithInfoView(course.getCourseId(), course.getCourseInfoId(), course.getCoordinatorId(), course.getStart().toString(), course.getEnd().toString(),course.getName(),course.getDescription());
        }
        return view;
    }

    @Override
    public List<CourseWithInfo> findCoursesWithInfoByUserId(long userId) {
        return courseDAO.findCoursesWithInfoByUserId(userId);
    }
}
