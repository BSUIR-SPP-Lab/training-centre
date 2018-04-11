package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.CourseInfoDAO;
import com.bsuir.trainingcenter.dao.Impl.Helpers.ListHelper;
import com.bsuir.trainingcenter.entity.CourseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseInfoDAOImpl implements CourseInfoDAO {

    private static final String queryAddCourseInfo = "INSERT INTO `course_info` (`name`, `description`) " +
            "VALUES (?, ?)";
    private static final String queryFindCoursesInfo = "SELECT `course_info`.`course_info_id`, `course_info`.`name`, " +
            "`course_info`.`description` FROM `course_info`";
    private static final String queryFindCourseInfoById = "SELECT `course_info`.`course_info_id`, " +
            "`course_info`.`name`, `course_info`.`description` FROM `course_info` " +
            "WHERE `course_info`.`course_info_id` = ?";
    private static final String queryUpdateCourseInfo = "UPDATE `course_info` SET `course_info`.`name` = ?, " +
            "`course_info`.`description` = ? WHERE `course_info`.`course_info_id` = ?";
    private static final String queryDeleteCourseInfo = "DELETE FROM `course_info` WHERE `course_info_id` = ?";


    private JdbcTemplate jdbcTemplate;
    private RowMapper<CourseInfo> rowMapper = ((resultSet, i) -> {
        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setCourseInfoId(resultSet.getLong("course_info_id"));
        courseInfo.setName(resultSet.getString("name"));
        courseInfo.setDescription(resultSet.getString("description"));
        return courseInfo;
    });

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean addCourseInfo(CourseInfo courseInfo) {
        return jdbcTemplate.update(queryAddCourseInfo, courseInfo.getName(), courseInfo.getDescription()) > 0;
    }

    @Override
    public List<CourseInfo> findCoursesInfo() {
        return jdbcTemplate.query(queryFindCoursesInfo, rowMapper);
    }

    @Override
    public Optional<CourseInfo> findCourseInfo(long courseInfoId) {
        List<CourseInfo> queryResults = jdbcTemplate.query(queryFindCourseInfoById, new Object[]{courseInfoId}, rowMapper);
        return ListHelper.getFirst(queryResults);
    }

    @Override
    public boolean updateCourseInfo(CourseInfo courseInfo) {
        return jdbcTemplate.update(queryUpdateCourseInfo, courseInfo.getName(), courseInfo.getDescription(),
                courseInfo.getCourseInfoId()) > 0;
    }

    @Override
    public boolean deleteCourseInfo(long courseInfoId) {
        return jdbcTemplate.update(queryDeleteCourseInfo, courseInfoId) > 0;
    }

}
