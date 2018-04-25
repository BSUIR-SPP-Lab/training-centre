package com.bsuir.trainingcenter.entity.view;

import java.util.Objects;

public class SolutionWithTaskView extends SolutionView {
    private String name;
    private String body;
    private String firstName;
    private String lastName;

    public SolutionWithTaskView(long taskId, long userId, String notes, String filepath, String teacherNotes, Long mark, String uploadTime, String name, String body, String firstName, String lastName) {
        super(taskId, userId, notes, filepath, teacherNotes, mark, uploadTime);
        this.name = name;
        this.body = body;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public SolutionWithTaskView() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SolutionWithTaskView that = (SolutionWithTaskView) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(body, that.body) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), name, body, firstName, lastName);
    }

    @Override
    public String toString() {
        return "SolutionWithTaskView{" +
                "name='" + name + '\'' +
                ", body='" + body + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
