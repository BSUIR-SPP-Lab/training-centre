package com.bsuir.trainingcenter.service;

import com.bsuir.trainingcenter.entity.Teacher;

import java.util.List;

public interface TeatcherService {
    boolean addTeacher(Teacher teacher);

    List<Teacher> findTeachers();

    List<Teacher> findGroupTeachers(long groupId);
}
