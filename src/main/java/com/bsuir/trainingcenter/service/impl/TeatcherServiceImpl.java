package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.TeacherDAO;
import com.bsuir.trainingcenter.entity.Teacher;
import com.bsuir.trainingcenter.service.TeatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeatcherServiceImpl implements TeatcherService {

    private final TeacherDAO dao;

    @Autowired
    public TeatcherServiceImpl(TeacherDAO dao) {
        this.dao = dao;
    }

    @Override
    public boolean addTeacher(Teacher teacher) {
        return dao.addTeacher(teacher);
    }

    @Override
    public List<Teacher> findTeachers() {
        return dao.findTeachers();
    }

    @Override
    public List<Teacher> findGroupTeachers(long groupId) {
        return dao.findGroupTeachers(groupId);
    }
}
