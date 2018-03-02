package com.bsuir.trainingcenter.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class TaskInfo {

    private long taskInfoId;

    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @NotNull
    @Size(min = 1)
    private String body;

    public TaskInfo() {
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
