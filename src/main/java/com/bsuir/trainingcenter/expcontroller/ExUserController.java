package com.bsuir.trainingcenter.expcontroller;

import com.bsuir.trainingcenter.entity.User;
import com.bsuir.trainingcenter.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExUserController {

    private static final Logger logger = LogManager.getLogger(ExUserController.class);

    @Autowired
    private UserService userService;


//    @RequestMapping(value = "user/add", method = RequestMethod.POST)
//    public String addUser(@RequestBody User user){
//        ResponseEntity response;
//        if(userService.addUser(user)){
//            response=new ResponseEntity( HttpStatus.OK);
//        }else {
//            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//        return response;
//    }

    @RequestMapping("/users/all")
    public String findUsers(Model model){
        model.addAttribute("users",userService.findUsers());
        return "user-all";

    }

    @RequestMapping("/users/info")
    public String findUser(@RequestParam(value = "id", required = true) Long id, Model model){
        User user = userService.findUser(id);
        if(user!=null){
            model.addAttribute(user);
        }else {
            model.addAttribute("errorMessage","notFound");
        }
        return "user-info";
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity updateUser(@PathVariable Long id,@RequestBody User user){
//        ResponseEntity response;
//        user.setId(id);
//        if(userService.updateUser(user)){
//            response=new ResponseEntity( HttpStatus.OK);
//        }else {
//            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//        return response;
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity deleteUser(@PathVariable Long id){
//        ResponseEntity response;
//        if(userService.deleteUser(id)){
//            response=new ResponseEntity( HttpStatus.OK);
//        }else {
//            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//        return response;
//    }
}
