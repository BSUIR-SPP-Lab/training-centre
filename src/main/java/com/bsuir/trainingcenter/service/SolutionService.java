package com.bsuir.trainingcenter.service;

import com.bsuir.trainingcenter.entity.view.SolutionView;
import com.bsuir.trainingcenter.entity.view.SolutionWithTaskView;

import java.util.List;

public interface SolutionService {
    boolean addSolution(SolutionView solution);

    List<SolutionWithTaskView> findSolutions();

    List<SolutionWithTaskView> findSolutionsByUserId(long userId);

    List<SolutionWithTaskView> findSolutionsByGroupId(long groupId);

    boolean updateSolution(SolutionView solution);

    boolean updateSolutionMark(long taskId, long userId, String teacherNotes, long mark);

    boolean deleteSolution(long taskId, long userId);

    List<SolutionWithTaskView> findSolutionsByUserIdAndCourseId(long groupId, long courseId);
}
