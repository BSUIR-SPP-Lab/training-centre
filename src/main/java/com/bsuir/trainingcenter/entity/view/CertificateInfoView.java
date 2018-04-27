package com.bsuir.trainingcenter.entity.view;

import java.time.LocalDateTime;
import java.util.Objects;

public class CertificateInfoView {

    private long certificateId;
    private String firstName;
    private String lastName;
    private String name;
    private String start;
    private String end;

    public CertificateInfoView(long certificateId, String firstName, String lastName, String name, String start, String end) {
        this.certificateId = certificateId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public CertificateInfoView() {
    }

    public long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(long certificateId) {
        this.certificateId = certificateId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        CertificateInfoView that = (CertificateInfoView) o;
        return certificateId == that.certificateId &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(name, that.name) &&
                Objects.equals(start, that.start) &&
                Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {

        return Objects.hash(certificateId, firstName, lastName, name, start, end);
    }

    @Override
    public String toString() {
        return "CertificateInfoView{" +
                "certificateId=" + certificateId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
