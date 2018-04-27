package com.bsuir.trainingcenter.entity;

import java.util.Objects;

public class StudentGroup {

    private long studentId;
    private long groupId;
    private boolean courseComplete;

    public StudentGroup() {
    }

    public StudentGroup(long studentId, long groupId, boolean courseComplete) {
        this.studentId = studentId;
        this.groupId = groupId;
        this.courseComplete = courseComplete;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public boolean isCourseComplete() {
        return courseComplete;
    }

    public void setCourseComplete(boolean courseComplete) {
        this.courseComplete = courseComplete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGroup that = (StudentGroup) o;
        return studentId == that.studentId &&
                groupId == that.groupId &&
                courseComplete == that.courseComplete;
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, groupId, courseComplete);
    }

    @Override
    public String toString() {
        return "StudentGroup{" +
                "studentId=" + studentId +
                ", groupId=" + groupId +
                ", courseComplete=" + courseComplete +
                '}';
    }
}
