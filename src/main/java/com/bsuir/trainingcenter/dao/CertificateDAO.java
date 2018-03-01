package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Certificate;

import java.util.List;

public interface CertificateDAO {

    boolean addCertificate(Certificate certificate);

    List<Certificate> findCertificates();

    Certificate findCertificate(long certificateId);

    boolean updateCertificate(Certificate certificate);

    boolean deleteCertificate(long certificateId);
    
}
