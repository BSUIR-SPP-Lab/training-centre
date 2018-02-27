package com.bsuir.trainingcenter.entity;

import java.util.Objects;

public class Teacher {

    private long teacherId;
    private long groupId;

    public Teacher() {
    }

    public Teacher(long teacherId, long groupId) {
        this.teacherId = teacherId;
        this.groupId = groupId;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return teacherId == teacher.teacherId &&
                groupId == teacher.groupId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(teacherId, groupId);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", groupId=" + groupId +
                '}';
    }
}
