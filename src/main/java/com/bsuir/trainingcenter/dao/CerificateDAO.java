package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Certificate;

import java.util.List;

public interface CerificateDAO {

    boolean addCertificate(Certificate certificate);

    List<Certificate> findCertificates();

    Certificate findCertificate(long id);

    boolean updateCertificate(Certificate certificate);

    boolean deleteCertificate(long id);
    
}
