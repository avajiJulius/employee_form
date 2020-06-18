package com.javaproject.employeerequest.domain.data;

import java.time.LocalDate;

public class PreviousEmployerData {
    private long previousEmployerId;
    private String organization;
    private String position;
    private String progress;
    private LocalDate workStart;
    private LocalDate workEnd;
    private String quitReason;

    public long getPreviousEmployerId() {
        return previousEmployerId;
    }

    public void setPreviousEmployerId(long previousEmployerId) {
        this.previousEmployerId = previousEmployerId;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public LocalDate getWorkStart() {
        return workStart;
    }

    public void setWorkStart(LocalDate workStart) {
        this.workStart = workStart;
    }

    public LocalDate getWorkEnd() {
        return workEnd;
    }

    public void setWorkEnd(LocalDate workEnd) {
        this.workEnd = workEnd;
    }

    public String getQuitReason() {
        return quitReason;
    }

    public void setQuitReason(String quitReason) {
        this.quitReason = quitReason;
    }
}
