package com.bsuir.trainingcenter.service;

import com.bsuir.trainingcenter.entity.StudentGroup;

import java.util.List;

public interface StudentGroupService {
    boolean addStudentGroup(StudentGroup studentGroup);

    List<StudentGroup> findStudentGroups();

    boolean updateStudentGroup(StudentGroup studentGroup);

}
