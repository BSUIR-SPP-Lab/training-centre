package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.TaskInfoDAO;
import com.bsuir.trainingcenter.entity.TaskInfo;
import com.bsuir.trainingcenter.service.TaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskInfoServiceImpl implements TaskInfoService {

    private final TaskInfoDAO taskInfoDAO;

    @Autowired
    public TaskInfoServiceImpl(TaskInfoDAO taskInfoDAO) {
        this.taskInfoDAO = taskInfoDAO;
    }

    @Override
    public boolean addTaskInfo(TaskInfo taskInfo) {
        return taskInfoDAO.addTaskInfo(taskInfo);
    }

    @Override
    public List<TaskInfo> findTasksInfo() {
        return taskInfoDAO.findTasksInfo();
    }

    @Override
    public TaskInfo findTaskInfo(long taskInfoId) {
        return taskInfoDAO.findTaskInfo(taskInfoId);
    }

    @Override
    public boolean updateTaskInfo(TaskInfo taskInfo) {
        return taskInfoDAO.updateTaskInfo(taskInfo);
    }

    @Override
    public boolean deleteTaskInfo(long taskInfoId) {
        return taskInfoDAO.deleteTaskInfo(taskInfoId);
    }
}
