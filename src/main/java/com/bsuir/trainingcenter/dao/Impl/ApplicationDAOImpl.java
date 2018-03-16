package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.ApplicationDAO;
import com.bsuir.trainingcenter.entity.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ApplicationDAOImpl implements ApplicationDAO {

    private static final String queryAddApplication = "INSERT INTO `application` (`student_id`, `course_id`) " +
            "VALUES (?, ?)";
    private static final String queryFindApplications = "SELECT `application`.`application_id`, " +
            "`application`.`student_id`, `application`.`course_id` FROM `application`";
    private static final String queryFindApplication = "SELECT `application`.`application_id`, " +
            "`application`.`student_id`, `application`.`course_id` FROM `application` " +
            "WHERE `application`.`application_id` = ?";
    private static final String queryUpdateApplication = "UPDATE `application` SET `application`.`student_id` = ?," +
            "`application`.`course_id` = ? WHERE `application`.`application_id` = ?";
    private static final String queryDeleteApplication = "DELETE FROM `application` WHERE `application_id` = ?";


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
    public boolean addApplication(Application application) {
        return jdbcTemplate.update(queryAddApplication, application.getStudentId(), application.getCourseId()) > 0;
    }

    @Override
    public List<Application> findApplications() {
        return jdbcTemplate.query(queryFindApplications, rowMapper);
    }

    @Override
    public Application findApplication(long applicationId) {
        return jdbcTemplate.queryForObject(queryFindApplication, new Object[]{applicationId}, rowMapper);
    }

    @Override
    public boolean updateApplication(Application application) {
        return jdbcTemplate.update(queryUpdateApplication, application.getStudentId(), application.getCourseId(),
                application.getApplicationId()) > 0;
    }

    @Override
    public boolean deleteApplication(long applicationId) {
        return jdbcTemplate.update(queryDeleteApplication, applicationId) > 0;
    }

}
