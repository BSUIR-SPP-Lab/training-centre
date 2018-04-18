package com.bsuir.trainingcenter.entity;

import java.util.Objects;

public class    TaskInfo {

    private long taskInfoId;
    private String name;
    private String body;

    public TaskInfo() {
    }

    public TaskInfo(String name, String body) {
        this.taskInfoId = 0;
        this.name = name;
        this.body = body;
    }

    public TaskInfo(long taskInfoId, String name, String body) {
        this.taskInfoId = taskInfoId;
        this.name = name;
        this.body = body;
    }

    public long getTaskInfoId() {
        return taskInfoId;
    }

    public void setTaskInfoId(long taskInfoId) {
        this.taskInfoId = taskInfoId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskInfo taskInfo = (TaskInfo) o;
        return taskInfoId == taskInfo.taskInfoId &&
                Objects.equals(name, taskInfo.name) &&
                Objects.equals(body, taskInfo.body);
    }

    @Override
    public int hashCode() {

        return Objects.hash(taskInfoId, name, body);
    }

    @Override
    public String toString() {
        return "TaskInfo{" +
                "taskInfoId=" + taskInfoId +
                ", name='" + name + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

}
