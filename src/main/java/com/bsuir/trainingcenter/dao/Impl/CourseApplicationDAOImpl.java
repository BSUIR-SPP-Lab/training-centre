package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.CourseApplicationDAO;
import com.bsuir.trainingcenter.entity.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CourseApplicationDAOImpl implements CourseApplicationDAO {

    private static final String queryFindApplicationsToCourse = "SELECT `application`.`application_id`, " +
            "`application`.`student_id`, `application`.`course_id` FROM `application` " +
            "WHERE `application`.`course_id` = ?";


    private JdbcTemplate jdbcTemplate;
    private RowMapper<Application> rowMapper = ((resultSet, i) -> {
        Application application = new Application();
        application.setApplicationId(resultSet.getLong("application_id"));
        application.setStudentId(resultSet.getLong("student_id"));
        application.setCourseId(resultSet.getLong("course_id"));
        return application;
    });

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Application> findApplicationsToCourse(long courseId) {
        return jdbcTemplate.query(queryFindApplicationsToCourse, new Object[]{courseId}, rowMapper);
    }

}
