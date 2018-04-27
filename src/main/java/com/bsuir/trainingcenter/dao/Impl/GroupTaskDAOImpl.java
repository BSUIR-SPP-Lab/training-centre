package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.GroupTaskDAO;
import com.bsuir.trainingcenter.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class GroupTaskDAOImpl implements GroupTaskDAO {

    private static final String queryFindGroupTasks = "SELECT `task`.`task_id`, `task`.`teacher_id`, `task`.`group_id`, " +
            "`task`.`task_info_id`, `task`.`upload_time` FROM `task` WHERE `task`.`group_id` = ?";


    private JdbcTemplate jdbcTemplate;
    private RowMapper<Task> rowMapper = ((resultSet, i) -> {
        Task task = new Task();
        task.setTaskId(resultSet.getLong("task_id"));
        task.setGroupId(resultSet.getLong("group_id"));
        task.setTeacherId(resultSet.getLong("teacher_id"));
        task.setTaskInfoId(resultSet.getLong("task_info_id"));
        task.setUploadTime(resultSet.getTimestamp("upload_time").toLocalDateTime());
        return task;
    });

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Task> findGroupTasks(long groupId) {
        return jdbcTemplate.query(queryFindGroupTasks, new Object[]{groupId}, rowMapper);
    }

}
