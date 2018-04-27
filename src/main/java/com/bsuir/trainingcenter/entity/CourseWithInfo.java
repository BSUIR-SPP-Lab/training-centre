package com.bsuir.trainingcenter.entity;

import java.util.Objects;

public class CourseWithInfo {
    private long courseId;
    private long courseInfoId;
    private long coordinatorId;
    private String firstName;
    private String lastName;
    private String start;
    private String end;
    private String name;
    //can be null
    private String description;

    public CourseWithInfo() {
    }

    public CourseWithInfo(long courseId, long courseInfoId, long coordinatorId, String firstName, String lastName, String start, String end, String name, String description) {
        this.courseId = courseId;
        this.courseInfoId = courseInfoId;
        this.coordinatorId = coordinatorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.start = start;
        this.end = end;
        this.name = name;
        this.description = description;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getCourseInfoId() {
        return courseInfoId;
    }

    public void setCourseInfoId(long courseInfoId) {
        this.courseInfoId = courseInfoId;
    }

    public long getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(long coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseWithInfo that = (CourseWithInfo) o;
        return courseId == that.courseId &&
                courseInfoId == that.courseInfoId &&
                coordinatorId == that.coordinatorId &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(start, that.start) &&
                Objects.equals(end, that.end) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(courseId, courseInfoId, coordinatorId, firstName, lastName, start, end, name, description);
    }

    @Override
    public String toString() {
        return "CourseWithInfo{" +
                "courseId=" + courseId +
                ", courseInfoId=" + courseInfoId +
                ", coordinatorId=" + coordinatorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
