package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Student;

import java.util.List;

public interface StudentDAO {

    boolean addStudent(Student student);

    List<Student> findStudents();

    Student findStudent(long studentId);

    boolean updateStudent(Student student);

    boolean deleteStudent(long studentId);
    
}
