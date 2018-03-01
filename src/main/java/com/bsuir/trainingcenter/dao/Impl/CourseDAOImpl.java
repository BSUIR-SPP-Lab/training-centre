package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.CourseDAO;
import com.bsuir.trainingcenter.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    private static final String queryAddCourse = "INSERT INTO `course` (`course_info_id`, `start`, `end`)" +
            "VALUES (?, ?, ?)";
    private static final String queryFindCourses = "SELECT `course`.`course_id`, `course`.`course_info_id`, " +
            "`course`.`start`, `course`.`end` FROM `course`";
    private static final String queryFindCourse = "SELECT `course`.`course_id`, `course`.`course_info_id`, " +
            "`course`.`start`, `course`.`end` FROM `course` WHERE `course`.`course_id` = ?";
    private static final String queryUpdateCourse = "UPDATE `course` SET `course`.`course_info_id` = ?, " +
            "`course`.`start` = ?, `course`.`end` = ? WHERE `course`.`course_id` = ?";
    private static final String queryDeleteCourse = "DELETE FROM `course` WHERE `course_id` = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Course> rowMapper = ((resultSet, i) -> {
        Course course = new Course();
        course.setCourseId(resultSet.getLong("course_id"));
        course.setCourseInfoId(resultSet.getLong("course_info_id"));
        course.setStart(resultSet.getTimestamp("start").toLocalDateTime());
        course.setEnd(resultSet.getTimestamp("end").toLocalDateTime());
        return course;
    });

    @Override
    public boolean addCourse(Course course) {
        return jdbcTemplate.update(queryAddCourse, course.getCourseInfoId(), course.getStart(), course.getEnd()) > 0;
    }

    @Override
    public List<Course> findCourses() {
        return jdbcTemplate.query(queryFindCourses, rowMapper);
    }

    @Override
    public Course findCourse(long courseId) {
        return jdbcTemplate.queryForObject(queryFindCourse, new Object[]{courseId}, rowMapper);
    }

    @Override
    public boolean updateCourse(Course course) {
        return jdbcTemplate.update(queryUpdateCourse, course.getCourseInfoId(), course.getStart(), course.getEnd(),
                course.getCourseId()) > 0;
    }

    @Override
    public boolean deleteCourse(long courseId) {
        return jdbcTemplate.update(queryDeleteCourse, courseId) > 0;
    }
}
