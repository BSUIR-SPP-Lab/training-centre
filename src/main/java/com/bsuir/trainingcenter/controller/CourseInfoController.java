package com.bsuir.trainingcenter.controller;

import com.bsuir.trainingcenter.entity.CourseInfo;
import com.bsuir.trainingcenter.service.CourseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courseInfo")
public class CourseInfoController {
    @Autowired
    private CourseInfoService courseInfoService;


    @PostMapping("/add")
    public ResponseEntity addCourseInfo(@RequestBody CourseInfo courseInfo){
        ResponseEntity response;
        if(courseInfoService.addCourseInfo(courseInfo)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseInfo>> findCoursesInfo(){
        return new ResponseEntity<>(courseInfoService.findCoursesInfo(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseInfo> findCourseInfo(@PathVariable Long id){
        ResponseEntity response;
        CourseInfo course = courseInfoService.findCourseInfo(id);
        if(course!=null){
            response=new ResponseEntity<>(course, HttpStatus.OK);
        }else {
            response=new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @PostMapping("/update/{id}")
    public ResponseEntity updateCourseInfo(@PathVariable Long id,@RequestBody CourseInfo courseInfo){
        ResponseEntity response;
        courseInfo.setCourseInfoId(id);
        if(courseInfoService.updateCourseInfo(courseInfo)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourseInfo(@PathVariable Long id){
        ResponseEntity response;
        if(courseInfoService.deleteCourseInfo(id)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
