package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.CourseStudentDAO;
import com.bsuir.trainingcenter.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CourseStudentDAOImpl implements CourseStudentDAO {

    private static final String queryFindCourseStudents = "SELECT `student_group`.`student_id` FROM `student_group` " +
            "INNER JOIN `group` ON `group`.`group_id` = `student_group`.`group_id` " +
            "WHERE `group`.`course_id` = ?";


    private JdbcTemplate jdbcTemplate;

    private RowMapper<Student> rowMapper = ((resultSet, i) -> {
        Student student = new Student();
        student.setStudentId(resultSet.getLong("student_id"));
        return student;
    });

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Student> findCourseStudents(long courseId) {
        return jdbcTemplate.query(queryFindCourseStudents, new Object[]{courseId}, rowMapper);
    }

}
