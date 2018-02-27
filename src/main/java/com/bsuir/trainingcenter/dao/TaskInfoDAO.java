package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.TaskInfo;

import java.util.List;

public interface TaskInfoDAO {

    boolean addTaskInfo(TaskInfo taskInfo);

    List<TaskInfo> findTaskInfos();

    TaskInfo findTaskInfo(long id);

    boolean updateTaskInfo(TaskInfo taskInfo);

    boolean deleteTaskInfo(long id);

}
