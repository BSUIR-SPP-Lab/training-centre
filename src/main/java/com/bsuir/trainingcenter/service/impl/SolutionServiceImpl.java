package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.SolutionDAO;
import com.bsuir.trainingcenter.entity.Solution;
import com.bsuir.trainingcenter.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolutionServiceImpl implements SolutionService {

    @Autowired
    private SolutionDAO solutionDAO;

    @Override
    public boolean addSolution(Solution solution) {
        return solutionDAO.addSolution(solution);
    }

    @Override
    public List<Solution> findSolutions() {
        return solutionDAO.findSolutions();
    }

    @Override
    public List<Solution> findSolutions(long userId) {
        return solutionDAO.findSolutions(userId);
    }

    @Override
    public boolean updateSolution(Solution solution) {
        return solutionDAO.updateSolution(solution);
    }

    @Override
    public boolean deleteSolution(long taskId, long userId) {
        return solutionDAO.deleteSolution(taskId,userId);
    }
}
