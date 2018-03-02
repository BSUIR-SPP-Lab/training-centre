package com.bsuir.trainingcenter.controller;

import com.bsuir.trainingcenter.entity.Course;
import com.bsuir.trainingcenter.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ModelAttribute
    public void setVaryResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
    }

    @PutMapping("/add")
    public ResponseEntity addCourse(@RequestBody Course course){
        ResponseEntity response;
        if(courseService.addCourse(course)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> findCourses(){
        return new ResponseEntity<>(courseService.findCourses(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findCourse(@PathVariable Long id){
        ResponseEntity response;
        Course course = courseService.findCourse(id);
        if(course!=null){
            response=new ResponseEntity<>(course, HttpStatus.OK);
        }else {
            response=new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable Long id,@RequestBody Course course){
        ResponseEntity response;
        course.setCourseId(id);
        if(courseService.updateCourse(course)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

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
