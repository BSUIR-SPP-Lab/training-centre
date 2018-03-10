package com.bsuir.trainingcenter.controller;

import com.bsuir.trainingcenter.entity.view.CourseView;
import com.bsuir.trainingcenter.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @PostMapping("/add")
    public ResponseEntity addCourse(@RequestBody CourseView course){
        ResponseEntity response;
        if(courseService.addCourse(course)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseView>> findCourses(){
        return new ResponseEntity<>(courseService.findCourses(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseView> findCourse(@PathVariable Long id){
        ResponseEntity response;
        CourseView course = courseService.findCourse(id);
        if(course!=null){
            response=new ResponseEntity<>(course, HttpStatus.OK);
        }else {
            response=new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @PostMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable Long id,@RequestBody CourseView course){
        ResponseEntity response;
        course.setCourseId(id);
        if(courseService.updateCourse(course)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable Long id){
        ResponseEntity response;
        if(courseService.deleteCourse(id)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
