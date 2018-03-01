package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Application;

import java.util.List;

public interface ApplicationDAO {

    boolean addApplication(Application application);

    List<Application> findApplications();

    Application findApplication(long applicationId);

    boolean updateApplication(Application application);

    boolean deleteApplication(long applicationId);
    
}
