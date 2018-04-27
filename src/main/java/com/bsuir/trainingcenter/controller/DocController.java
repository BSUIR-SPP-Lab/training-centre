package com.bsuir.trainingcenter.controller;

import com.bsuir.trainingcenter.service.impl.DocServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class DocController {
    @Autowired
    DocServiceImpl docServiceImpl;

    @GetMapping("/pdfCertificate/{id}")
    public ResponseEntity<Resource> pdfCertificate(@PathVariable long id) {
        Resource body = docServiceImpl.generatePdfCertificate(id);

        if (body != null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "CertificatePDF.pdf" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/xlsCertificate/{id}")
    public ResponseEntity<Resource> xlsCertificate(@PathVariable long id) {
        Resource body = docServiceImpl.generateXLSCertificate(id);

        if (body != null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "CertificateXLS.xls" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/csvCertificate/{id}")
    public ResponseEntity<Resource> csvCertificate(@PathVariable long id) {
        Resource body = docServiceImpl.generateCSVCertificate(id);

        if (body != null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "CertificateCSV.csv" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/pdfUserListCourse/{id}")
    public ResponseEntity<Resource> pdfUserListCourse(@PathVariable long id) {
        Resource body = docServiceImpl.generatePdfUsersOnCourse(id);

        if (body != null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "UserListCoursePDF.pdf" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/xlsUserListCourse/{id}")
    public ResponseEntity<Resource> xlsUserListCourse(@PathVariable long id) {
        Resource body = docServiceImpl.generateXLSUsersOnCourse(id);

        if (body != null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "UserListCourseXLS.xls" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/csvUserListCourse/{id}")
    public ResponseEntity<Resource> csvUserListCourse(@PathVariable long id) {
        Resource body = docServiceImpl.generateCSVUsersOnCourse(id);

        if (body != null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "UserListCourseCSV.csv" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/pdfUserListInGroup/{id}")
    public ResponseEntity<Resource> pdfUserListInGroup(@PathVariable long id) {
        Resource body = docServiceImpl.generatePdfUsersInGroup(id);

        if (body != null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "UserListInGroup" + id + ".pdf" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/xlsUserListInGroup/{id}")
    public ResponseEntity<Resource> xlsUserListInGroup(@PathVariable long id) {
        Resource body = docServiceImpl.generateXLSUsersInGroup(id);

        if (body != null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "UserListInGroup" + id + ".xls" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/csvUserListInGroup/{id}")
    public ResponseEntity<Resource> csvUserListInGroup(@PathVariable long id) {
        Resource body = docServiceImpl.generateCSVUsersInGroup(id);

        if (body != null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "UserListInGroup" + id + ".csv" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/pdfUserSolutions/{courseId}/{userId}")
    public ResponseEntity<Resource> pdfUserSolutions(@PathVariable long userId, @PathVariable long courseId) {
        Resource body = docServiceImpl.generatePdfUserSolutions(userId, courseId);

        if (body != null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "UserSolutionList" + userId + ".pdf" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/xlsUserSolutions/{courseId}/{userId}")
    public ResponseEntity<Resource> xlsUserSolutions(@PathVariable long userId, @PathVariable long courseId) {
        Resource body = docServiceImpl.generateXLSUserSolution(userId, courseId);

        if (body != null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "UserSolutionList" + userId + ".xls" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/csvUserSolutions/{courseId}/{userId}")
    public ResponseEntity<Resource> csvUserSolutions(@PathVariable long userId, @PathVariable long courseId) {
        Resource body = docServiceImpl.generateCSVUserSolution(userId, courseId);

        if (body != null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "UserSolutionList" + userId + ".csv" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/pdfGroupTasks/{groupId}")
    public ResponseEntity<Resource> pdfGroupTasks(@PathVariable long groupId) {
        Resource body = docServiceImpl.generatePdfTasksByGroup(groupId);

        if (body != null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "GroupTasks" + groupId + ".pdf" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/xlsGroupTasks/{groupId}")
    public ResponseEntity<Resource> xlsGroupTasks(@PathVariable long groupId) {
        Resource body = docServiceImpl.generateXLSTasksByGroup(groupId);

        if (body != null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "GroupTasks" + groupId + ".xls" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/csvGroupTasks/{groupId}")
    public ResponseEntity<Resource> csvGroupTasks(@PathVariable long groupId) {
        Resource body = docServiceImpl.generateCSVTasksByGroup(groupId);

        if (body != null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "GroupTasks" + groupId + ".csv" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
