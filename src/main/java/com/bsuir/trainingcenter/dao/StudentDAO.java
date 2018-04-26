package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDAO {

    List<Student> findStudents();

    Optional<Student> findStudent(long studentId);

    boolean deleteStudent(long studentId);

}
