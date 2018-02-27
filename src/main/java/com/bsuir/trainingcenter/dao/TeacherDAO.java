package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Teacher;

import java.util.List;

public interface TeacherDAO {

    boolean addTeacher(Teacher teacher);

    List<Teacher> findTeachers();

    Teacher findTeacher(long id);

    boolean updateTeacher(Teacher teacher);

    boolean deleteTeacher(long id);

}
