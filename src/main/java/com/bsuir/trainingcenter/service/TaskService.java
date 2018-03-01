package com.bsuir.trainingcenter.service;

import com.bsuir.trainingcenter.entity.Task;

import java.util.List;

public interface TaskService {


    boolean addTask(Task task);

    List<Task> findTasks();

    Task findTask(long taskId);

    boolean updateTask(Task task);

    boolean deleteTask(long taskId);
}
