package com.bsuir.trainingcenter.dao;

import com.bsuir.trainingcenter.entity.Student;

import java.util.List;

public interface CourseStudentDAO {

    List<Student> findCourseStudents(long courseId);

}
