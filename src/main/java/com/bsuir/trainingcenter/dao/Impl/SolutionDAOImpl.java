package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.SolutionDAO;
import com.bsuir.trainingcenter.entity.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SolutionDAOImpl implements SolutionDAO {

    private static final String queryAddSolution = "INSERT INTO `solution` (`task_id`, `user_id`, `notes`, " +
            "`filepath`, `teacher_notes`, `mark`) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String queryFindSolutions = "SELECT `solution`.`task_id`, `solution`.`user_id`, " +
            "`solution`.`notes`, `solution`.`filepath`, `solution`.`teacher_notes`, `solution`.`upload_time`, " +
            "`solution`.`mark` FROM `solution`";
    private static final String queryFindSolutionsByUserId = "SELECT `solution`.`task_id`, `solution`.`user_id`," +
            "`solution`.`notes`, `solution`.`filepath`, `solution`.`teacher_notes`, `solution`.`upload_time`, " +
            "`solution`.`mark` FROM `solution` WHERE `solution`.`user_id` = ?";
    private static final String queryUpdateSolution = "UPDATE `solution` SET `solution`.`notes` = ?, " +
            "`solution`.`filepath` = ?, `solution`.`teacher_notes` = ?, `solution`.`mark` = ? " +
            "WHERE (`solution`.`task_id` = ?) AND (`solution`.`user_id` = ?)";
    private static final String queryUpdateSolutionMark = "UPDATE `solution` SET `solution`.`teacher_notes` = ?, " +
            "`solution`.`mark` = ? WHERE (`solution`.`task_id` = ?) AND (`solution`.`user_id` = ?)";
    private static final String queryDeleteSolution = "DELETE FROM `solution` WHERE (`solution`.`task_id` = ?) AND " +
            "(`solution`.`user_id` = ?)";


    private JdbcTemplate jdbcTemplate;

    private RowMapper<Solution> rowMapper = ((resultSet, i) -> {
        Solution solution = new Solution();
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
    public List<Solution> findSolutions() {
        return jdbcTemplate.query(queryFindSolutions, rowMapper);
    }

    @Override
    public List<Solution> findSolutions(long userId) {
        return jdbcTemplate.query(queryFindSolutionsByUserId, new Object[]{userId}, rowMapper);
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
