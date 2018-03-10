package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.StudentGroupDAO;
import com.bsuir.trainingcenter.entity.StudentGroup;
import com.bsuir.trainingcenter.service.StudentGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentGroupServiceImpl implements StudentGroupService {

    @Autowired
    private StudentGroupDAO studentGroupDAO;

    @Override
    public boolean addStudentGroup(StudentGroup studentGroup) {
        return studentGroupDAO.addStudentGroup(studentGroup);
    }

    @Override
    public List<StudentGroup> findStudentGroups() {
        return studentGroupDAO.findStudentGroups();
    }

    @Override
    public boolean updateStudentGroup(StudentGroup studentGroup) {
        return studentGroupDAO.updateStudentGroup(studentGroup);
    }

    @Override
    public boolean deleteStudentGroup(long studentId, long groupId) {
        return studentGroupDAO.deleteStudentGroup(studentId, groupId);
    }
}
