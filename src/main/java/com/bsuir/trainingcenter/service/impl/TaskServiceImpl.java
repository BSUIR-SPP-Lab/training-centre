package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.TaskDAO;
import com.bsuir.trainingcenter.entity.Task;
import com.bsuir.trainingcenter.entity.view.TaskView;
import com.bsuir.trainingcenter.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskDAO taskDAO;

    @Override
    public boolean addTask(TaskView taskView) {
        Task task = new Task(taskView.getTaskId(),taskView.getTeacherId(),taskView.getGroupId(),taskView.getTaskInfoId(),LocalDateTime.parse(taskView.getUploadTime()));
        return taskDAO.addTask(task);
    }

    @Override
    public List<TaskView> findTasks() {
        List<TaskView> list = new ArrayList<>();
        for(Task task : taskDAO.findTasks()){
            list.add(new TaskView(task.getTaskId(),task.getTeacherId(),task.getGroupId(),task.getTaskInfoId(),task.getUploadTime().toString()));
        }
        return list;
    }

    @Override
    public TaskView findTask(long taskId) {

        Task task =taskDAO.findTask(taskId);
        return new TaskView(task.getTaskId(),task.getTeacherId(),task.getGroupId(),task.getTaskInfoId(),task.getUploadTime().toString());
    }

    @Override
    public boolean updateTask(TaskView taskView) {
        Task task = new Task(taskView.getTaskId(),taskView.getTeacherId(),taskView.getGroupId(),taskView.getTaskInfoId(),LocalDateTime.parse(taskView.getUploadTime()));
        return taskDAO.updateTask(task);
    }

    @Override
    public boolean deleteTask(long taskId) {
        return taskDAO.deleteTask(taskId);
    }
}
