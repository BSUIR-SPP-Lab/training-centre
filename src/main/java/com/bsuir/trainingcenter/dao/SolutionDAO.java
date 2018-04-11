package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Solution;

import java.util.List;
import java.util.Optional;

public interface SolutionDAO {

    boolean addSolution(Solution solution);

    List<Solution> findSolutionsByUserId();

    List<Solution> findSolutionsByUserId(long userId);

    Optional<Solution> findSolution(long taskId, long userId);

    boolean updateSolution(Solution solution);

    boolean updateSolutionMark(long taskId, long userId, String teacherNotes, long mark);

    boolean deleteSolution(long taskId, long userId);

}
