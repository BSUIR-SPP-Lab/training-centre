package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.GroupStudentDAO;
import com.bsuir.trainingcenter.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class GroupStudentDAOImpl implements GroupStudentDAO {

    private static final String queryFindStudentsInGroup = "SELECT `student_group`.`student_id` FROM `student_group`" +
            "WHERE `student_group`.`group_id` = ?";


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
    public List<Student> findStudentsInGroup(long groupId) {
        return jdbcTemplate.query(queryFindStudentsInGroup, new Object[]{groupId}, rowMapper);
    }

}
