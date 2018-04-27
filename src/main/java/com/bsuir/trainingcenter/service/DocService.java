package com.bsuir.trainingcenter.service;

import org.springframework.core.io.Resource;

public interface DocService {
    Resource generatePdfCertificate(long id);

    Resource generateXLSCertificate(long id);

    Resource generateCSVCertificate(long id);


    Resource generatePdfUsersOnCourse(long id, boolean finish);

    Resource generateXLSUsersOnCourse(long id, boolean finish);

    Resource generateCSVUsersOnCourse(long id, boolean finish);

    Resource generateXLSUserSolution(long userId, long courseId);

    Resource generateCSVUserSolution(long userId, long courseId);

    Resource generatePdfTasksByGroup(long groupId);

    Resource generateXLSTasksByGroup(long groupId);

    Resource generateCSVTasksByGroup(long groupId);

    Resource generatePdfUsersInGroup(long grouoId);

    Resource generatePdfUserSolutions(long userId, long courseId);

    Resource generateXLSUsersInGroup(long groupId);

    Resource generateCSVUsersInGroup(long groupId);
}
