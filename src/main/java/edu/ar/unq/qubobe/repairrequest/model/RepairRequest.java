package edu.ar.unq.qubobe.repairrequest;

import ar.com.kfgodel.nary.api.optionals.Optional;
import edu.ar.unq.qubobe.customer.Customer;
import edu.ar.unq.qubobe.itemtorepair.ItemToRepair;

import java.time.LocalDateTime;

public class RepairRequest {
    private final static String CREATED_STATUS = "CREATED";
    private final Customer customer;
    private final ItemToRepair itemToRepair;
    private final String status;
    private final RepairRequestCreationInfo creationInfo;
    private final String technicianHasIt;

    private RepairRequest(Customer customer, ItemToRepair itemToRepair, RepairRequestCreationInfo creationInfo,
                          String technicianHasIt) {
        this.customer = customer;
        this.itemToRepair = itemToRepair;
        this.creationInfo = creationInfo;
        this.technicianHasIt = technicianHasIt;
        this.status = CREATED_STATUS;
    }

    public static RepairRequest filledWith(Customer customer, ItemToRepair itemToRepair,
                                           RepairRequestCreationInfo creationInfo) {
        return new RepairRequest(customer, itemToRepair, creationInfo, creationInfo.getTechnician());
    }

    public Customer getCustomer() {
        return customer;
    }

    public ItemToRepair getItemToRepair() {
        return itemToRepair;
    }

    public LocalDateTime getCreationDate() {
        return creationInfo.getCreationDate();
    }

    public String getTechnicianRegistered() {
        return creationInfo.getTechnician();
    }

    public String getTechnicianHasIt() {
        return technicianHasIt;
    }

    public String getStatus() {
        return status;
    }

    public Optional<String> getReportedProblems() {
        return creationInfo.getReportedProblem();
    }

    public Optional<String> getNotes() {
        return creationInfo.getNotes();
    }
}
