package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Group;

import java.util.List;
import java.util.Optional;

public interface GroupDAO {

    boolean addGroup(Group group);

    List<Group> findGroups();

    Optional<Group> findGroup(long groupId);

    boolean updateGroup(Group group);

    boolean deleteGroup(long groupId);

}
