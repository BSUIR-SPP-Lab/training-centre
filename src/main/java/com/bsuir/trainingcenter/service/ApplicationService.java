package com.bsuir.trainingcenter.service;

import com.bsuir.trainingcenter.entity.Application;
import com.bsuir.trainingcenter.entity.ApplicationWithInfo;

import java.util.List;

public interface ApplicationService {
    boolean addApplication(Application application);

    List<Application> findApplications();

    List<ApplicationWithInfo> findApplicationsByCourse(long courseId);

    Application findApplication(long applicationId);

    boolean updateApplication(Application application);

    boolean deleteApplication(long applicationId);
}
