package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Task;

import java.util.List;

public interface GroupTaskDAO {

    List<Task> findGroupTasks(long groupId);

}
