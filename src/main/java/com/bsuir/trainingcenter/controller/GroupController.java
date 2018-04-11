package com.bsuir.trainingcenter.controller;

import com.bsuir.trainingcenter.entity.Group;
import com.bsuir.trainingcenter.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/add")
    public ResponseEntity addGroup(@RequestBody Group group){
        ResponseEntity response;
        if(groupService.addGroup(group)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Group>> findGroups(){
        return new ResponseEntity<>(groupService.findGroups(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> findGroup(@PathVariable Long id){
        return new ResponseEntity<>(groupService.findGroup(id), HttpStatus.OK);
    }

    @PostMapping("/update/{groupId}")
    public ResponseEntity updateGroup(@PathVariable Long groupId,@RequestBody Group group){
        ResponseEntity response;
        group.setGroupId(groupId);
        if(groupService.updateGroup(group)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @CrossOrigin
    @DeleteMapping("/delete/{groupId}")
    public ResponseEntity deleteGroup(@PathVariable Long groupId){
        ResponseEntity response;
        if(groupService.deleteGroup(groupId)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
