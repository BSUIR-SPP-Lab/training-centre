package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Solution;

import java.util.List;

public interface SolutionDAO {

    boolean addSolution(Solution solution);

    List<Solution> findSolutionsByUserId();

    List<Solution> findSolutionsByUserId(long userId);

    Solution findSolution(long taskId, long userId);

    boolean updateSolution(Solution solution);

    boolean updateSolutionMark(long taskId, long userId, String teacherNotes, long mark);

    boolean deleteSolution(long taskId, long userId);

}
