package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.StudentGroup;

import java.util.List;

public interface StudentGroupDAO {

    boolean addStudentGroup(StudentGroup studentGroup);

    List<StudentGroup> findStudentGroups();

    StudentGroup findStudentGroup(long id);

    boolean updateStudentGroup(StudentGroup studentGroup);

    boolean deleteStudentGroup(long id);
    
}
