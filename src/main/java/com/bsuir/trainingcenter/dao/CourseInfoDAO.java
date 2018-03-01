package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.CourseInfo;

import java.util.List;

public interface CourseInfoDAO {

    boolean addCourseInfo(CourseInfo courseInfo);

    List<CourseInfo> findCoursesInfo();

    CourseInfo findCourseInfo(long courseInfoId);

    boolean updateCourseInfo(CourseInfo courseInfo);

    boolean deleteCourseInfo(long courseInfoId);
    
}
