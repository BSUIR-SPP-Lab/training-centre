package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.Impl.Helpers.ListHelper;
import com.bsuir.trainingcenter.dao.SolutionDAO;
import com.bsuir.trainingcenter.entity.Solution;
import com.bsuir.trainingcenter.entity.SolutionWithTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class SolutionDAOImpl implements SolutionDAO {

    private static final String queryAddSolution = "INSERT INTO `solution` (`task_id`, `user_id`, `notes`, " +
            "`filepath`, `teacher_notes`, `mark`) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String queryFindSolutions = "SELECT\n" +
            "  `solution`.`task_id`, `solution`.`user_id`, `solution`.`notes`, `solution`.`filepath`,`solution`.`teacher_notes`,`solution`.`upload_time`,`solution`.`mark`,`t2`.`body`,`t2`.name\n" +
            ", u.first_name,u.last_name FROM `solution`\n" +
            "  JOIN task t ON solution.task_id = t.task_id\n" +
            "  JOIN task_info t2 ON t.task_info_id = t2.task_info_id" +
            "  JOIN `user` u ON u.user_id=solution.user_id";
    private static final String queryFindSolutionsByUserId = "SELECT\n" +
            "  `solution`.`task_id`, `solution`.`user_id`, `solution`.`notes`, `solution`.`filepath`,`solution`.`teacher_notes`,`solution`.`upload_time`,`solution`.`mark`,`t2`.`body`,`t2`.name\n" +
            ", u.first_name,u.last_name FROM `solution`\n" +
            "  JOIN task t ON solution.task_id = t.task_id\n" +
            "  JOIN task_info t2 ON t.task_info_id = t2.task_info_id " +
            "JOIN `user` u ON u.user_id=solution.user_id WHERE `solution`.`user_id` = ?";
    private static final String queryFindSolutionsByGroupId = "SELECT\n" +
            "  `solution`.`task_id`, `solution`.`user_id`, `solution`.`notes`, `solution`.`filepath`,`solution`.`teacher_notes`,`solution`.`upload_time`,`solution`.`mark`,`t2`.`body`,`t2`.name\n" +
            ", u.first_name,u.last_name FROM `solution`\n" +
            "  JOIN task t ON solution.task_id = t.task_id\n" +
            "  JOIN task_info t2 ON t.task_info_id = t2.task_info_id " +
            "JOIN `user` u ON u.user_id=solution.user_id WHERE group_id = ?";

    private static final String queryFindSolution = "SELECT\n" +
            "  `solution`.`task_id`, `solution`.`user_id`, `solution`.`notes`, `solution`.`filepath`,`solution`.`teacher_notes`,`solution`.`upload_time`,`solution`.`mark`,`t2`.`body`,`t2`.name,\n" +
            "u.first_name,u.last_name FROM `solution`\n" +
            "  JOIN task t ON solution.task_id = t.task_id\n" +
            "  JOIN task_info t2 ON t.task_info_id = t2.task_info_id" +
            "  JOIN `user` u ON u.user_id=solution.user_id WHERE (`solution`.`task_id` = ?) AND (`solution`.`user_id` = ?)";
    private static final String queryUpdateSolution = "UPDATE `solution` SET `solution`.`notes` = ?, " +
            "`solution`.`filepath` = ?, `solution`.`teacher_notes` = ?, `solution`.`mark` = ? " +
            "WHERE (`solution`.`task_id` = ?) AND (`solution`.`user_id` = ?)";
    private static final String queryUpdateSolutionMark = "UPDATE `solution` SET `solution`.`teacher_notes` = ?, " +
            "`solution`.`mark` = ? WHERE (`solution`.`task_id` = ?) AND (`solution`.`user_id` = ?)";
    private static final String queryDeleteSolution = "DELETE FROM `solution` WHERE (`solution`.`task_id` = ?) AND " +
            "(`solution`.`user_id` = ?)";


    private JdbcTemplate jdbcTemplate;
    private RowMapper<SolutionWithTask> rowMapper = ((resultSet, i) -> {
        SolutionWithTask solution = new SolutionWithTask();
        solution.setTaskId(resultSet.getLong("task_id"));
        solution.setUserId(resultSet.getLong("user_id"));
        solution.setNotes(resultSet.getString("notes"));
        solution.setFilepath(resultSet.getString("filepath"));
        solution.setTeacherNotes(resultSet.getString("teacher_notes"));
        if (resultSet.wasNull()) {
            solution.setMark(null);
        } else {
            solution.setMark(resultSet.getLong("mark"));
        }
        solution.setName(resultSet.getString("name"));
        solution.setBody(resultSet.getString("body"));
        solution.setFirstName(resultSet.getString("first_name"));
        solution.setLastName(resultSet.getString("last_name"));
        solution.setUploadTime(resultSet.getTimestamp("upload_time").toLocalDateTime());
        return solution;
    });

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean addSolution(Solution solution) {
        return jdbcTemplate.update(queryAddSolution, solution.getTaskId(), solution.getUserId(), solution.getNotes(),
                solution.getFilepath(), solution.getTeacherNotes(), solution.getMark()) > 0;
    }

    @Override
    public List<SolutionWithTask> findSolutions() {
        return jdbcTemplate.query(queryFindSolutions, rowMapper);
    }

    @Override
    public List<SolutionWithTask> findSolutionsByUserId(long userId) {
        return jdbcTemplate.query(queryFindSolutionsByUserId, new Object[]{userId}, rowMapper);
    }

    @Override
    public List<SolutionWithTask> findSolutionsByGroupId(long groupId) {
        return jdbcTemplate.query(queryFindSolutionsByGroupId, new Object[]{groupId}, rowMapper);
    }

    @Override
    public Optional<SolutionWithTask> findSolution(long taskId, long userId) {
        List<SolutionWithTask> queryResults = jdbcTemplate.query(queryFindSolution, new Object[]{taskId, userId}, rowMapper);
        return ListHelper.getFirst(queryResults);
    }

    @Override
    public boolean updateSolution(Solution solution) {
        return jdbcTemplate.update(queryUpdateSolution, solution.getNotes(), solution.getFilepath(),
                solution.getTeacherNotes(), solution.getMark(), solution.getTaskId(), solution.getUserId()) > 0;
    }

    @Override
    public boolean updateSolutionMark(long taskId, long userId, String teacherNotes, long mark) {
        return jdbcTemplate.update(queryUpdateSolutionMark, teacherNotes, mark, taskId, userId) > 0;
    }

    @Override
    public boolean deleteSolution(long taskId, long userId) {
        return jdbcTemplate.update(queryDeleteSolution, taskId, userId) > 0;
    }
}
