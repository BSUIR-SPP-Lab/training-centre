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
    DocServiceImpl docServiceImpl = new DocServiceImpl();

    @GetMapping("/pdfCertificate/{id}")
    public ResponseEntity<Resource> pdfCertificate(@PathVariable int id){
        Resource body = docServiceImpl.generatePdfCertificate(id);

        if (body!=null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + "CertificatePDF.pdf" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/xlsCertificate/{id}")
    public ResponseEntity<Resource> xlsCertificate(@PathVariable int id){
        Resource body = docServiceImpl.generateXLSCertificate(id);

        if (body!=null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + "CertificateXLS.xls" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/csvCertificate/{id}")
    public ResponseEntity<Resource> csvCertificate(@PathVariable int id){
        Resource body = docServiceImpl.generateCSVCertificate(id);

        if (body!=null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + "CertificateCSV.csv" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
