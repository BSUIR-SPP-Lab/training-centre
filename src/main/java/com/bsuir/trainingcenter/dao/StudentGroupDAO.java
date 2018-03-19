package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.StudentGroup;

import java.util.List;

public interface StudentGroupDAO {

    boolean addStudentGroup(StudentGroup studentGroup);

    List<StudentGroup> findStudentGroups();

    StudentGroup findStudentGroup(long studentId, long groupId);

    boolean updateStudentGroup(StudentGroup studentGroup);

    boolean deleteStudentGroup(long studentId, long groupId);

}
