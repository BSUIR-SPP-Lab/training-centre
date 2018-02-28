package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Student;
import com.bsuir.trainingcenter.entity.StudentGroup;

import java.util.List;

public interface StudentGroupDAO {

    boolean addStudentGroup(StudentGroup studentGroup);

    List<StudentGroup> findStudentGroups();

    boolean updateStudentGroup(StudentGroup studentGroup);
    
}
