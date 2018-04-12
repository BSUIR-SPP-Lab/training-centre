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
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + "Certificate.pdf" + "\"").body(body);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
