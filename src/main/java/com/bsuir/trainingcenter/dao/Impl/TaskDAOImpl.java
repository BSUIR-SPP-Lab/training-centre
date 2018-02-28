package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.TaskDAO;
import com.bsuir.trainingcenter.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class TaskDAOImpl implements TaskDAO {

    private static final String queryAddTask = "INSERT INTO `task` (`teacher_id`, `group_id`, `task_info_id`, " +
            "`upload_time`) VALUES (?, ?, ?, ?)";
    private static final String queryFindTasks = "SELECT `task`.`task_id`, `task`.`teacher_id`, `task`.`group_id`, " +
            "`task`.`task_info_id`, `task`.`upload_time` FROM `task`";
    private static final String queryFindTaskById = "SELECT `task`.`task_id`, `task`.`teacher_id`, `task`.`group_id`, " +
            "`task`.`task_info_id`, `task`.`upload_time` FROM `task` WHERE `task`.`task_id` = ?";
    private static final String queryUpdateTask = "UPDATE `task` SET `teacher_id` = ?, `group_id` = ?, " +
            "`task_info_id` = ?, `upload_time` = ? WHERE `task`.`task_id` = ?";
    private static final String queryDeleteTask = "DELETE FROM `task` WHERE `task`.`task_id` = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Task> rowMapper = ((resultSet, i) -> {
        Task task = new Task();
        task.setTaskId(resultSet.getLong("task_id"));
        task.setTeacherId(resultSet.getLong("teacher_id"));
        task.setGroupId(resultSet.getLong("group_id"));
        task.setTaskInfoId(resultSet.getLong("task_info_id"));
        task.setUploadTime(resultSet.getTimestamp("upload_time").toLocalDateTime());
        return task;
    });

    @Override
    public boolean addTask(Task task) {
        return jdbcTemplate.update(queryAddTask, task.getTeacherId(), task.getGroupId(), task.getTaskInfoId(),
                task.getUploadTime()) > 0;
    }

    @Override
    public List<Task> findTasks() {
        return jdbcTemplate.query(queryFindTasks, rowMapper);
    }

    @Override
    public Task findTask(long taskId) {
        return jdbcTemplate.queryForObject(queryFindTaskById, new Object[]{taskId}, rowMapper);
    }

    @Override
    public boolean updateTask(Task task) {
        return jdbcTemplate.update(queryUpdateTask, task.getTeacherId(), task.getGroupId(), task.getTaskInfoId(),
                task.getUploadTime(), task.getTaskId()) > 0;
    }

    @Override
    public boolean deleteTask(long taskId) {
        return jdbcTemplate.update(queryDeleteTask, taskId) > 0;
    }
}
