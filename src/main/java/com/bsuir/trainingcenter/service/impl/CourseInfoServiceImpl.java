package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.CourseInfoDAO;
import com.bsuir.trainingcenter.entity.CourseInfo;
import com.bsuir.trainingcenter.service.CourseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseInfoServiceImpl implements CourseInfoService {

    private final CourseInfoDAO courseInfoDAO;

    @Autowired
    public CourseInfoServiceImpl(CourseInfoDAO courseInfoDAO) {
        this.courseInfoDAO = courseInfoDAO;
    }

    @Override
    public boolean addCourseInfo(CourseInfo courseInfo) {
        return courseInfoDAO.addCourseInfo(courseInfo);
    }

    @Override
    public List<CourseInfo> findCoursesInfo() {
        return courseInfoDAO.findCoursesInfo();
    }

    @Override
    public CourseInfo findCourseInfo(long courseInfoId) {
        return courseInfoDAO.findCourseInfo(courseInfoId);
    }

    @Override
    public boolean updateCourseInfo(CourseInfo courseInfo) {
        return courseInfoDAO.updateCourseInfo(courseInfo);
    }

    @Override
    public boolean deleteCourseInfo(long courseInfoId) {
        return courseInfoDAO.deleteCourseInfo(courseInfoId);
    }
}
