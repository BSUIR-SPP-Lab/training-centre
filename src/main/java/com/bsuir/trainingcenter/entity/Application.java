package com.bsuir.trainingcenter.entity;

import java.util.Objects;

public class Application {

    private long applicationId;
    private long studentId;
    private long courseId;

    public Application() {
    }

    public Application(long studentId, long courseId) {
        this.applicationId = 0;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Application(long applicationId, long studentId, long courseId) {
        this.applicationId = applicationId;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return applicationId == that.applicationId &&
                studentId == that.studentId &&
                courseId == that.courseId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(applicationId, studentId, courseId);
    }

    @Override
    public String toString() {
        return "Application{" +
                "applicationId=" + applicationId +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
    
}
