package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.CoordinatorCourseDAO;
import com.bsuir.trainingcenter.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class CoordinatorCourseDAOImpl implements CoordinatorCourseDAO {

    private static final String queryFindCoordinatorCourses = "SELECT `course`.`course_id`, " +
            "`course`.`course_info_id`, `course`.`start`, `course`.`end` FROM `course` " +
            "WHERE `course`.`coordinator_id` = ?";


    private JdbcTemplate jdbcTemplate;

    private RowMapper<Course> rowMapper = ((resultSet, i) -> {
        Course course = new Course();
        course.setCourseId(resultSet.getLong("course_id"));
        course.setCourseInfoId(resultSet.getLong("course_info_id"));
        course.setCoordinatorId(resultSet.getLong("coordinator_id"));
        course.setStart(resultSet.getTimestamp("start").toLocalDateTime());
        course.setEnd(resultSet.getTimestamp("end").toLocalDateTime());
        return course;
    });

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Course> findCoordinatorCourses(long coordinatorId) {
        return jdbcTemplate.query(queryFindCoordinatorCourses, new Object[]{coordinatorId}, rowMapper);
    }

}
