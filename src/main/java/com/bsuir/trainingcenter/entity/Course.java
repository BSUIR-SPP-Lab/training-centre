package com.bsuir.trainingcenter.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Course {

    private long courseId;
    private long courseInfoId;
    private long coordinatorId;
    private LocalDateTime start;
    private LocalDateTime end;

    public Course() {
    }

    public Course(long courseId, long courseInfoId, long coordinatorId, LocalDateTime start, LocalDateTime end) {
        this.courseId = courseId;
        this.courseInfoId = courseInfoId;
        this.coordinatorId = coordinatorId;
        this.start = start;
        this.end = end;
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

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
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
        Course course = (Course) o;
        return courseId == course.courseId &&
                courseInfoId == course.courseInfoId &&
                Objects.equals(start, course.start) &&
                Objects.equals(end, course.end);
    }

    @Override
    public int hashCode() {

        return Objects.hash(courseId, courseInfoId, start, end);
    }

    @Override
    public String toString() {
        return "CourseDAO{" +
                "courseId=" + courseId +
                ", courseInfoId=" + courseInfoId +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
