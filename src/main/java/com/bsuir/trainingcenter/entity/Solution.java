package com.bsuir.trainingcenter.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Solution {

    private long taskId;
    private long userId;
    private String notes;
    private String filepath;
    private String teacherNotes;
    private long mark;
    private LocalDateTime uploadTime;

    public Solution() {
    }

    public Solution(long taskId, long userId, String notes, String filepath, String teacherNotes, long mark, LocalDateTime uploadTime) {
        this.taskId = taskId;
        this.userId = userId;
        this.notes = notes;
        this.filepath = filepath;
        this.teacherNotes = teacherNotes;
        this.mark = mark;
        this.uploadTime = uploadTime;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getTeacherNotes() {
        return teacherNotes;
    }

    public void setTeacherNotes(String teacherNotes) {
        this.teacherNotes = teacherNotes;
    }

    public long getMark() {
        return mark;
    }

    public void setMark(long mark) {
        this.mark = mark;
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
        Solution solution = (Solution) o;
        return taskId == solution.taskId &&
                userId == solution.userId &&
                mark == solution.mark &&
                Objects.equals(notes, solution.notes) &&
                Objects.equals(filepath, solution.filepath) &&
                Objects.equals(teacherNotes, solution.teacherNotes) &&
                Objects.equals(uploadTime, solution.uploadTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(taskId, userId, notes, filepath, teacherNotes, mark, uploadTime);
    }

    @Override
    public String toString() {
        return "Solution{" +
                "taskId=" + taskId +
                ", userId=" + userId +
                ", notes='" + notes + '\'' +
                ", filepath='" + filepath + '\'' +
                ", teacherNotes='" + teacherNotes + '\'' +
                ", mark=" + mark +
                ", uploadTime=" + uploadTime +
                '}';
    }

}
