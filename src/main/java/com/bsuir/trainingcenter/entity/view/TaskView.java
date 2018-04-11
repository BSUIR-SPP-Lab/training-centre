package com.bsuir.trainingcenter.entity.view;

import java.util.Objects;

public class TaskView {

    private long taskId;
    private long teacherId;
    private long groupId;
    private long taskInfoId;
    private String uploadTime;

    public TaskView(long taskId, long teacherId, long groupId, long taskInfoId, String uploadTime) {
        this.taskId = taskId;
        this.teacherId = teacherId;
        this.groupId = groupId;
        this.taskInfoId = taskInfoId;
        this.uploadTime = uploadTime;
    }

    public TaskView() {
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

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskView taskView = (TaskView) o;
        return taskId == taskView.taskId &&
                teacherId == taskView.teacherId &&
                groupId == taskView.groupId &&
                taskInfoId == taskView.taskInfoId &&
                Objects.equals(uploadTime, taskView.uploadTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(taskId, teacherId, groupId, taskInfoId, uploadTime);
    }

    @Override
    public String toString() {
        return "TaskView{" +
                "taskId=" + taskId +
                ", teacherId=" + teacherId +
                ", groupId=" + groupId +
                ", taskInfoId=" + taskInfoId +
                ", uploadTime='" + uploadTime + '\'' +
                '}';
    }
}
