package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.GroupDAO;
import com.bsuir.trainingcenter.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class GroupDAOImpl implements GroupDAO {

    private static final String queryAddGroup = "INSERT INTO `group` (`course_id`, `coordinator_id`) VALUES (?, ?)";
    private static final String queryFindGroups = "SELECT `group`.`group_id`, `group`.`course_id`, " +
            "`group`.`coordinator_id` FROM `group`";
    private static final String queryFindGroupById = "SELECT `group`.`group_id`, `group`.`course_id`, " +
            "`group`.`coordinator_id` FROM `group` WHERE `group`.`group_id` = ?";
    private static final String queryUpdateGroup = "UPDATE `group` SET `group`.`course_id` = ?, " +
            "`group`.`coordinator_id` = ? WHERE `group`.`group_id` = ?";
    private static final String queryDeleteGroup = "DELETE FROM `group` WHERE `group_id` = ?";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Group> rowMapper = ((resultSet, i) -> {
        Group group = new Group();
        group.setGroupId(resultSet.getLong("group_id"));
        group.setCourseId(resultSet.getLong("course_id"));
        group.setCoordinatorId(resultSet.getLong("coordinator_id"));
        return group;
    });

    @Override
    public boolean addGroup(Group group) {
        return jdbcTemplate.update(queryAddGroup, group.getCourseId(), group.getCoordinatorId()) > 0;
    }

    @Override
    public List<Group> findGroups() {
        return jdbcTemplate.query(queryFindGroups, rowMapper);
    }

    @Override
    public Group findGroup(long groupId) {
        return jdbcTemplate.queryForObject(queryFindGroupById, new Object[]{groupId}, rowMapper);
    }

    @Override
    public boolean updateGroup(Group group) {
        return jdbcTemplate.update(queryUpdateGroup, group.getCourseId(), group.getCoordinatorId(),
                group.getGroupId()) > 0;
    }

    @Override
    public boolean deleteGroup(long groupId) {
        return jdbcTemplate.update(queryDeleteGroup, groupId) > 0;
    }
}
