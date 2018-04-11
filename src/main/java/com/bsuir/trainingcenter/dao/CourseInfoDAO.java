package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.CourseInfo;

import java.util.List;
import java.util.Optional;

public interface CourseInfoDAO {

    boolean addCourseInfo(CourseInfo courseInfo);

    List<CourseInfo> findCoursesInfo();

    Optional<CourseInfo> findCourseInfo(long courseInfoId);

    boolean updateCourseInfo(CourseInfo courseInfo);

    boolean deleteCourseInfo(long courseInfoId);

}
