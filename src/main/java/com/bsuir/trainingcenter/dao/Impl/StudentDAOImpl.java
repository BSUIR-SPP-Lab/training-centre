package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.StudentDAO;
import com.bsuir.trainingcenter.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private static final String queryAddStudent = "INSERT INTO `student` () VALUES ()";
    private static final String queryFindStudents = "SELECT `student`.`student_id` FROM `student`";
    private static final String queryFindStudentById = "SELECT `student`.`student_id` FROM `student` " +
            "WHERE `student`.`student_id` = ?";
    private static final String queryDeleteStudent = "DELETE FROM `student` WHERE `student`.`student_id` = ?";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Student> rowMapper = ((resultSet, i) -> {
        Student student = new Student();
        student.setStudentId(resultSet.getLong("student_id"));
        return student;
    });

    @Override
    public boolean addStudent(Student student) {
        return jdbcTemplate.update(queryAddStudent) > 0;
    }

    @Override
    public List<Student> findStudents() {
        return jdbcTemplate.query(queryFindStudents, rowMapper);
    }

    @Override
    public Student findStudent(long studentId) {
        return jdbcTemplate.queryForObject(queryFindStudentById, new Object[]{studentId}, rowMapper);
    }

    @Override
    public boolean updateStudent(Student student) {
        return true;
    }

    @Override
    public boolean deleteStudent(long studentId) {
        return jdbcTemplate.update(queryDeleteStudent, studentId) > 0;
    }
}
