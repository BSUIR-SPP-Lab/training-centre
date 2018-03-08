package com.bsuir.trainingcenter.service;

import com.bsuir.trainingcenter.entity.Solution;

import java.util.List;

public interface SolutionService {
    boolean addSolution(Solution solution);

    List<Solution> findSolutions();

    List<Solution> findSolutions(long userId);

    boolean updateSolution(Solution solution);

    boolean updateSolutionMark(long taskId, long userId, String teacherNotes, long mark);

    boolean deleteSolution(long taskId, long userId);
}
