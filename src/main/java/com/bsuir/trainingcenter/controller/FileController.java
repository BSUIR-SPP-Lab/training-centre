package com.bsuir.trainingcenter.controller;

import com.bsuir.trainingcenter.service.impl.FileCretor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class FileController {
    @Autowired
    FileCretor fileCretor = new FileCretor();

    @GetMapping("/file")
    public ResponseEntity<Resource> find(){
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + "Certeficate.pdf" + "\"").body(fileCretor.find());
    }
}
