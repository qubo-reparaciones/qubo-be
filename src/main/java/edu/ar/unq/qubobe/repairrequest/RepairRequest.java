package edu.ar.unq.qubobe.repairrequest;

import ar.com.kfgodel.nary.api.optionals.Optional;
import edu.ar.unq.qubobe.customer.Customer;
import edu.ar.unq.qubobe.itemtorepair.ItemToRepair;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RepairRequest {
    public static final String COST_NOT_BE_LESS_THAN_ZERO = "El costo no puede ser menor a cero";
    public static final String DELIVERY_DATE_NOT_BE_BEFORE_THAN_CREATION_DATE = "La fecha y hora de entrega no " +
        "puede ser anterior a la fecha de creación";
    private final static String CREATED_STATUS = "CREATED";
    private final Customer customer;
    private final ItemToRepair itemToRepair;
    private final Optional<BigDecimal> cost;
    private final Optional<LocalDateTime> deliveryDate;
    private final String status;
    private final RepairRequestCreationInfo creationInfo;
    private final String technicianHasIt;

    private RepairRequest(Customer customer, ItemToRepair itemToRepair, RepairRequestCreationInfo creationInfo,
                          String technicianHasIt, Optional<BigDecimal> cost, Optional<LocalDateTime> deliveryDate) {
        this.customer = customer;
        this.itemToRepair = itemToRepair;
        this.creationInfo = creationInfo;
        this.technicianHasIt = technicianHasIt;
        this.cost = cost;
        this.deliveryDate = deliveryDate;
        this.status = CREATED_STATUS;
    }

    public static RepairRequest withDeliveryDateAndCost(Customer customer, ItemToRepair itemToRepair,
                                                        RepairRequestCreationInfo creationInfo, BigDecimal cost,
                                                        LocalDateTime deliveryDate) {
        assertIfLessThanZero(cost);
        assertIfLessThanCreationDate(creationInfo.getCreationDate(), deliveryDate);
        return new RepairRequest(customer, itemToRepair, creationInfo, creationInfo.getTechnician(), Optional.of(cost),
            Optional.of(deliveryDate));
    }

    public static RepairRequest noDeliveryDateOrCost(Customer customer, ItemToRepair itemToRepair,
                                                     RepairRequestCreationInfo creationInfo) {
        return new RepairRequest(customer, itemToRepair, creationInfo, creationInfo.getTechnician(), Optional.empty(),
            Optional.empty());
    }

    public static RepairRequest noDeliveryDate(Customer customer, ItemToRepair itemToRepair,
                                               RepairRequestCreationInfo creationInfo, BigDecimal cost) {
        assertIfLessThanZero(cost);
        return new RepairRequest(customer, itemToRepair, creationInfo, creationInfo.getTechnician(), Optional.of(cost),
            Optional.empty());
    }

    public static RepairRequest noCost(Customer customer, ItemToRepair itemToRepair,
                                       RepairRequestCreationInfo creationInfo, LocalDateTime deliveryDate) {
        assertIfLessThanCreationDate(creationInfo.getCreationDate(), deliveryDate);
        return new RepairRequest(customer, itemToRepair, creationInfo, creationInfo.getTechnician(), Optional.empty(),
            Optional.of(deliveryDate));
    }

    private static void assertIfLessThanCreationDate(LocalDateTime creationDate, LocalDateTime deliveryDate) {
        if (deliveryDate.isBefore(creationDate))
            throw new RuntimeException(DELIVERY_DATE_NOT_BE_BEFORE_THAN_CREATION_DATE);
    }

    private static void assertIfLessThanZero(BigDecimal cost) {
        if (cost.compareTo(BigDecimal.ZERO) < 0)
            throw new RuntimeException(COST_NOT_BE_LESS_THAN_ZERO);
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

    public Optional<BigDecimal> getCost() {
        return cost;
    }

    public Optional<LocalDateTime> getDeliveryDate() {
        return deliveryDate;
    }
}
