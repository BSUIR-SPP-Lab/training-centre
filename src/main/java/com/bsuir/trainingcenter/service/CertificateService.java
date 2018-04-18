package com.bsuir.trainingcenter.service;

import com.bsuir.trainingcenter.entity.Certificate;
import com.bsuir.trainingcenter.entity.view.CertificateInfoView;

import java.util.List;

public interface CertificateService {
    CertificateInfoView getCertificateInfo(int id);

    boolean addCertificate(Certificate certificate);

    List<Certificate> findCertificates();

    List<Certificate> findCertificates(long userId);

    boolean updateCertificate(Certificate certificate);

    boolean deleteCertificate(long certificateId);
}
