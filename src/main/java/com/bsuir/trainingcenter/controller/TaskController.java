package com.bsuir.trainingcenter.controller;

import com.bsuir.trainingcenter.entity.Task;
import com.bsuir.trainingcenter.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    private static final Logger logger = LogManager.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @ModelAttribute
    public void setVaryResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
    }

    @PostMapping("/add")
    public ResponseEntity addTask(@RequestBody Task task){
        ResponseEntity response;
        if(taskService.addTask(task)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/all")
    @Secured({"TEACHER"})
    public ResponseEntity<List<Task>> findTasksInfo(){
        return new ResponseEntity<>(taskService.findTasks(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findUser(@PathVariable Long id){
        ResponseEntity response;
        Task task = taskService.findTask(id);
        if(task!=null){
            response=new ResponseEntity<>(task, HttpStatus.OK);
        }else {
            response=new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @PostMapping("/update/{id}")
    public ResponseEntity updateTask(@PathVariable Long id,@RequestBody Task task){
        ResponseEntity response;
        task.setTaskId(id);
        if(taskService.updateTask(task)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id){
        ResponseEntity response;
        if(taskService.deleteTask(id)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
