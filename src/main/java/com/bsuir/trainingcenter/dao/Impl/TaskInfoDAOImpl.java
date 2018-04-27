package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.Impl.Helpers.ListHelper;
import com.bsuir.trainingcenter.dao.TaskInfoDAO;
import com.bsuir.trainingcenter.entity.TaskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskInfoDAOImpl implements TaskInfoDAO {

    private static final String queryAddTaskInfo = "INSERT INTO `task_info` (`name`, `body`) VALUES (?, ?)";
    private static final String queryFindTasksInfo = "SELECT `task_info`.`task_info_id`, `task_info`.`name`, " +
            "`task_info`.`body` FROM `task_info`";
    private static final String queryFindTaskInfoById = "SELECT `task_info`.`task_info_id`, `task_info`.`name`, " +
            "`task_info`.`body` FROM `task_info` WHERE `task_info`.`task_info_id` = ?";
    private static final String queryUpdateTaskInfo = "UPDATE `task_info` SET `task_info`.`name` = ?, " +
            "`task_info`.`body` = ? WHERE `task_info`.`task_info_id` = ?";
    private static final String queryDeleteTaskInfo = "DELETE FROM `task_info` WHERE `task_info`.`task_info_id` = ?";


    private JdbcTemplate jdbcTemplate;
    private RowMapper<TaskInfo> rowMapper = ((resultSet, i) -> {
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTaskInfoId(resultSet.getLong("task_info_id"));
        taskInfo.setName(resultSet.getString("name"));
        taskInfo.setBody(resultSet.getString("body"));
        return taskInfo;
    });

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean addTaskInfo(TaskInfo taskInfo) {
        return jdbcTemplate.update(queryAddTaskInfo, taskInfo.getName(), taskInfo.getBody()) > 0;
    }

    @Override
    public List<TaskInfo> findTasksInfo() {
        return jdbcTemplate.query(queryFindTasksInfo, rowMapper);
    }

    @Override
    public Optional<TaskInfo> findTaskInfo(long taskInfoId) {
        List<TaskInfo> queryResults = jdbcTemplate.query(queryFindTaskInfoById, new Object[]{taskInfoId}, rowMapper);
        return ListHelper.getFirst(queryResults);
    }

    @Override
    public boolean updateTaskInfo(TaskInfo taskInfo) {
        return jdbcTemplate.update(queryUpdateTaskInfo, taskInfo.getName(), taskInfo.getBody(),
                taskInfo.getTaskInfoId()) > 0;
    }

    @Override
    public boolean deleteTaskInfo(long taskInfoId) {
        return jdbcTemplate.update(queryDeleteTaskInfo, taskInfoId) > 0;
    }

}
