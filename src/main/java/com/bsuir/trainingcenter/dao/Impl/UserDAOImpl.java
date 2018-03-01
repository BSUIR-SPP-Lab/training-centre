package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.UserDAO;
import com.bsuir.trainingcenter.entity.Role;
import com.bsuir.trainingcenter.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private static final String queryAddUser = "INSERT INTO `user` (`login`, `password`, `role`, `email`, `phone`, " +
            "`first_name`, `last_name`) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String queryFindUsers = "SELECT `user`.`user_id`, `user`.`login`, `user`.`password`, " +
            "`user`.`role`, `user`.`email`, `user`.`phone`, `user`.`first_name`, `user`.`last_name`  FROM `user`";
    private static final String queryFindUserById = "SELECT `user`.`user_id`, `user`.`login`, `user`.`password`, " +
            "`user`.`role`, `user`.`email`, `user`.`phone`, `user`.`first_name`, `user`.`last_name`  FROM `user` " +
            "WHERE `user`.`user_id` = ?";
    private static final String queryUpdateUser = "UPDATE `user` SET `user`.`login` = ?, `user`.`password` = ?, " +
            "`user`.`role` = ?, `user`.`email` = ?, `user`.`phone` = ?, `user`.`first_name` = ?, " +
            "`user`.`last_name` = ? WHERE `user`.`user_id` = ?";
    private static final String queryDeleteUser = "DELETE FROM `user` WHERE `user`.`user_id` = ?";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

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

    @Override
    public boolean addUser(User user) {
        return jdbcTemplate.update(queryAddUser, user.getLogin(), user.getPassword(),
                user.getRole().toString(), user.getEmail(), user.getPhone(), user.getFirstName(),
                user.getLastName()) > 0;
    }

    @Override
    public List<User> findUsers() {
        return jdbcTemplate.query(queryFindUsers, rowMapper);
    }

    public User findUser(long userId) {
        return jdbcTemplate.queryForObject(queryFindUserById, new Object[]{userId}, rowMapper);
    }

    @Override
    public boolean updateUser(User user) {
        return jdbcTemplate.update(queryUpdateUser, user.getLogin(), user.getPassword(),
                user.getRole(), user.getEmail(), user.getPhone(), user.getFirstName(), user.getLastName(),
                user.getId()) > 0;
    }

    @Override
    public boolean deleteUser(long userId) {
        return jdbcTemplate.update(queryDeleteUser, userId) > 0;
    }

}
