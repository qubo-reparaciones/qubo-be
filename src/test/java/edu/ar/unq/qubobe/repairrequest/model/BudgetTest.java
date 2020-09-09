package edu.ar.unq.qubobe.repairrequest.model;

import ar.com.kfgodel.nary.api.optionals.Optional;
import edu.ar.unq.qubobe.customer.model.Customer;
import edu.ar.unq.qubobe.itemtorepair.model.Article;
import edu.ar.unq.qubobe.itemtorepair.model.ItemToRepair;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BudgetTest {
    @Test
    void whenCreateABudgetHasTheValuesThatReceived() {
        LocalDateTime creationDate = LocalDateTime.now();
        RepairRequest repairRequest = getRepairRequest(creationDate);
        LocalDateTime deliveryDate = creationDate.plusDays(1);
        BigDecimal cost = BigDecimal.ONE;
        String description = "description";

        Budget budget = Budget.create(repairRequest, cost, deliveryDate, description);

        assertThat(budget.getRepairRequest(), is(repairRequest));
        assertThat(budget.getCost(), is(Optional.of(cost)));
        assertThat(budget.getDeliveryDate(), is(Optional.of(deliveryDate)));
        assertThat(budget.getDescription(), is(Optional.of(description)));
    }

    @Test
    void whenCreateARepairRequestWithCostLessThanZeroThenThrowAnError() {
        RepairRequest repairRequest = getRepairRequest(LocalDateTime.now());
        BigDecimal cost = BigDecimal.ONE.negate();
        LocalDateTime deliveryDate = LocalDateTime.now().plusDays(1);
        String description = "description";

        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Budget.create(repairRequest, cost, deliveryDate, description));
        assertThat(thrown.getMessage(), is(Budget.COST_NOT_BE_LESS_THAN_ZERO));
    }

    @Test
    void canNotCreateBudgetWithNoneRepairRequest() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Budget.create(null, null, null, null));
        assertThat(thrown.getMessage(), is(Budget.REPAIR_REQUEST_NOT_BE_EMPTY));
    }

    @Test
    void whenCreateARepairRequestWithDeliveryDateBeforeCreationDateThenThrowAnError() {
        LocalDateTime creationDate = LocalDateTime.now();
        RepairRequest repairRequest = getRepairRequest(creationDate);
        BigDecimal cost = BigDecimal.ONE;
        LocalDateTime beforeDeliveryDate = creationDate.minusDays(1);
        String description = "description";

        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Budget.create(repairRequest, cost, beforeDeliveryDate, description));
        assertThat(thrown.getMessage(), is(Budget.DELIVERY_DATE_NOT_BE_BEFORE_THAN_CREATION_DATE));
    }

    private RepairRequest getRepairRequest(LocalDateTime creationDate) {
        Customer customer = Customer.named("12345678", "name", "lastname", "123456");
        Article article = Article.named("name", "brand", "model");
        ItemToRepair itemToRepair = ItemToRepair.withoutSecurity(article, "nroserie");
        String technician = "technician";
        String notes = "notes";
        String reportedProblem = "reportedProblem";
        RepairRequestCreationInfo creationInfo = RepairRequestCreationInfo.filledWith(creationDate, technician, notes,
            reportedProblem);
        return RepairRequest.filledWith(customer, itemToRepair, creationInfo);
    }
}