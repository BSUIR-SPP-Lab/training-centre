package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.ApplicationDAO;
import com.bsuir.trainingcenter.entity.Application;
import com.bsuir.trainingcenter.entity.ApplicationWithInfo;
import com.bsuir.trainingcenter.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationDAO dao;

    @Autowired
    public ApplicationServiceImpl(ApplicationDAO dao) {
        this.dao = dao;
    }

    @Override
    public boolean addApplication(Application application) {
        return dao.addApplication(application);
    }

    @Override
    public List<Application> findApplications() {
        return dao.findApplications();
    }

    @Override
    public List<ApplicationWithInfo> findApplicationsByCourse(long courseId) {
        return dao.findApplicationsByCourse(courseId);
    }

    @Override
    public Application findApplication(long applicationId) {
        Optional<Application> app = dao.findApplication(applicationId);
        return app.orElse(null);
    }

    @Override
    public boolean updateApplication(Application application) {
        return dao.updateApplication(application);
    }

    @Override
    public boolean deleteApplication(long applicationId) {
        return dao.deleteApplication(applicationId);
    }
}
