package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Student;

import java.util.List;

public interface GroupStudentDAO {

    List<Student> findStudentsInGroup(long groupId);

}
