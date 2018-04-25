package com.bsuir.trainingcenter.controller;

import com.bsuir.trainingcenter.entity.view.SolutionView;
import com.bsuir.trainingcenter.entity.view.SolutionWithTaskView;
import com.bsuir.trainingcenter.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solution")
public class SolutionController {

    @Autowired
    private SolutionService solutionService;



    @PostMapping("/add")
    public ResponseEntity addSolution(@RequestBody SolutionView solution){
        ResponseEntity response;
        if(solutionService.addSolution(solution)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SolutionWithTaskView>> findSolutions(){
        return new ResponseEntity<>(solutionService.findSolutions(), HttpStatus.OK);

    }

    @GetMapping("/byUserId/{userId}")
    public ResponseEntity<List<SolutionWithTaskView>> findSolutionUserId(@PathVariable Long userId){
        return new ResponseEntity<>(solutionService.findSolutionsByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/byGroupId/{groupId}")
    public ResponseEntity<List<SolutionWithTaskView>> findSolutionByGroupId(@PathVariable Long groupId){
        return new ResponseEntity<>(solutionService.findSolutionsByGroupId(groupId), HttpStatus.OK);
    }

    @PostMapping("/update/{taskId}/{userId}")
    public ResponseEntity updateSolution(@PathVariable Long taskId,@PathVariable Long userId,@RequestBody SolutionView solution){
        ResponseEntity response;
        solution.setUserId(userId);
        solution.setTaskId(taskId);
        if(solutionService.updateSolution(solution)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @CrossOrigin
    @DeleteMapping("/delete/{taskId}/{userId}")
    public ResponseEntity deleteSolution(@PathVariable Long taskId,@PathVariable Long userId){
        ResponseEntity response;
        if(solutionService.deleteSolution(taskId,userId)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
