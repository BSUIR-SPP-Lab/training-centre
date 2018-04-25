package com.bsuir.trainingcenter.controller;

import com.bsuir.trainingcenter.entity.view.TaskView;
import com.bsuir.trainingcenter.entity.view.TaskWIthInfoView;
import com.bsuir.trainingcenter.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    private static final Logger logger = LogManager.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;



    @PostMapping("/add")
    public ResponseEntity addTask(@RequestBody TaskView task){
        ResponseEntity response;
        if(taskService.addTask(task)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskView>> findTasksInfo(){
        return new ResponseEntity<>(taskService.findTasks(), HttpStatus.OK);

    }

    @GetMapping("/byGroup/{groupId}")
    public ResponseEntity<List<TaskWIthInfoView>> findTasksByGroupId(@PathVariable long groupId){
        return ResponseEntity.ok(taskService.findTasksByGroupId(groupId));
    }


    @GetMapping("/{id}")
    public ResponseEntity<TaskView> findUser(@PathVariable Long id){
        ResponseEntity response;
        TaskView task = taskService.findTask(id);
        if(task!=null){
            response=new ResponseEntity<>(task, HttpStatus.OK);
        }else {
            response=new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @PostMapping("/update/{id}")
    public ResponseEntity updateTask(@PathVariable Long id,@RequestBody TaskView task){
        ResponseEntity response;
        task.setTaskId(id);
        if(taskService.updateTask(task)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @CrossOrigin
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
