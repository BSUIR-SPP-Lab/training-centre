package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.CourseDAO;
import com.bsuir.trainingcenter.dao.Impl.Helpers.ListHelper;
import com.bsuir.trainingcenter.entity.Course;
import com.bsuir.trainingcenter.entity.CourseWithInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseDAOImpl implements CourseDAO {

    private static final String queryAddCourse = "INSERT INTO `course` (`course_info_id`, `coordinator_id`, " +
            "`start`, `end`) VALUES (?, ?, ?, ?)";
    private static final String queryFindCourses = "SELECT `course`.`course_id`, `course`.`course_info_id`, " +
            "`course`.`start`, `course`.`end`, `course`.`coordinator_id` FROM `course`";
    private static final String queryFindCoursesWithInfo = "SELECT `course`.`course_id`, `course`.`course_info_id`, \n" +
            "            `course`.`start`, `course`.`end`, `course`.`coordinator_id`,\n" +
            "            `course_info`.`name`, `course_info`.`description`,`user`.`first_name`,`user`.`last_name` FROM `course` \n" +
            "            JOIN `course_info` ON `course`.`course_info_id` = `course_info`.`course_info_id`\n" +
            "            JOIN `user` ON course.coordinator_id = `user`.user_id\n";
    private static final String queryFindCourse = "SELECT `course`.`course_id`, `course`.`course_info_id`, " +
            "`course`.`start`, `course`.`end`, `course`.`coordinator_id` FROM `course` WHERE `course`.`course_id` = ?";
    private static final String queryFindCourseWithInfo = "SELECT `course`.`course_id`, `course`.`course_info_id`, \n" +
            "            `course`.`start`, `course`.`end`, `course`.`coordinator_id`,\n" +
            "            `course_info`.`name`, `course_info`.`description`,`user`.`first_name`,`user`.last_name FROM `course` \n" +
            "            JOIN `course_info` ON `course`.`course_info_id` = `course_info`.`course_info_id`\n" +
            "            JOIN `user` ON course.coordinator_id = `user`.user_id\n" +
            "            WHERE `course`.`course_id` = ?";
    private static final String queryUpdateCourse = "UPDATE `course` SET `course`.`course_info_id` = ?, " +
            "`course`.`coordinator_id` = ?, `course`.`start` = ?, `course`.`end` = ? WHERE `course`.`course_id` = ?";
    private static final String queryDeleteCourse = "DELETE FROM `course` WHERE `course_id` = ?";
    private static final String queryFindCoursesByUserId = "SELECT\n" +
            "  `course`.`course_id`, `course`.`course_info_id`,`course`.`start`,`course`.`end`,\n" +
            "  `course`.`coordinator_id`, `course_info`.`name`,`course_info`.`description`, `user`.`first_name`,`user`.last_name\n" +
            "FROM `student_group`\n" +
            "  JOIN `group` ON student_group.group_id = `group`.group_id\n" +
            "  JOIN course ON `group`.course_id = course.course_id\n" +
            "  JOIN course_info ON course.course_info_id = course_info.course_info_id\n" +
            "  JOIN user ON course.coordinator_id = `user`.user_id\n" +
            "WHERE student_group.student_id = ?";
    private static final String queryFindCoursesByCoordinatorId = "SELECT\n" +
            "  `course`.`course_id`,`course`.`course_info_id`, `course`.`start`,`course`.`end`,\n" +
            "  `course`.`coordinator_id`,`course_info`.`name`,`course_info`.`description`,`user`.`first_name`,`user`.`last_name`\n" +
            "FROM `course`\n" +
            "  JOIN `course_info` ON `course`.`course_info_id` = `course_info`.`course_info_id`\n" +
            "  JOIN `user` ON course.coordinator_id = `user`.user_id\n" +
            " WHERE coordinator_id=?";

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Course> rowMapper = ((resultSet, i) -> {
        Course course = new Course();
        course.setCourseId(resultSet.getLong("course_id"));
        course.setCoordinatorId(resultSet.getLong("coordinator_id"));
        course.setCourseInfoId(resultSet.getLong("course_info_id"));
        course.setStart(resultSet.getTimestamp("start").toLocalDateTime());
        course.setEnd(resultSet.getTimestamp("end").toLocalDateTime());
        return course;
    });

    private RowMapper<CourseWithInfo> rowMapperWithInfo = ((resultSet, i) -> {
        CourseWithInfo courseWithInfo = new CourseWithInfo();
        courseWithInfo.setCourseId(resultSet.getLong("course_id"));
        courseWithInfo.setCoordinatorId(resultSet.getLong("coordinator_id"));
        courseWithInfo.setCourseInfoId(resultSet.getLong("course_info_id"));
        courseWithInfo.setStart(resultSet.getTimestamp("start").toLocalDateTime().toString());
        courseWithInfo.setEnd(resultSet.getTimestamp("end").toLocalDateTime().toString());
        courseWithInfo.setName(resultSet.getString("name"));
        courseWithInfo.setDescription(resultSet.getString("description"));
        courseWithInfo.setFirstName(resultSet.getString("first_name"));
        courseWithInfo.setLastName(resultSet.getString("last_name"));
        return courseWithInfo;
    });

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean addCourse(Course course) {
        return jdbcTemplate.update(queryAddCourse, course.getCourseInfoId(), course.getCoordinatorId(),
                course.getStart(), course.getEnd()) > 0;
    }

    @Override
    public List<Course> findCourses() {
        return jdbcTemplate.query(queryFindCourses, rowMapper);
    }

    @Override
    public List<CourseWithInfo> findCoursesWithInfo() {
        return jdbcTemplate.query(queryFindCoursesWithInfo, rowMapperWithInfo);
    }

    @Override
    public List<CourseWithInfo> findCoursesWithInfoByUserId(long userId) {
        return jdbcTemplate.query(queryFindCoursesByUserId, new Object[]{userId}, rowMapperWithInfo);
    }

    @Override
    public List<CourseWithInfo> findCoursesWithInfoByCoordinatorId(long userId) {
        return jdbcTemplate.query(queryFindCoursesByCoordinatorId, new Object[]{userId}, rowMapperWithInfo);
    }

    @Override
    public Optional<Course> findCourse(long courseId) {
        List<Course> queryResults = jdbcTemplate.query(queryFindCourse, new Object[]{courseId}, rowMapper);
        return ListHelper.getFirst(queryResults);
    }

    @Override
    public Optional<CourseWithInfo> findCourseWithInfo(long courseId) {
        List<CourseWithInfo> queryResults = jdbcTemplate.query(queryFindCourseWithInfo, new Object[]{courseId}, rowMapperWithInfo);
        return ListHelper.getFirst(queryResults);
    }

    @Override
    public boolean updateCourse(Course course) {
        return jdbcTemplate.update(queryUpdateCourse, course.getCourseInfoId(), course.getCoordinatorId(),
                course.getStart(), course.getEnd(), course.getCourseId()) > 0;
    }

    @Override
    public boolean deleteCourse(long courseId) {
        return jdbcTemplate.update(queryDeleteCourse, courseId) > 0;
    }
}
