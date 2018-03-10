package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.GroupDAO;
import com.bsuir.trainingcenter.entity.Group;
import com.bsuir.trainingcenter.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDAO groupServiceDAO;

    @Override
    public boolean addGroup(Group group) {
        return groupServiceDAO.addGroup(group);
    }

    @Override
    public List<Group> findGroups() {
        return groupServiceDAO.findGroups();
    }

    @Override
    public Group findGroup(long groupId) {
        return groupServiceDAO.findGroup(groupId);
    }

    @Override
    public boolean updateGroup(Group group) {
        return groupServiceDAO.updateGroup(group);
    }

    @Override
    public boolean deleteGroup(long groupId) {
        return groupServiceDAO.deleteGroup(groupId);
    }
}
