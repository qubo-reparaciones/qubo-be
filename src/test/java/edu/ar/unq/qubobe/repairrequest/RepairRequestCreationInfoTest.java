package edu.ar.unq.qubobe.repairrequest;

import ar.com.kfgodel.nary.api.optionals.Optional;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RepairRequestCreationInfoTest {
    @Test
    void whenCreateAValidRepairRequestCreationInfoHasTheValuesThatReceived() {
        LocalDateTime creationDate = LocalDateTime.now();
        String technician = "technician";
        String notes = "notes";
        String reportedProblem = "reportedProblem";

        RepairRequestCreationInfo creationInfo = RepairRequestCreationInfo.filledWith(creationDate, technician, notes,
            reportedProblem);

        assertThat(creationInfo.getCreationDate(), is(creationDate));
        assertThat(creationInfo.getTechnician(), is(technician));
        assertThat(creationInfo.getNotes(), is(Optional.of(notes)));
        assertThat(creationInfo.getReportedProblem(), is(Optional.of(reportedProblem)));
    }

    @Test
    void canNotCreateRepairRequestCreationInfoWithoutCreationDate() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> RepairRequestCreationInfo.filledWith(null, "technician", "notes", "reportedProblem"));
        assertThat(thrown.getMessage(), is(RepairRequestCreationInfo.CREATION_DATE_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateRepairRequestCreationInfoWithoutTechnician() {
        LocalDateTime creationDate = LocalDateTime.now();

        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> RepairRequestCreationInfo.filledWith(creationDate, "", "notes", "reportedProblem"));
        assertThat(thrown.getMessage(), is(RepairRequestCreationInfo.TECHNICIAN_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateRepairRequestCreationInfoWithEmptyTechnician() {
        LocalDateTime creationDate = LocalDateTime.now();

        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> RepairRequestCreationInfo.filledWith(creationDate, null, "notes", "reportedProblem"));
        assertThat(thrown.getMessage(), is(RepairRequestCreationInfo.TECHNICIAN_CAN_NOT_BE_EMPTY));
    }

    @Test
    void whenCreateARepairRequestCreationInfoWithOutNotesHasTheValuesThatReceived() {
        LocalDateTime creationDate = LocalDateTime.now();
        String technician = "technician";
        String notes = null;
        String reportedProblem = "reportedProblem";

        RepairRequestCreationInfo creationInfo = RepairRequestCreationInfo.filledWith(creationDate, technician, notes,
            reportedProblem);

        assertThat(creationInfo.getCreationDate(), is(creationDate));
        assertThat(creationInfo.getTechnician(), is(technician));
        assertThat(creationInfo.getNotes(), is(Optional.empty()));
        assertThat(creationInfo.getReportedProblem(), is(Optional.of(reportedProblem)));
    }

    @Test
    void whenCreateARepairRequestCreationInfoWithOutReportedProblemsHasTheValuesThatReceived() {
        LocalDateTime creationDate = LocalDateTime.now();
        String technician = "technician";
        String notes = "notes";
        String reportedProblem = null;

        RepairRequestCreationInfo creationInfo = RepairRequestCreationInfo.filledWith(creationDate, technician, notes,
            reportedProblem);

        assertThat(creationInfo.getCreationDate(), is(creationDate));
        assertThat(creationInfo.getTechnician(), is(technician));
        assertThat(creationInfo.getNotes(), is(Optional.of(notes)));
        assertThat(creationInfo.getReportedProblem(), is(Optional.ofNullable(reportedProblem)));
    }
}