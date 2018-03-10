package com.bsuir.trainingcenter.service;

import com.bsuir.trainingcenter.entity.view.TaskView;

import java.util.List;

public interface TaskService {


    boolean addTask(TaskView task);

    List<TaskView> findTasks();

    TaskView findTask(long taskId);

    boolean updateTask(TaskView task);

    boolean deleteTask(long taskId);
}
