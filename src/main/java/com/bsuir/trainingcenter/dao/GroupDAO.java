package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Group;

import java.util.List;

public interface GroupDAO {

    boolean addGroup(Group group);

    List<Group> findGroups();

    Group findGroup(long id);

    boolean updateGroup(Group group);

    boolean deleteGroup(long id);
    
}
