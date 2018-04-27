package com.bsuir.trainingcenter.controller;

import com.bsuir.trainingcenter.entity.Certificate;
import com.bsuir.trainingcenter.entity.view.CertificateInfoView;
import com.bsuir.trainingcenter.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/certificate")
public class CertificateController {

    private final CertificateService service;

    @Autowired
    public CertificateController(CertificateService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity addCertificate(@RequestBody Certificate certificate){
        if(service.addCertificate(certificate)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<CertificateInfoView>> findCertificates(){
        return ResponseEntity.ok(service.findCertificates());
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<CertificateInfoView>> findCertificates(@PathVariable long userId){
        return ResponseEntity.ok(service.findCertificates(userId));
    }

    @PostMapping("/update")
    public ResponseEntity updateCertificate(@RequestBody Certificate certificate){
        if(service.updateCertificate(certificate)){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin
    @DeleteMapping("/delete/{certificateId}")
    public ResponseEntity deleteCertificate(@PathVariable long certificateId){
        if(service.deleteCertificate(certificateId)){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
}
