package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Group;

import java.util.List;
import java.util.Optional;

public interface GroupDAO {

    boolean addGroup(Group group);

    List<Group> findGroups();

    List<Group> findGroupsByCourseId(long courseId);

    Optional<Group> findGroup(long groupId);

    List<Long> findGroupIdByCourseAndUserId(long userId, long courseId);

    boolean updateGroup(Group group);

    boolean deleteGroup(long groupId);

}
