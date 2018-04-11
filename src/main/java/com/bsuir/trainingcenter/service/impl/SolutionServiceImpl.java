package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.SolutionDAO;
import com.bsuir.trainingcenter.entity.Solution;
import com.bsuir.trainingcenter.entity.view.SolutionView;
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
    public List<SolutionView> findSolutions() {
        List<SolutionView> list = new ArrayList<>();
        for(Solution solution :solutionDAO.findSolutionsByUserId()){
            list.add(new SolutionView(solution.getTaskId(),solution.getUserId(),solution.getNotes(),solution.getFilepath(),solution.getTeacherNotes(),solution.getMark(), solution.getUploadTime().toString()));
        }
        return list;
    }

    @Override
    public List<SolutionView> findSolutions(long userId) {
        List<SolutionView> list = new ArrayList<>();
        for(Solution solution :solutionDAO.findSolutionsByUserId(userId)){
            list.add(new SolutionView(solution.getTaskId(),solution.getUserId(),solution.getNotes(),solution.getFilepath(),solution.getTeacherNotes(),solution.getMark(), solution.getUploadTime().toString()));
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
