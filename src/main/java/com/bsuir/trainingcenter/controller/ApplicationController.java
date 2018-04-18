package com.bsuir.trainingcenter.controller;

import com.bsuir.trainingcenter.entity.Application;
import com.bsuir.trainingcenter.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationService service;

    @Autowired
    public ApplicationController(ApplicationService service) {
        this.service = service;
    }


    @PostMapping("/add")
    public ResponseEntity addApplication(@RequestBody Application application) {
        if (service.addApplication(application)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<Application>> findApplications() {
        return ResponseEntity.ok(service.findApplications());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Application> findApplication(@PathVariable long id) {
        Application app = service.findApplication(id);
        if (app != null) {
            return ResponseEntity.ok(app);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity updateApplication(@RequestBody Application application) {
        if (service.updateApplication(application)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }

    }


    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteApplication(@PathVariable long id) {
        if(service.deleteApplication(id)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }

    }
}
