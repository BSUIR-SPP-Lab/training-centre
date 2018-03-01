package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.TeacherDAO;
import com.bsuir.trainingcenter.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class TeacherDAOImpl implements TeacherDAO {

    private static final String queryAddTeacher = "INSERT INTO `teacher` (`teacher_id`, `group_id`) VALUES (?, ?)";
    private static final String queryFindTeachers = "SELECT `teacher_id`, `group_id` `teacher` FROM `teacher`";
    private static final String queryFindTeacherByUserId = "SELECT `teacher_id`, `group_id` `teacher` FROM `teacher` " +
            "WHERE `group_id` = ?";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Teacher> rowMapper = ((resultSet, i) -> {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(resultSet.getLong("teacher_id"));
        teacher.setGroupId(resultSet.getLong("group_id"));
        return teacher;
    });

    @Override
    public boolean addTeacher(Teacher teacher) {
        return jdbcTemplate.update(queryAddTeacher, teacher.getTeacherId(), teacher.getGroupId()) > 0;
    }

    @Override
    public List<Teacher> findTeachers() {
        return jdbcTemplate.query(queryFindTeachers, rowMapper);
    }

    @Override
    public List<Teacher> findTeacher(long groupId) {
        return jdbcTemplate.query(queryFindTeacherByUserId, new Object[]{groupId}, rowMapper);
    }

}
