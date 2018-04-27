package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.TaskInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskInfoDAO {

    boolean addTaskInfo(TaskInfo taskInfo);

    List<TaskInfo> findTasksInfo();

    Optional<TaskInfo> findTaskInfo(long taskInfoId);

    boolean updateTaskInfo(TaskInfo taskInfo);

    boolean deleteTaskInfo(long taskInfoId);

}
