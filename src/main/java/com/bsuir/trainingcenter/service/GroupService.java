package com.bsuir.trainingcenter.service;

import com.bsuir.trainingcenter.entity.Group;

import java.util.List;

public interface GroupService {

    boolean addGroup(Group group);

    List<Group> findGroups();

    List<Group> findGroupsByCourseId(long courseId);

    Group findGroup(long groupId);

    List<Long> findGroupIdByCourseAndUserId(long userId, long courseId);

    boolean updateGroup(Group group);

    boolean deleteGroup(long groupId);
}
