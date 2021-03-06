package com.bsuir.trainingcenter.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class TaskWithInfo {
    private long taskId;
    private String firstName;
    private String lastName;
    private long groupId;
    private String name;
    private String body;
    private LocalDateTime uploadTime;

    public TaskWithInfo() {
    }

    public TaskWithInfo(long taskId, String firstName, String lastName, long groupId, String name, String body, LocalDateTime uploadTime) {
        this.taskId = taskId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.groupId = groupId;
        this.name = name;
        this.body = body;
        this.uploadTime = uploadTime;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
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

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskWithInfo that = (TaskWithInfo) o;
        return taskId == that.taskId &&
                groupId == that.groupId &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(name, that.name) &&
                Objects.equals(body, that.body) &&
                Objects.equals(uploadTime, that.uploadTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(taskId, firstName, lastName, groupId, name, body, uploadTime);
    }

    @Override
    public String toString() {
        return "TaskWithInfo{" +
                "taskId=" + taskId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", groupId=" + groupId +
                ", name='" + name + '\'' +
                ", body='" + body + '\'' +
                ", uploadTime=" + uploadTime +
                '}';
    }
}
