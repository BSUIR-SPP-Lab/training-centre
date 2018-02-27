package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Task;

import java.util.List;

public interface TaskDAO {

    boolean addTask(Task task);

    List<Task> findTasks();

    Task findTask(long id);

    boolean updateTask(Task task);

    boolean deleteTask(long id);

}
