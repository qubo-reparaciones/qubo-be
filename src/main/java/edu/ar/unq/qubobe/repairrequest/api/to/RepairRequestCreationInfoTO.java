package edu.ar.unq.qubobe.repairrequest.api.to;

import java.time.LocalDateTime;

public class RepairRequestCreationInfoTO {
    private LocalDateTime creationDate;
    private String technician;
    private String notes;
    private String reportedProblem;

    public RepairRequestCreationInfoTO(LocalDateTime creationDate, String technician, String notes, String reportedProblem) {
        this.creationDate = creationDate;
        this.technician = technician;
        this.notes = notes;
        this.reportedProblem = reportedProblem;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getReportedProblem() {
        return reportedProblem;
    }

    public void setReportedProblem(String reportedProblem) {
        this.reportedProblem = reportedProblem;
    }
}
