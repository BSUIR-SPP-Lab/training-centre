package com.bsuir.trainingcenter.service;

import com.bsuir.trainingcenter.entity.StudentGroup;

import java.util.List;
import java.util.Optional;

public interface StudentGroupService {
    boolean addStudentGroup(StudentGroup studentGroup);

    List<StudentGroup> findStudentGroups();

    Optional<StudentGroup> findStudentGroup(long studentId, long groupId);

    boolean updateStudentGroup(StudentGroup studentGroup);

    boolean deleteStudentGroup(long studentId, long groupId);

}
