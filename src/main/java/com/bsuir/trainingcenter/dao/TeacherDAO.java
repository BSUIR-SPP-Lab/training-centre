package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Teacher;

import java.util.List;

public interface TeacherDAO {

    boolean addTeacher(Teacher teacher);

    List<Teacher> findTeachers();

    List<Teacher> findTeacher(long teacherId);

    boolean updateTeacher(Teacher teacher);

}
