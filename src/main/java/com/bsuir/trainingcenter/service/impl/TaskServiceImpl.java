package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.TaskDAO;
import com.bsuir.trainingcenter.entity.Task;
import com.bsuir.trainingcenter.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskDAO taskDAO;

    @Override
    public boolean addTask(Task task) {
        return taskDAO.addTask(task);
    }

    @Override
    public List<Task> findTasks() {
        return taskDAO.findTasks();
    }

    @Override
    public Task findTask(long taskId) {
        return taskDAO.findTask(taskId);
    }

    @Override
    public boolean updateTask(Task task) {
        return taskDAO.updateTask(task);
    }

    @Override
    public boolean deleteTask(long taskId) {
        return taskDAO.deleteTask(taskId);
    }
}
