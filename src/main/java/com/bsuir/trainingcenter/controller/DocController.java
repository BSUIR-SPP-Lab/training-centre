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
    public ResponseEntity<Resource> pdfCertificate(@PathVariable long id){
        Resource body = docServiceImpl.generatePdfCertificate(id);

        if (body!=null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + "CertificatePDF.pdf" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/xlsCertificate/{id}")
    public ResponseEntity<Resource> xlsCertificate(@PathVariable long id){
        Resource body = docServiceImpl.generateXLSCertificate(id);

        if (body!=null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + "CertificateXLS.xls" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/csvCertificate/{id}")
    public ResponseEntity<Resource> csvCertificate(@PathVariable long id){
        Resource body = docServiceImpl.generateCSVCertificate(id);

        if (body!=null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + "CertificateCSV.csv" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/pdfUserListCompleteCourse/{id}")
    public ResponseEntity<Resource> pdfUserListCompleteCourse(@PathVariable long id){
        Resource body = docServiceImpl.generatePdfUsersOnCourse(id,true);

        if (body!=null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + "UserListCompleteCoursePDF.pdf" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/xlsUserListCompleteCourse/{id}")
    public ResponseEntity<Resource> xlsUserListCompleteCourse(@PathVariable long id){
        Resource body = docServiceImpl.generateXLSUsersOnCourse(id,true);

        if (body!=null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + "UserListCompleteCourseXLS.xls" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/csvUserListCompleteCourse/{id}")
    public ResponseEntity<Resource> csvUserListCompleteCourse(@PathVariable long id){
        Resource body = docServiceImpl.generateCSVUsersOnCourse(id,true);

        if (body!=null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + "UserListCompleteCourseCSV.csv" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/pdfUserListInGroup/{id}")
    public ResponseEntity<Resource> pdfUserListInGroup(@PathVariable long id){
        Resource body = docServiceImpl.generatePdfUsersInGroup(id);

        if (body!=null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + "UserListInGroup"+id+".pdf" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/xlsUserListInGroup/{id}")
    public ResponseEntity<Resource> xlsUserListInGroup(@PathVariable long id){
        Resource body = docServiceImpl.generateXLSUsersInGroup(id);

        if (body!=null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + "UserListInGroup"+id+".xls" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/csvUserListInGroup/{id}")
    public ResponseEntity<Resource> csvUserListInGroup(@PathVariable long id){
        Resource body = docServiceImpl.generateCSVUsersInGroup(id);

        if (body!=null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + "UserListInGroup"+id+".csv" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
