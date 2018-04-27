package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Application;

import java.util.List;

public interface CourseApplicationDAO {

    List<Application> findApplicationsToCourse(long courseId);

}
