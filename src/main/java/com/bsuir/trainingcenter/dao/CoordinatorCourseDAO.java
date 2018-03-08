package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Course;

import java.util.List;

public interface CoordinatorCourseDAO {

    List<Course> findCoordinatorCourses(long coordinatorId);

}
