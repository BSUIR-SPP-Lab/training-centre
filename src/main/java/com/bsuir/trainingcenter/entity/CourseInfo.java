package com.bsuir.trainingcenter.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class CourseInfo {

    private long courseInfoId;
    @NotNull
    @Size(min=1,max = 50)
    private String name;
    //can be null
    private String description;

    public CourseInfo() {
    }

    public CourseInfo(long courseInfoId, String name, String description) {
        this.courseInfoId = courseInfoId;
        this.name = name;
        this.description = description;
    }

    public long getCourseInfoId() {
        return courseInfoId;
    }

    public void setCourseInfoId(long courseInfoId) {
        this.courseInfoId = courseInfoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseInfo that = (CourseInfo) o;
        return courseInfoId == that.courseInfoId &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(courseInfoId, name, description);
    }

    @Override
    public String toString() {
        return "CourseInfo{" +
                "courseInfoId=" + courseInfoId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
