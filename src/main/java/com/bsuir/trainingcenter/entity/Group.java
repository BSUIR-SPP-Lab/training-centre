package com.bsuir.trainingcenter.entity;

import java.util.Objects;

public class Group {

    private long groupId;
    private long courseId;
    private long coordinatorId;

    public Group() {
    }

    public Group(long courseId, long coordinatorId) {
        this.groupId = 0;
        this.courseId = courseId;
        this.coordinatorId = coordinatorId;
    }

    public Group(long groupId, long courseId, long coordinatorId) {
        this.groupId = groupId;
        this.courseId = courseId;
        this.coordinatorId = coordinatorId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(long coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return groupId == group.groupId &&
                courseId == group.courseId &&
                coordinatorId == group.coordinatorId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(groupId, courseId, coordinatorId);
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", courseId=" + courseId +
                ", coordinatorId=" + coordinatorId +
                '}';
    }

}
