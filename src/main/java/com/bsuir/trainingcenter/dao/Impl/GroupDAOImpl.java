package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.GroupDAO;
import com.bsuir.trainingcenter.dao.Impl.Helpers.ListHelper;
import com.bsuir.trainingcenter.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class GroupDAOImpl implements GroupDAO {

    private static final String queryAddGroup = "INSERT INTO `group` (`course_id`, `coordinator_id`) VALUES (?, ?)";
    private static final String queryFindGroups = "SELECT `group`.`group_id`, `group`.`course_id`, " +
            "`group`.`coordinator_id` FROM `group`";
    private static final String queryFindGroupsByCourseId = "SELECT `group`.`group_id`, `group`.`course_id`, " +
            "`group`.`coordinator_id` FROM `group` WHERE course_id=?";
    private static final String queryFindGroupById = "SELECT `group`.`group_id`, `group`.`course_id`, " +
            "`group`.`coordinator_id` FROM `group` WHERE `group`.`group_id` = ?";
    private static final String queryUpdateGroup = "UPDATE `group` SET `group`.`course_id` = ?, " +
            "`group`.`coordinator_id` = ? WHERE `group`.`group_id` = ?";
    private static final String queryDeleteGroup = "DELETE FROM `group` WHERE `group_id` = ?";
    private static final String queryFindGroupIdByCourseAndUserId ="SELECT g.group_id FROM student_group\n" +
            "JOIN `group` g ON student_group.group_id = g.group_id\n" +
            "WHERE student_group.student_id=? AND g.course_id=?";


    private JdbcTemplate jdbcTemplate;
    private RowMapper<Group> rowMapper = ((resultSet, i) -> {
        Group group = new Group();
        group.setGroupId(resultSet.getLong("group_id"));
        group.setCourseId(resultSet.getLong("course_id"));
        group.setCoordinatorId(resultSet.getLong("coordinator_id"));
        return group;
    });

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean addGroup(Group group) {
        return jdbcTemplate.update(queryAddGroup, group.getCourseId(), group.getCoordinatorId()) > 0;
    }

    @Override
    public List<Group> findGroups() {
        return jdbcTemplate.query(queryFindGroups, rowMapper);
    }

    @Override
    public List<Group> findGroupsByCourseId(long courseId) {
        return jdbcTemplate.query(queryFindGroupsByCourseId, new Object[]{courseId}, rowMapper);
    }

    @Override
    public Optional<Group> findGroup(long groupId) {
        List<Group> queryResults = jdbcTemplate.query(queryFindGroupById, new Object[]{groupId}, rowMapper);
        return ListHelper.getFirst(queryResults);
    }

    @Override
    public List<Long> findGroupIdByCourseAndUserId(long userId, long courseId){
        return jdbcTemplate.query(queryFindGroupIdByCourseAndUserId, new Object[]{userId, courseId}, (resultSet, i) -> resultSet.getLong("group_id"));

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
