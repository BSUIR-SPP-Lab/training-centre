package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.SolutionDAO;
import com.bsuir.trainingcenter.entity.Solution;
import com.bsuir.trainingcenter.entity.SolutionWithTask;
import com.bsuir.trainingcenter.entity.view.SolutionView;
import com.bsuir.trainingcenter.entity.view.SolutionWithTaskView;
import com.bsuir.trainingcenter.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SolutionServiceImpl implements SolutionService {

    private final SolutionDAO solutionDAO;

    @Autowired
    public SolutionServiceImpl(SolutionDAO solutionDAO) {
        this.solutionDAO = solutionDAO;
    }

    @Override
    public boolean addSolution(SolutionView solutionView) {
        Solution solution = new Solution(solutionView.getTaskId(),solutionView.getUserId(),solutionView.getNotes(),solutionView.getFilepath(),solutionView.getTeacherNotes(),solutionView.getMark(), LocalDateTime.parse(solutionView.getUploadTime()));
        return solutionDAO.addSolution(solution);
    }

    @Override
    public List<SolutionWithTaskView> findSolutions() {
        List<SolutionWithTaskView> list = new ArrayList<>();
        for(SolutionWithTask solution :solutionDAO.findSolutions()){
            list.add(new SolutionWithTaskView(solution.getTaskId(),solution.getUserId(),solution.getNotes(),solution.getFilepath(),solution.getTeacherNotes(),solution.getMark(), solution.getUploadTime().toString(),solution.getName(),solution.getBody(),solution.getFirstName(),solution.getLastName()));
        }
        return list;
    }

    @Override
    public List<SolutionWithTaskView> findSolutions(long userId) {
        List<SolutionWithTaskView> list = new ArrayList<>();
        for(SolutionWithTask solution :solutionDAO.findSolutionsByUserId(userId)){
            list.add(new SolutionWithTaskView(solution.getTaskId(),solution.getUserId(),solution.getNotes(),solution.getFilepath(),solution.getTeacherNotes(),solution.getMark(), solution.getUploadTime().toString(),solution.getName(),solution.getBody(),solution.getFirstName(),solution.getLastName()));
        }
        return list;
    }

    @Override
    public boolean updateSolution(SolutionView solutionView) {
        Solution solution = new Solution(solutionView.getTaskId(),solutionView.getUserId(),solutionView.getNotes(),solutionView.getFilepath(),solutionView.getTeacherNotes(),solutionView.getMark(), LocalDateTime.parse(solutionView.getUploadTime()));
        return solutionDAO.updateSolution(solution);
    }

    @Override
    public boolean updateSolutionMark(long taskId, long userId, String teacherNotes, long mark) {
        return solutionDAO.updateSolutionMark(taskId, userId, teacherNotes, mark);
    }

    @Override
    public boolean deleteSolution(long taskId, long userId) {
        return solutionDAO.deleteSolution(taskId, userId);
    }
}
