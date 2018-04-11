package com.bsuir.trainingcenter.service;

import com.bsuir.trainingcenter.entity.view.SolutionView;

import java.util.List;

public interface SolutionService {
    boolean addSolution(SolutionView solution);

    List<SolutionView> findSolutions();

    List<SolutionView> findSolutions(long userId);

    boolean updateSolution(SolutionView solution);

    boolean updateSolutionMark(long taskId, long userId, String teacherNotes, long mark);

    boolean deleteSolution(long taskId, long userId);
}
