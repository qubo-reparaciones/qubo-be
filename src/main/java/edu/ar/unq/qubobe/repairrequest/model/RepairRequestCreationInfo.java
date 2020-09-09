package edu.ar.unq.qubobe.repairrequest.model;

import ar.com.kfgodel.nary.api.optionals.Optional;

import java.time.LocalDateTime;

import static edu.ar.unq.qubobe.objectextensions.ObjectValidations.assertIfNoneOrEmpty;

public class RepairRequestCreationInfo {
    public static final String CREATION_DATE_NOT_BE_EMPTY = "Debe tener fecha de creación";
    public static final String TECHNICIAN_CAN_NOT_BE_EMPTY = "El tecnico no puede ser nulo o vacío";
    private final LocalDateTime creationDate;
    private final String technician;
    private final Optional<String> notes;
    private final Optional<String> reportedProblem;

    private RepairRequestCreationInfo(LocalDateTime creationDate, String technician, Optional<String> notes,
                                      Optional<String> reportedProblem) {
        this.creationDate = creationDate;
        this.technician = technician;
        this.notes = notes;
        this.reportedProblem = reportedProblem;
    }

    public static RepairRequestCreationInfo filledWith(LocalDateTime creationDate, String technician, String notes,
                                                       String reportedProblem) {
        assertIfDate(creationDate);
        assertIfNoneOrEmpty(technician, TECHNICIAN_CAN_NOT_BE_EMPTY);
        return new RepairRequestCreationInfo(creationDate, technician, Optional.ofNullable(notes),
            Optional.ofNullable(reportedProblem));
    }

    private static void assertIfDate(LocalDateTime creationDate) {
        if (!(creationDate instanceof LocalDateTime)) {
            throw new RuntimeException(CREATION_DATE_NOT_BE_EMPTY);
        }
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getTechnician() {
        return technician;
    }

    public Optional<String> getNotes() {
        return notes;
    }

    public Optional<String> getReportedProblem() {
        return reportedProblem;
    }
}
