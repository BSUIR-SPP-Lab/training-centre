package com.bsuir.trainingcenter.service;

import com.bsuir.trainingcenter.entity.Certificate;
import com.bsuir.trainingcenter.entity.view.CertificateInfoView;

import java.util.List;

public interface CertificateService {
    CertificateInfoView getCertificateInfo(long id);

    boolean addCertificate(Certificate certificate);

    List<CertificateInfoView> findCertificates();

    List<CertificateInfoView> findCertificates(long userId);

    boolean updateCertificate(Certificate certificate);

    boolean deleteCertificate(long certificateId);
}
