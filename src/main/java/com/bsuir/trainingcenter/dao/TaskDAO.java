package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskDAO {

    boolean addTask(Task task);

    List<Task> findTasks();

    Optional<Task> findTask(long taskId);

    boolean updateTask(Task task);

    boolean deleteTask(long taskId);

}
