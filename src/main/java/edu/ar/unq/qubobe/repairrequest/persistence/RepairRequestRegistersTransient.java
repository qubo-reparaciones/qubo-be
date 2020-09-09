package edu.ar.unq.qubobe.repairrequest.persistence;

import edu.ar.unq.qubobe.repairrequest.model.RepairRequest;

import java.util.ArrayList;
import java.util.List;

public class RepairRequestRegistersTransient implements RepairRequestRegisters {
    private final List<RepairRequest> repairRequests;

    public RepairRequestRegistersTransient() {
        this.repairRequests = new ArrayList<>();
    }

    //    @Override
//    public boolean hasCustomerIdentifiedAs(String dni) {
//        return this.repairRequests.stream().anyMatch(request -> request.isIdentifiedAs(dni));
//    }

    @Override
    public void add(RepairRequest repairRequest) {
        this.repairRequests.add(repairRequest);
    }

    @Override
    public List<RepairRequest> getAll() {
        return this.repairRequests;
    }

    @Override
    public RepairRequest getById(String repairRequestId) {
        return repairRequests.get(Integer.parseInt(repairRequestId));
    }
}
