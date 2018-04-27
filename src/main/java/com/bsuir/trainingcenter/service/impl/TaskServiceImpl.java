package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.TaskDAO;
import com.bsuir.trainingcenter.entity.Task;
import com.bsuir.trainingcenter.entity.TaskWithInfo;
import com.bsuir.trainingcenter.entity.view.TaskView;
import com.bsuir.trainingcenter.entity.view.TaskWIthInfoView;
import com.bsuir.trainingcenter.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskDAO taskDAO;

    @Autowired
    public TaskServiceImpl(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Override
    public boolean addTask(TaskView taskView) {
        Task task = new Task(taskView.getTaskId(), taskView.getTeacherId(), taskView.getGroupId(), taskView.getTaskInfoId(), LocalDateTime.parse(taskView.getUploadTime()));
        return taskDAO.addTask(task);
    }

    @Override
    public List<TaskView> findTasks() {
        List<TaskView> list = new ArrayList<>();
        for (Task task : taskDAO.findTasks()) {
            list.add(new TaskView(task.getTaskId(), task.getTeacherId(), task.getGroupId(), task.getTaskInfoId(), task.getUploadTime().toString()));
        }
        return list;
    }


    @Override
    public List<TaskWIthInfoView> findTasksByGroupId(long groupId) {
        List<TaskWIthInfoView> list = new ArrayList<>();
        for (TaskWithInfo task : taskDAO.findTasksByGroupId(groupId)) {
            list.add(new TaskWIthInfoView(task.getTaskId(), task.getFirstName(), task.getLastName(), task.getGroupId(), task.getName(), task.getBody(), task.getUploadTime().toString()));
        }
        return list;
    }

    @Override
    public List<TaskWIthInfoView> findTasks(long groupId) {
        List<TaskWIthInfoView> list = new ArrayList<>();
        for (TaskWithInfo task : taskDAO.findTasks(groupId)) {
            list.add(new TaskWIthInfoView(task.getTaskId(), task.getFirstName(), task.getLastName(), task.getGroupId(), task.getName(), task.getBody(), task.getUploadTime().toString()));
        }
        return list;
    }

    @Override
    public TaskView findTask(long taskId) {

        Task task = taskDAO.findTask(taskId).get();
        return new TaskView(task.getTaskId(), task.getTeacherId(), task.getGroupId(), task.getTaskInfoId(), task.getUploadTime().toString());
    }

    @Override
    public boolean updateTask(TaskView taskView) {
        Task task = new Task(taskView.getTaskId(), taskView.getTeacherId(), taskView.getGroupId(), taskView.getTaskInfoId(), LocalDateTime.parse(taskView.getUploadTime()));
        return taskDAO.updateTask(task);
    }

    @Override
    public boolean deleteTask(long taskId) {
        return taskDAO.deleteTask(taskId);
    }
}
