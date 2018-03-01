package com.bsuir.trainingcenter.controller;

import com.bsuir.trainingcenter.entity.StudentGroup;
import com.bsuir.trainingcenter.service.StudentGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentGroup")
public class StudentGroupController {

    @Autowired
    private StudentGroupService studentGroupService;

    @PutMapping("/add")
    public ResponseEntity addStudentGroup(@RequestBody StudentGroup studentGroup){
        ResponseEntity response;
        if(studentGroupService.addStudentGroup(studentGroup)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentGroup>> findStudentGroups(){
        return new ResponseEntity<>(studentGroupService.findStudentGroups(), HttpStatus.OK);

    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateTask(@PathVariable Long id,@RequestBody StudentGroup studentGroup){
        ResponseEntity response;
        studentGroup.setStudentId(id);
        if(studentGroupService.updateStudentGroup(studentGroup)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }


}
