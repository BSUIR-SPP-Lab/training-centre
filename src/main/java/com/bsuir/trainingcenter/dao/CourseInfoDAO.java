package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.CourseInfo;

import java.util.List;

public interface CourseInfoDAO {

    boolean addCourseInfo(CourseInfo courseInfo);

    List<CourseInfo> findCourseInfos();

    CourseInfo findCourseInfo(long id);

    boolean updateCourseInfo(CourseInfo courseInfo);

    boolean deleteCourseInfo(long id);
    
}
