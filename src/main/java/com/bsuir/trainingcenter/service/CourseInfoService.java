package com.bsuir.trainingcenter.service;

import com.bsuir.trainingcenter.entity.CourseInfo;

import java.util.List;

public interface CourseInfoService {

    boolean addCourseInfo(CourseInfo courseInfo);

    List<CourseInfo> findCoursesInfo();

    CourseInfo findCourseInfo(long courseInfoId);

    boolean updateCourseInfo(CourseInfo courseInfo);

    boolean deleteCourseInfo(long courseInfoId);

}
