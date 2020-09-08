package edu.ar.unq.qubobe.repairrequest;

import ar.com.kfgodel.nary.api.optionals.Optional;
import edu.ar.unq.qubobe.customer.Customer;
import edu.ar.unq.qubobe.itemtorepair.Article;
import edu.ar.unq.qubobe.itemtorepair.ItemToRepair;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RepairRequestTest {
    @Test
    void whenCreateARepairRequestNoDeliveryDateOrCostHasTheValuesThatReceived() {
        Customer customer = Customer.named("12345678", "name", "lastname", "123456", "algo@otracosa");
        Article article = Article.named("name", "brand", "model");
        ItemToRepair itemToRepair = ItemToRepair.withoutSecurity(article, "nroserie");
        LocalDateTime creationDate = LocalDateTime.now();
        String technician = "technician";
        String notes = "notes";
        String reportedProblem = "reportedProblem";
        RepairRequestCreationInfo creationInfo = RepairRequestCreationInfo.filledWith(creationDate, technician, notes,
            reportedProblem);

        RepairRequest repairRequest = RepairRequest.noDeliveryDateOrCost(customer, itemToRepair, creationInfo);

        assertThat(repairRequest.getCustomer(), is(customer));
        assertThat(repairRequest.getItemToRepair(), is(itemToRepair));
        assertThat(repairRequest.getCreationDate(), is(creationDate));
        assertThat(repairRequest.getTechnicianRegistered(), is("technician"));
        assertThat(repairRequest.getTechnicianHasIt(), is("technician"));
        assertThat(repairRequest.getNotes(), is(Optional.of(notes)));
        assertThat(repairRequest.getReportedProblems(), is(Optional.of(reportedProblem)));
        assertThat(repairRequest.getCost(), is(Optional.empty()));
        assertThat(repairRequest.getDeliveryDate(), is(Optional.empty()));
        assertThat(repairRequest.getStatus(), is("CREATED"));
    }

    @Test
    void whenCreateARepairRequestNoDeliveryDateHasTheValuesThatReceived() {
        Customer customer = Customer.named("12345678", "name", "lastname", "123456", "algo@otracosa");
        Article article = Article.named("name", "brand", "model");
        ItemToRepair itemToRepair = ItemToRepair.withoutSecurity(article, "nroserie");
        LocalDateTime creationDate = LocalDateTime.now();
        String technician = "technician";
        String notes = "notes";
        String reportedProblem = "reportedProblem";
        BigDecimal cost = BigDecimal.ONE;
        RepairRequestCreationInfo creationInfo = RepairRequestCreationInfo.filledWith(creationDate, technician, notes,
            reportedProblem);

        RepairRequest repairRequest = RepairRequest.noDeliveryDate(customer, itemToRepair, creationInfo, cost);

        assertThat(repairRequest.getCustomer(), is(customer));
        assertThat(repairRequest.getItemToRepair(), is(itemToRepair));
        assertThat(repairRequest.getCreationDate(), is(creationDate));
        assertThat(repairRequest.getTechnicianRegistered(), is("technician"));
        assertThat(repairRequest.getTechnicianHasIt(), is("technician"));
        assertThat(repairRequest.getNotes(), is(Optional.of(notes)));
        assertThat(repairRequest.getReportedProblems(), is(Optional.of(reportedProblem)));
        assertThat(repairRequest.getCost(), is(Optional.of(cost)));
        assertThat(repairRequest.getDeliveryDate(), is(Optional.empty()));
        assertThat(repairRequest.getStatus(), is("CREATED"));
    }

    @Test
    void whenCreateARepairRequestNoDeliveryDateAndCostLessThanZeroThenThrowAnError() {
        Customer customer = Customer.named("12345678", "name", "lastname", "123456", "algo@otracosa");
        Article article = Article.named("name", "brand", "model");
        ItemToRepair itemToRepair = ItemToRepair.withoutSecurity(article, "nroserie");
        LocalDateTime creationDate = LocalDateTime.now();
        String technician = "technician";
        String notes = "notes";
        String reportedProblem = "reportedProblem";
        BigDecimal lessThanZero = BigDecimal.ONE.negate();
        RepairRequestCreationInfo creationInfo = RepairRequestCreationInfo.filledWith(creationDate, technician, notes,
            reportedProblem);

        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> RepairRequest.noDeliveryDate(customer, itemToRepair, creationInfo, lessThanZero));
        assertThat(thrown.getMessage(), is(RepairRequest.COST_NOT_BE_LESS_THAN_ZERO));
    }

    @Test
    void whenCreateARepairRequestWithDeliveryDateAndCostHasTheValuesThatReceived() {
        Customer customer = Customer.named("12345678", "name", "lastname", "123456", "algo@otracosa");
        Article article = Article.named("name", "brand", "model");
        ItemToRepair itemToRepair = ItemToRepair.withoutSecurity(article, "nroserie");
        LocalDateTime creationDate = LocalDateTime.now();
        LocalDateTime deliveryDate = LocalDateTime.now().plusDays(1);
        String technician = "technician";
        String notes = "notes";
        String reportedProblem = "reportedProblem";
        BigDecimal cost = BigDecimal.ONE;
        RepairRequestCreationInfo creationInfo = RepairRequestCreationInfo.filledWith(creationDate, technician, notes,
            reportedProblem);

        RepairRequest repairRequest = RepairRequest.withDeliveryDateAndCost(customer, itemToRepair, creationInfo, cost,
            deliveryDate);

        assertThat(repairRequest.getCustomer(), is(customer));
        assertThat(repairRequest.getItemToRepair(), is(itemToRepair));
        assertThat(repairRequest.getCreationDate(), is(creationDate));
        assertThat(repairRequest.getTechnicianRegistered(), is("technician"));
        assertThat(repairRequest.getTechnicianHasIt(), is("technician"));
        assertThat(repairRequest.getNotes(), is(Optional.of(notes)));
        assertThat(repairRequest.getReportedProblems(), is(Optional.of(reportedProblem)));
        assertThat(repairRequest.getCost(), is(Optional.of(cost)));
        assertThat(repairRequest.getDeliveryDate(), is(Optional.of(deliveryDate)));
        assertThat(repairRequest.getStatus(), is("CREATED"));
    }

    @Test
    void whenCreateARepairRequestWithDeliveryDateAndDeliveryDateBeforeCreationDateThenThrowAnError() {
        Customer customer = Customer.named("12345678", "name", "lastname", "123456", "algo@otracosa");
        Article article = Article.named("name", "brand", "model");
        ItemToRepair itemToRepair = ItemToRepair.withoutSecurity(article, "nroserie");
        LocalDateTime creationDate = LocalDateTime.now();
        LocalDateTime beforeCreationDate = LocalDateTime.now().minusDays(1);
        String technician = "technician";
        String notes = "notes";
        String reportedProblem = "reportedProblem";
        BigDecimal cost = BigDecimal.ONE;
        RepairRequestCreationInfo creationInfo = RepairRequestCreationInfo.filledWith(creationDate, technician, notes,
            reportedProblem);

        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> RepairRequest.withDeliveryDateAndCost(customer, itemToRepair, creationInfo, cost, beforeCreationDate));
        assertThat(thrown.getMessage(), is(RepairRequest.DELIVERY_DATE_NOT_BE_BEFORE_THAN_CREATION_DATE));
    }

    @Test
    void whenCreateARepairRequestWithoutDeliveryDateHasTheValuesThatReceived() {
        Customer customer = Customer.named("12345678", "name", "lastname", "123456", "algo@otracosa");
        Article article = Article.named("name", "brand", "model");
        ItemToRepair itemToRepair = ItemToRepair.withoutSecurity(article, "nroserie");
        LocalDateTime creationDate = LocalDateTime.now();
        LocalDateTime deliveryDate = LocalDateTime.now().plusDays(1);
        String technician = "technician";
        String notes = "notes";
        String reportedProblem = "reportedProblem";
        RepairRequestCreationInfo creationInfo = RepairRequestCreationInfo.filledWith(creationDate, technician, notes,
            reportedProblem);

        RepairRequest repairRequest = RepairRequest.noCost(customer, itemToRepair, creationInfo, deliveryDate);

        assertThat(repairRequest.getCustomer(), is(customer));
        assertThat(repairRequest.getItemToRepair(), is(itemToRepair));
        assertThat(repairRequest.getCreationDate(), is(creationDate));
        assertThat(repairRequest.getTechnicianRegistered(), is("technician"));
        assertThat(repairRequest.getTechnicianHasIt(), is("technician"));
        assertThat(repairRequest.getNotes(), is(Optional.of(notes)));
        assertThat(repairRequest.getReportedProblems(), is(Optional.of(reportedProblem)));
        assertThat(repairRequest.getCost(), is(Optional.empty()));
        assertThat(repairRequest.getDeliveryDate(), is(Optional.of(deliveryDate)));
        assertThat(repairRequest.getStatus(), is("CREATED"));
    }
}