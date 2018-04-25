package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.Impl.Helpers.ListHelper;
import com.bsuir.trainingcenter.dao.TaskDAO;
import com.bsuir.trainingcenter.entity.Task;
import com.bsuir.trainingcenter.entity.TaskWithInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskDAOImpl implements TaskDAO {

    private static final String queryAddTask = "INSERT INTO `task` (`teacher_id`, `group_id`, `task_info_id`, " +
            "`upload_time`) VALUES (?, ?, ?, ?)";
    private static final String queryFindTasks = "SELECT `task`.`task_id`, `task`.`teacher_id`, `task`.`group_id`, " +
            "`task`.`task_info_id`, `task`.`upload_time` FROM `task`";
    private static final String queryFindTasksByGroupId ="SELECT `task`.`task_id`,`task`.`group_id`, `task`.`upload_time`, `u`.`first_name`, `u`.`last_name`, ti.name, ti.body\n" +
            "FROM `task`\n" +
            "  JOIN task_info ti ON task.task_info_id = ti.task_info_id\n" +
            "  JOIN `user` u ON task.teacher_id = u.user_id\n" +
            "WHERE group_id = ?";
    private static final String queryFindTaskById = "SELECT `task`.`task_id`, `task`.`teacher_id`, `task`.`group_id`, " +
            "`task`.`task_info_id`, `task`.`upload_time` FROM `task` WHERE `task`.`task_id` = ?";
    private static final String queryUpdateTask = "UPDATE `task` SET `task`.`teacher_id` = ?, `task`.`group_id` = ?, " +
            "`task`.`task_info_id` = ?, `task`.`upload_time` = ? WHERE `task`.`task_id` = ?";
    private static final String queryDeleteTask = "DELETE FROM `task` WHERE `task`.`task_id` = ?";


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
    private RowMapper<TaskWithInfo> taskWithInfoRowMapper = ((resultSet, i) ->{
        TaskWithInfo task = new TaskWithInfo();
        task.setTaskId(resultSet.getLong("task_id"));
        task.setFirstName(resultSet.getString("first_name"));
        task.setLastName(resultSet.getString("last_name"));
        task.setGroupId(resultSet.getLong("group_id"));
        task.setBody(resultSet.getString("body"));
        task.setName(resultSet.getString("name"));
        task.setUploadTime(resultSet.getTimestamp("upload_time").toLocalDateTime());
        return task;
    });

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

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
    public List<TaskWithInfo> findTasksByGroupId(long groupId) {
        return jdbcTemplate.query(queryFindTasksByGroupId,new Object[]{groupId},taskWithInfoRowMapper);
    }

    @Override
    public Optional<Task> findTask(long taskId) {
        List<Task> queryResults = jdbcTemplate.query(queryFindTaskById, new Object[]{taskId}, rowMapper);
        return ListHelper.getFirst(queryResults);
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
