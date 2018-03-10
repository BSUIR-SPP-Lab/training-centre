package com.bsuir.trainingcenter.entity.view;

import java.util.Objects;

public class SolutionView {
    private long taskId;
    private long userId;

    //can be null
    private String notes;
    private String filepath;
    private String teacherNotes;
    private Long mark;


    private String uploadTime;

    public SolutionView(long taskId, long userId, String notes, String filepath, String teacherNotes, Long mark, String uploadTime) {
        this.taskId = taskId;
        this.userId = userId;
        this.notes = notes;
        this.filepath = filepath;
        this.teacherNotes = teacherNotes;
        this.mark = mark;
        this.uploadTime = uploadTime;
    }

    public SolutionView() {
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

    public Long getMark() {
        return mark;
    }

    public void setMark(Long mark) {
        this.mark = mark;
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
        SolutionView that = (SolutionView) o;
        return taskId == that.taskId &&
                userId == that.userId &&
                Objects.equals(notes, that.notes) &&
                Objects.equals(filepath, that.filepath) &&
                Objects.equals(teacherNotes, that.teacherNotes) &&
                Objects.equals(mark, that.mark) &&
                Objects.equals(uploadTime, that.uploadTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(taskId, userId, notes, filepath, teacherNotes, mark, uploadTime);
    }

    @Override
    public String toString() {
        return "SolutionView{" +
                "taskId=" + taskId +
                ", userId=" + userId +
                ", notes='" + notes + '\'' +
                ", filepath='" + filepath + '\'' +
                ", teacherNotes='" + teacherNotes + '\'' +
                ", mark=" + mark +
                ", uploadTime='" + uploadTime + '\'' +
                '}';
    }
}
