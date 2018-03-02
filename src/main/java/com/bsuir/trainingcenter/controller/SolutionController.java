package com.bsuir.trainingcenter.controller;

import com.bsuir.trainingcenter.entity.Solution;
import com.bsuir.trainingcenter.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/solution")
public class SolutionController {

    @Autowired
    private SolutionService solutionService;

    @ModelAttribute
    public void setVaryResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
    }

    @PutMapping("/add")
    public ResponseEntity addSolution(@RequestBody Solution solution){
        ResponseEntity response;
        if(solutionService.addSolution(solution)){
            response=new ResponseEntity( HttpStatus.OK);
        }else {
            response=new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Solution>> findSolutions(){
        return new ResponseEntity<>(solutionService.findSolutions(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Solution>> findSolution(@PathVariable Long id){
        return new ResponseEntity<>(solutionService.findSolutions(id), HttpStatus.OK);
    }

    @PutMapping("/update/{taskId}/{userId}")
    public ResponseEntity updateSolution(@PathVariable Long taskId,@PathVariable Long userId,@RequestBody Solution solution){
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
