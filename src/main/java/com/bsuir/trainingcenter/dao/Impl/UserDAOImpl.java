package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.UserDAO;
import com.bsuir.trainingcenter.dao.rowMapper.UserRowMapper;
import com.bsuir.trainingcenter.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String queryAddUser = "INSERT INTO `user` (`login`, `password`, `role`, `email`, `phone`, " +
            "`first_name`, `last_name`) VALUES (?, ?, ?, ?, ?, ?, ?);";

    private String queryFindUsers = "SELECT `user`.`user_id`, `user`.`login`, `user`.`password`, `user`.`role`, " +
            "`user`.`email`, `user`.`phone`, `user`.`first_name`, " +
            "`user`.`last_name`  FROM `user`";

    private String queryFindUserById = "SELECT `user`.`user_id`, `user`.`login`, `user`.`password`, `user`.`role`, " +
            "`user`.`email`, `user`.`phone`, `user`.`first_name`, " +
            "`user`.`last_name`  FROM `user` WHERE `user`.`user_id` = ?";

    private String queryUpdateUser = "UPDATE `user` SET `login` = ?, `password` = ?, " +
            "`role` = ?, `email` = ?, `phone` = ?, `first_name` = ?, " +
            "`last_name` = ? WHERE `user_id` = ?";

    private String queryDeleteUser = "DELETE FROM `user` WHERE `user`.`user_id` = ?";

    @Override
    public boolean addUser(User user) {
        return jdbcTemplate.update(queryAddUser, user.getLogin(), user.getPassword(),
                user.getRole().toString(), user.getEmail(), user.getPhone(), user.getFirstName(),
                user.getLastName()) > 0;
    }

    public User findUser(long id) {
        return jdbcTemplate.queryForObject(queryFindUserById, new Object[]{id}, new UserRowMapper());
    }

    @Override
    public List<User> findUsers() {
        return jdbcTemplate.query(queryFindUsers, new UserRowMapper());
    }

    @Override
    public boolean updateUser(User user) {
        return jdbcTemplate.update(queryUpdateUser, user.getLogin(), user.getPassword(),
                user.getRole(), user.getEmail(), user.getPhone(), user.getFirstName(), user.getLastName(),
                user.getId()) > 0;
    }

    @Override
    public boolean deleteUser(long id) {
        return jdbcTemplate.update(queryDeleteUser, id) > 0;
    }
}
