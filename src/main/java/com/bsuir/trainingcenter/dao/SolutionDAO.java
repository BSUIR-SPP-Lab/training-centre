package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Solution;
import com.bsuir.trainingcenter.entity.SolutionWithTask;

import java.util.List;
import java.util.Optional;

public interface SolutionDAO {

    boolean addSolution(Solution solution);

    List<SolutionWithTask> findSolutions();

    List<SolutionWithTask> findSolutionsByUserId(long userId);

    List<SolutionWithTask> findSolutionsByGroupId(long groupId);

    Optional<SolutionWithTask> findSolution(long taskId, long userId);

    boolean updateSolution(Solution solution);

    boolean updateSolutionMark(long taskId, long userId, String teacherNotes, long mark);

    boolean deleteSolution(long taskId, long userId);

}
