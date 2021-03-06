package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.Impl.Helpers.ListHelper;
import com.bsuir.trainingcenter.dao.UserDAO;
import com.bsuir.trainingcenter.entity.Role;
import com.bsuir.trainingcenter.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {

    private static final String queryAddUser = "INSERT INTO `user` (`login`, `password`, `role`, `email`, `phone`, " +
            "`first_name`, `last_name`) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String queryCheckLoginUnique = "SELECT (COUNT(*) = 0) FROM `user` WHERE `user`.`login` = ?";
    private static final String queryFindUsers = "SELECT `user`.`user_id`, `user`.`login`, `user`.`password`, " +
            "`user`.`role`, `user`.`email`, `user`.`phone`, `user`.`first_name`, `user`.`last_name`  FROM `user`";
    private static final String queryFindUserById = "SELECT `user`.`user_id`, `user`.`login`, `user`.`password`, " +
            "`user`.`role`, `user`.`email`, `user`.`phone`, `user`.`first_name`, `user`.`last_name`  FROM `user` " +
            "WHERE `user`.`user_id` = ?";
    private static final String queryFindUserByLogin = "SELECT `user`.`user_id`, `user`.`login`, `user`.`password`, " +
            "`user`.`role`, `user`.`email`, `user`.`phone`, `user`.`first_name`, `user`.`last_name`  FROM `user` " +
            "WHERE `user`.`login` = ?";
    private static final String queryUpdateUser = "UPDATE `user` SET `user`.`login` = ?, `user`.`password` = ?, " +
            "`user`.`role` = ?, `user`.`email` = ?, `user`.`phone` = ?, `user`.`first_name` = ?, " +
            "`user`.`last_name` = ? WHERE `user`.`user_id` = ?";
    private static final String queryUpdateUserRole = "UPDATE `user` SET `user`.`role` = ? WHERE `user`.`user_id` = ?";
    private static final String queryDeleteUser = "DELETE FROM `user` WHERE `user`.`user_id` = ?";
    private static final String queryFindUsersByCourseId="SELECT `u`.`user_id`, `u`.`login`, `u`.`password`, `u`.`role`, `u`.`email`, `u`.`phone`, `u`.`first_name`, `u`.`last_name` FROM course\n" +
            "JOIN `group` g ON course.course_id = g.course_id\n" +
            "JOIN student_group s ON g.group_id = s.group_id\n" +
            "JOIN `user` u ON s.student_id=u.user_id\n" +
            "WHERE `course`.course_id=?";
    private static final String queryFindUsersByGroupId="SELECT `u`.`user_id`, `u`.`login`, `u`.`password`, `u`.`role`, `u`.`email`, `u`.`phone`, `u`.`first_name`, `u`.`last_name` FROM course\n" +
            "JOIN `group` g ON course.course_id = g.course_id\n" +
            "JOIN student_group s ON g.group_id = s.group_id\n" +
            "JOIN `user` u ON s.student_id=u.user_id\n" +
            "WHERE `g`.group_id=?";

    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> rowMapper = ((resultSet, i) -> {
        User user = new User();
        user.setId(resultSet.getLong("user_id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(Role.valueOf(resultSet.getString("role").toUpperCase()));
        user.setEmail(resultSet.getString("email"));
        user.setPhone(resultSet.getString("phone"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        return user;
    });

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean addUser(User user) {
        return jdbcTemplate.update(queryAddUser, user.getLogin(), user.getPassword(),
                user.getRole().toString(), user.getEmail(), user.getPhone(), user.getFirstName(),
                user.getLastName()) > 0;
    }

    @Override
    public boolean isLoginUnique(String login) {
        return jdbcTemplate.queryForObject(queryCheckLoginUnique, Boolean.class,login);
    }

    @Override
    public List<User> findUsers() {
        return jdbcTemplate.query(queryFindUsers, rowMapper);
    }

    @Override
    public List<User> findUsersByCourseId(long courseId) {
        return jdbcTemplate.query(queryFindUsersByCourseId,new Object[]{courseId}, rowMapper);
    }

    @Override
    public List<User> findUsersByGroupId(long groupId) {
        return jdbcTemplate.query(queryFindUsersByGroupId,new Object[]{groupId}, rowMapper);
    }

    public Optional<User> findUser(long userId) {
        List<User> queryResults = jdbcTemplate.query(queryFindUserById, new Object[]{userId}, rowMapper);
        return ListHelper.getFirst(queryResults);
    }

    @Override
    public Optional<User> findUser(String login) {
        List<User> queryResults = jdbcTemplate.query(queryFindUserByLogin, new Object[]{login}, rowMapper);
        return ListHelper.getFirst(queryResults);
    }

    @Override
    public boolean updateUser(User user) {
        return jdbcTemplate.update(queryUpdateUser, user.getLogin(), user.getPassword(),
                user.getRole().toString(), user.getEmail(), user.getPhone(), user.getFirstName(), user.getLastName(),
                user.getId()) > 0;
    }

    @Override
    public boolean updateUserRole(long userId, Role newRole) {
        return jdbcTemplate.update(queryUpdateUserRole, newRole.toString(), userId) > 0;
    }

    @Override
    public boolean deleteUser(long userId) {
        return jdbcTemplate.update(queryDeleteUser, userId) > 0;
    }

}
