package com.bsuir.trainingcenter.controller;

import com.bsuir.trainingcenter.entity.TaskInfo;
import com.bsuir.trainingcenter.service.TaskInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taskInfo")
public class TaskInfoController {
    private static final Logger logger = LogManager.getLogger(TaskInfoController.class);

    @Autowired
    private TaskInfoService taskInfoService;
    

    @PostMapping("/add")
    public ResponseEntity addTaskInfo(@RequestBody TaskInfo taskInfo){
        ResponseEntity response;
        if(taskInfoService.addTaskInfo(taskInfo)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskInfo>> findTasksInfo(){
        return new ResponseEntity<>(taskInfoService.findTasksInfo(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskInfo> findTaskInfo(@PathVariable Long id){
        ResponseEntity response;
        TaskInfo taskInfo = taskInfoService.findTaskInfo(id);
        if(taskInfo!=null){
            response=new ResponseEntity<>(taskInfo, HttpStatus.OK);
        }else {
            response=new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @PostMapping("/update/{id}")
    public ResponseEntity updateTaskInfo(@PathVariable Long id,@RequestBody TaskInfo taskInfo){
        ResponseEntity response;
        taskInfo.setTaskInfoId(id);
        if(taskInfoService.updateTaskInfo(taskInfo)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTaskInfo(@PathVariable Long id){
        ResponseEntity response;
        if(taskInfoService.deleteTaskInfo(id)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
