package edu.ar.unq.qubobe.repairrequest.api.to;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BudgetTO {
    private String repairRequestId;
    private BigDecimal cost;
    private LocalDateTime deliveryDate;
    private String description;

    public BudgetTO(String repairRequestId, BigDecimal cost, LocalDateTime deliveryDate, String description) {
        this.repairRequestId = repairRequestId;
        this.cost = cost;
        this.deliveryDate = deliveryDate;
        this.description = description;
    }

    public String getRepairRequestId() {
        return repairRequestId;
    }

    public void setRepairRequestId(String repairRequestId) {
        this.repairRequestId = repairRequestId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
