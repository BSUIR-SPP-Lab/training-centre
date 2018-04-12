package com.bsuir.trainingcenter.service;

import org.springframework.core.io.Resource;

public interface DocService {
    Resource generatePdfCertificate(int id);
}
