package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.ApplicationDAO;
import com.bsuir.trainingcenter.dao.Impl.Helpers.ListHelper;
import com.bsuir.trainingcenter.entity.Application;
import com.bsuir.trainingcenter.entity.ApplicationWithInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class ApplicationDAOImpl implements ApplicationDAO {

    private static final String queryAddApplication = "INSERT INTO `application` (`student_id`, `course_id`) " +
            "VALUES (?, ?)";
    private static final String queryFindApplications = "SELECT `application`.`application_id`, " +
            "`application`.`student_id`, `application`.`course_id` FROM `application`";
    private static final String queryFindApplicationsByCourseId = "SELECT `application`.`application_id`,`application`.`student_id`,`application`.`course_id`,`s`.`first_name`,`s`.`last_name`\n" +
            "FROM `application`\n" +
            "JOIN `user` s ON application.student_id = s.user_id\n" +
            "WHERE course_id=?\n";
    private static final String queryFindApplication = "SELECT `application`.`application_id`, " +
            "`application`.`student_id`, `application`.`course_id` FROM `application` " +
            "WHERE `application`.`application_id` = ?";
    private static final String queryFindApplicationByCourseAndUser = "SELECT `application`.`application_id`" +
            " FROM `application` " +
            "WHERE `application`.`course_id` = ? AND student_id=?";
    private static final String queryUpdateApplication = "UPDATE `application` SET `application`.`student_id` = ?," +
            "`application`.`course_id` = ? WHERE `application`.`application_id` = ?";
    private static final String queryDeleteApplication = "DELETE FROM `application` WHERE `application_id` = ?";


    private JdbcTemplate jdbcTemplate;
    private RowMapper<Application> rowMapper = ((resultSet, i) -> {
        Application application = new Application();
        application.setStudentId(resultSet.getLong("student_id"));
        application.setApplicationId(resultSet.getLong("application_id"));
        application.setCourseId(resultSet.getLong("course_id"));
        return application;
    });
    private RowMapper<ApplicationWithInfo> applicationWithInfoRowMapper=(resultSet, i) -> {
        ApplicationWithInfo application = new ApplicationWithInfo();
        application.setStudentId(resultSet.getLong("student_id"));
        application.setApplicationId(resultSet.getLong("application_id"));
        application.setCourseId(resultSet.getLong("course_id"));
        application.setFirstName(resultSet.getString("first_name"));
        application.setLastName(resultSet.getString("last_name"));
        return application;
    };

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
    public List<ApplicationWithInfo> findApplicationsByCourse(long courseId) {
        return jdbcTemplate.query(queryFindApplicationsByCourseId,new Object[]{courseId},applicationWithInfoRowMapper);
    }

    @Override
    public Optional<Application> findApplication(long applicationId) {
        List<Application> queryResults = jdbcTemplate.query(queryFindApplication, new Object[]{applicationId}, rowMapper);
        return ListHelper.getFirst(queryResults);
    }

    @Override
    public boolean isApplicationfind(long courseId,long studentId) {
        List<Long> queryResults = jdbcTemplate.query(queryFindApplicationByCourseAndUser, new Object[]{courseId,studentId},(resultSet,i)->resultSet.getLong(1));
        return !queryResults.isEmpty();
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
