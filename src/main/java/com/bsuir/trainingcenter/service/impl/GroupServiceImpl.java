package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.GroupDAO;
import com.bsuir.trainingcenter.entity.Group;
import com.bsuir.trainingcenter.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupDAO groupDAO;

    @Autowired
    public GroupServiceImpl(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    @Override
    public boolean addGroup(Group group) {
        return groupDAO.addGroup(group);
    }

    @Override
    public List<Group> findGroups() {
        return groupDAO.findGroups();
    }

    @Override
    public List<Group> findGroupsByCourseId(long courseId) {
        return groupDAO.findGroupsByCourseId(courseId);
    }

    @Override
    public Group findGroup(long groupId) {
        return groupDAO.findGroup(groupId).get();
    }

    @Override
    public List<Long> findGroupIdByCourseAndUserId(long userId, long courseId) {
        return groupDAO.findGroupIdByCourseAndUserId(userId,courseId);
    }

    @Override
    public boolean updateGroup(Group group) {
        return groupDAO.updateGroup(group);
    }

    @Override
    public boolean deleteGroup(long groupId) {
        return groupDAO.deleteGroup(groupId);
    }
}
