package com.bsuir.trainingcenter.entity;

import java.util.Objects;

public class Certificate {

    private long certificateId;
    private long studentId;
    private long groupId;

    public Certificate() {
    }

    public Certificate(long studentId, long groupId) {
        this.certificateId = 0;
        this.studentId = studentId;
        this.groupId = groupId;
    }

    public Certificate(long certificateId, long studentId, long groupId) {
        this.certificateId = certificateId;
        this.studentId = studentId;
        this.groupId = groupId;
    }

    public long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(long certificateId) {
        this.certificateId = certificateId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Certificate that = (Certificate) o;
        return certificateId == that.certificateId &&
                studentId == that.studentId &&
                groupId == that.groupId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(certificateId, studentId, groupId);
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "certificateId=" + certificateId +
                ", studentId=" + studentId +
                ", groupId=" + groupId +
                '}';
    }

}
