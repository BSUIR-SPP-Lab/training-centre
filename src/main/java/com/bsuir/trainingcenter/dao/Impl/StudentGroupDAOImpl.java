package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.StudentGroupDAO;
import com.bsuir.trainingcenter.entity.StudentGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class StudentGroupDAOImpl implements StudentGroupDAO {

    private static final String queryAddStudentGroup = "INSERT INTO `student_group` (`student_id`, `group_id`, " +
            "`course_complete`) VALUES (?, ?, ?)";
    private static final String queryFindStudentGroups = "SELECT `student_group`.`student_id`, " +
            "`student_group`.`group_id`, `student_group`.`course_complete` FROM `student_group`";
    private static final String queryUpdateStudentGroup = "UPDATE `student_group` " +
            "SET `student_group`.`course_complete` = ? " +
            "WHERE (`student_group`.`student_id` = ?) AND (`student_group`.`group_id` = ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<StudentGroup> rowMapper = ((resultSet, i) -> {
        StudentGroup studentGroup = new StudentGroup();
        studentGroup.setStudentId(resultSet.getLong("student_id"));
        studentGroup.setGroupId(resultSet.getLong("group_id"));
        studentGroup.setCourseComplete(resultSet.getBoolean("course_complete"));
        return studentGroup;
    });

    @Override
    public boolean addStudentGroup(StudentGroup studentGroup) {
        return jdbcTemplate.update(queryAddStudentGroup, studentGroup.getStudentId(), studentGroup.getGroupId(),
                studentGroup.isCourseComplete()) > 0;
    }

    @Override
    public List<StudentGroup> findStudentGroups() {
        return jdbcTemplate.query(queryFindStudentGroups, rowMapper);
    }

    @Override
    public boolean updateStudentGroup(StudentGroup studentGroup) {
        return jdbcTemplate.update(queryUpdateStudentGroup, studentGroup.isCourseComplete(),
                studentGroup.getStudentId(), studentGroup.getGroupId()) > 0;
    }
}
