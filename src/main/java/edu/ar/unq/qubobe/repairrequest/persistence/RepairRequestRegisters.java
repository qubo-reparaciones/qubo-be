package edu.ar.unq.qubobe.repairrequest.persistence;

import edu.ar.unq.qubobe.repairrequest.model.RepairRequest;

import java.util.List;

public interface RepairRequestRegisters {
    void add(RepairRequest repairRequest);

    List<RepairRequest> getAll();

    RepairRequest getById(String repairRequestId);
}
