package edu.ar.unq.qubobe.repairrequest.model;

import ar.com.kfgodel.nary.api.optionals.Optional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static edu.ar.unq.qubobe.objectextensions.ObjectValidations.assertIfNone;

public class Budget {
    public static final String COST_NOT_BE_LESS_THAN_ZERO = "El costo no puede ser menor a cero";
    public static final String DELIVERY_DATE_NOT_BE_BEFORE_THAN_CREATION_DATE = "La fecha y hora de entrega no " +
        "puede ser anterior a la fecha de creación";
    public static final String REPAIR_REQUEST_NOT_BE_EMPTY = "El pedido de repaación no puede ser nulo o vacío";
    private final RepairRequest repairRequest;
    private final Optional<BigDecimal> cost;
    private final Optional<LocalDateTime> deliveryDate;
    private final Optional<String> description;

    public Budget(RepairRequest repairRequest, Optional<BigDecimal> cost, Optional<LocalDateTime> deliveryDate,
                  Optional<String> description) {
        this.repairRequest = repairRequest;
        this.cost = cost;
        this.deliveryDate = deliveryDate;
        this.description = description;
    }

    public static Budget create(RepairRequest repairRequest, BigDecimal cost, LocalDateTime deliveryDate, String description) {
        assertIfNone(repairRequest, REPAIR_REQUEST_NOT_BE_EMPTY);
        assertIfLessThanZero(cost);
        assertIfBeforeThanCreationDate(repairRequest.getCreationDate(), deliveryDate);
        return new Budget(repairRequest, Optional.ofNullable(cost), Optional.ofNullable(deliveryDate),
            Optional.ofNullable(description));
    }

    private static void assertIfBeforeThanCreationDate(LocalDateTime creationDate, LocalDateTime deliveryDate) {
        if (deliveryDate.isBefore(creationDate))
            throw new RuntimeException(DELIVERY_DATE_NOT_BE_BEFORE_THAN_CREATION_DATE);
    }

    private static void assertIfLessThanZero(BigDecimal cost) {
        if (cost.compareTo(BigDecimal.ZERO) < 0)
            throw new RuntimeException(COST_NOT_BE_LESS_THAN_ZERO);
    }

    public RepairRequest getRepairRequest() {
        return repairRequest;
    }

    public Optional<BigDecimal> getCost() {
        return cost;
    }

    public Optional<LocalDateTime> getDeliveryDate() {
        return deliveryDate;
    }

    public Optional<String> getDescription() {
        return description;
    }
}
