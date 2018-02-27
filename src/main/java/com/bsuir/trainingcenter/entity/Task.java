package com.bsuir.trainingcenter.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {

    private long taskId;
    private long teacherId;
    private long groupId;
    private long taskInfoId;
    private LocalDateTime uploadTime;

    public Task() {
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
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

    public long getTaskInfoId() {
        return taskInfoId;
    }

    public void setTaskInfoId(long taskInfoId) {
        this.taskInfoId = taskInfoId;
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
        Task task = (Task) o;
        return taskId == task.taskId &&
                teacherId == task.teacherId &&
                groupId == task.groupId &&
                taskInfoId == task.taskInfoId &&
                Objects.equals(uploadTime, task.uploadTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(taskId, teacherId, groupId, taskInfoId, uploadTime);
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", teacherId=" + teacherId +
                ", groupId=" + groupId +
                ", taskInfoId=" + taskInfoId +
                ", uploadTime=" + uploadTime +
                '}';
    }
}
