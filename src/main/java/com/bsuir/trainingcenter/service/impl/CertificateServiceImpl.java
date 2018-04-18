package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.CertificateDAO;
import com.bsuir.trainingcenter.dao.CourseDAO;
import com.bsuir.trainingcenter.dao.GroupDAO;
import com.bsuir.trainingcenter.dao.UserDAO;
import com.bsuir.trainingcenter.entity.Certificate;
import com.bsuir.trainingcenter.entity.CourseWithInfo;
import com.bsuir.trainingcenter.entity.Group;
import com.bsuir.trainingcenter.entity.User;
import com.bsuir.trainingcenter.entity.view.CertificateInfoView;
import com.bsuir.trainingcenter.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateDAO certificateDAO;

    private final UserDAO userDAO;

    private final CourseDAO courseDAO;

    private final GroupDAO groupDAO;

    @Autowired
    public CertificateServiceImpl(CertificateDAO certificateDAO, UserDAO userDAO, CourseDAO courseDAO, GroupDAO groupDAO) {
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

    @Override
    public boolean addCertificate(Certificate certificate) {
        return certificateDAO.addCertificate(certificate);
    }

    @Override
    public List<Certificate> findCertificates() {
        return certificateDAO.findCertificates();
    }

    @Override
    public List<Certificate> findCertificates(long userId) {
        return certificateDAO.findCertificates(userId);
    }

    @Override
    public boolean updateCertificate(Certificate certificate) {
        return certificateDAO.updateCertificate(certificate);
    }

    @Override
    public boolean deleteCertificate(long certificateId) {
        return certificateDAO.deleteCertificate(certificateId);
    }
}
