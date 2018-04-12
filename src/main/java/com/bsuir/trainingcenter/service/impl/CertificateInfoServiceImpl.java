package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.*;
import com.bsuir.trainingcenter.entity.*;
import com.bsuir.trainingcenter.entity.view.CertificateInfoView;
import com.bsuir.trainingcenter.service.CertificateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.UserDataHandler;

import java.util.Optional;

@Service
public class CertificateInfoServiceImpl implements CertificateInfoService {

    private final CertificateDAO certificateDAO;

    private final UserDAO userDAO;

    private final CourseDAO courseDAO;

    private final GroupDAO groupDAO;

    @Autowired
    public CertificateInfoServiceImpl(CertificateDAO certificateDAO, UserDAO userDAO, CourseDAO courseDAO, GroupDAO groupDAO) {
        this.certificateDAO = certificateDAO;
        this.userDAO = userDAO;
        this.courseDAO = courseDAO;
        this.groupDAO = groupDAO;
    }

    @Override
    public CertificateInfoView getCertificateInfo(int id) {
        Optional<Certificate> certificate= certificateDAO.findCertificate(id);
        if(certificate.isPresent()){
            CertificateInfoView view = new CertificateInfoView();
            view.setCertificateId(certificate.get().getCertificateId());

            User user = userDAO.findUser(certificate.get().getStudentId()).get();
            view.setFirstName(user.getFirstName());
            view.setLastName(user.getLastName());

            Group group = groupDAO.findGroup(certificate.get().getGroupId()).get();
            CourseWithInfo course = courseDAO.findCourseWithInfo(group.getCourseId()).get();

            view.setName(course.getName());
            view.setStart(course.getStart().toString());
            view.setEnd(course.getEnd().toString());
            return view;
        }
        return null;
    }
}
