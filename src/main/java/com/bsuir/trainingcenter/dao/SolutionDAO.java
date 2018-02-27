package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Solution;

import java.util.List;

public interface SolutionDAO {

    boolean addSolution(Solution solution);

    List<Solution> findSolutions();

    Solution findSolution(long id);

    boolean updateSolution(Solution solution);

    boolean deleteSolution(long id);

}
