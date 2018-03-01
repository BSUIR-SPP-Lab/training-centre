package com.bsuir.trainingcenter.service;

import com.bsuir.trainingcenter.entity.TaskInfo;

import java.util.List;

public interface TaskInfoService {
    boolean addTaskInfo(TaskInfo taskInfo);

    List<TaskInfo> findTasksInfo();

    TaskInfo findTaskInfo(long taskInfoId);

    boolean updateTaskInfo(TaskInfo taskInfo);

    boolean deleteTaskInfo(long taskInfoId);
}
