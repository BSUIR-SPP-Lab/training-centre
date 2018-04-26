package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Application;
import com.bsuir.trainingcenter.entity.ApplicationWithInfo;

import java.util.List;
import java.util.Optional;

public interface ApplicationDAO {

    boolean addApplication(Application application);

    List<ApplicationWithInfo> findApplications();

    List<ApplicationWithInfo> findApplicationsByCourse(long courseId);

    Optional<Application> findApplication(long applicationId);

    boolean isApplicationfind(long courseId, long studentId);

    boolean updateApplication(Application application);

    boolean deleteApplication(long applicationId);

}
