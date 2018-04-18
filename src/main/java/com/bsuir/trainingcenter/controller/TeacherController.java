package com.bsuir.trainingcenter.controller;

import com.bsuir.trainingcenter.entity.Teacher;
import com.bsuir.trainingcenter.service.TeatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final TeatcherService service;

    @Autowired
    public TeacherController(TeatcherService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity addTeacher(@RequestBody Teacher teacher){
        if(service.addTeacher(teacher)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<Teacher>> findTeachers(){
        return ResponseEntity.ok(service.findTeachers());
    }

    @GetMapping("/get/{groupId}")
    public ResponseEntity<List<Teacher>> findGroupTeachers(@PathVariable long groupId){
        return ResponseEntity.ok(service.findGroupTeachers(groupId));
    }
}
