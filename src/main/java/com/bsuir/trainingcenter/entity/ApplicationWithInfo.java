package com.bsuir.trainingcenter.entity;

import java.util.Objects;

public class ApplicationWithInfo {

    private long applicationId;
    private long studentId;
    private String firstName;
    private String lastName;
    private long courseId;

    public ApplicationWithInfo() {
    }

    public ApplicationWithInfo(long applicationId, long studentId, String firstName, String lastName, long courseId) {
        this.applicationId = applicationId;
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        ApplicationWithInfo that = (ApplicationWithInfo) o;
        return applicationId == that.applicationId &&
                studentId == that.studentId &&
                courseId == that.courseId &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(applicationId, studentId, firstName, lastName, courseId);
    }

    @Override
    public String toString() {
        return "ApplicationWithInfo{" +
                "applicationId=" + applicationId +
                ", studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", courseId=" + courseId +
                '}';
    }
}
