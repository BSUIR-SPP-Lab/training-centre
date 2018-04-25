package com.bsuir.trainingcenter.service;

import org.springframework.core.io.Resource;

public interface DocService {
    Resource generatePdfCertificate(long id);

    Resource generateXLSCertificate(long id);

    Resource generateCSVCertificate(long id);


    Resource generatePdfUsersOnCourse(long id, boolean finish);

    Resource generateXLSUsersOnCourse(long id, boolean finish);

    Resource generateCSVUsersOnCourse(long id, boolean finish);
}
