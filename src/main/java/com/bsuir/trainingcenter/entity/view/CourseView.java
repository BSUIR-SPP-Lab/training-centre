package com.bsuir.trainingcenter.entity.view;

import java.util.Objects;

public class CourseView {

    private long courseId;
    private long courseInfoId;
    private long coordinatorId;
    private String start;
    private String end;

    public CourseView(long courseId, long courseInfoId, long coordinatorId, String start, String end) {
        this.courseId = courseId;
        this.courseInfoId = courseInfoId;
        this.coordinatorId = coordinatorId;
        this.start = start;
        this.end = end;
    }

    public CourseView() {
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getCourseInfoId() {
        return courseInfoId;
    }

    public void setCourseInfoId(long courseInfoId) {
        this.courseInfoId = courseInfoId;
    }

    public long getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(long coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseView that = (CourseView) o;
        return courseId == that.courseId &&
                courseInfoId == that.courseInfoId &&
                coordinatorId == that.coordinatorId &&
                Objects.equals(start, that.start) &&
                Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {

        return Objects.hash(courseId, courseInfoId, coordinatorId, start, end);
    }

    @Override
    public String toString() {
        return "CourseView{" +
                "courseId=" + courseId +
                ", courseInfoId=" + courseInfoId +
                ", coordinatorId=" + coordinatorId +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}
