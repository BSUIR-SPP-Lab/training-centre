package com.bsuir.trainingcenter.controller;

import com.bsuir.trainingcenter.entity.User;
import com.bsuir.trainingcenter.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody User user){
        ResponseEntity response;
        if(userService.addUser(user)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> findUsers(){
        return new ResponseEntity<>(userService.findUsers(), HttpStatus.OK);

    }

    @GetMapping("/teachers")
    public ResponseEntity<List<User>> findTeachers(){
        return new ResponseEntity<>(userService.findTeachers(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUser(@PathVariable Long id){
        ResponseEntity response;
        User user = userService.findUser(id);
        if(user!=null){
            response=new ResponseEntity<>(user, HttpStatus.OK);
        }else {
            response=new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @GetMapping("/byLogin/{login}")
    public ResponseEntity<User> findUser(@PathVariable String login){
        ResponseEntity response;
        User user = userService.findUser(login);
        if(user!=null){
            response=new ResponseEntity<>(user, HttpStatus.OK);
        }else {
            response=new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @PostMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Long id,@RequestBody User user){
        ResponseEntity response;
        user.setId(id);
        if(userService.updateUser(user)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        ResponseEntity response;
        if(userService.deleteUser(id)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @PostMapping("/login/{login}")
    public ResponseEntity<User> login(@PathVariable String login, @RequestBody String password){
        User user = userService.login(login, password);
        if(user!=null){
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
}
