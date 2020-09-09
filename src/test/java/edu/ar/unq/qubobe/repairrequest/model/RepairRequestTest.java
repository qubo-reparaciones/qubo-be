package edu.ar.unq.qubobe.repairrequest.model;

import ar.com.kfgodel.nary.api.optionals.Optional;
import edu.ar.unq.qubobe.customer.model.Customer;
import edu.ar.unq.qubobe.itemtorepair.model.Article;
import edu.ar.unq.qubobe.itemtorepair.model.ItemToRepair;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RepairRequestTest {
    @Test
    void whenCreateARepairRequestNoDeliveryDateOrCostHasTheValuesThatReceived() {
        Customer customer = Customer.named("12345678", "name", "lastname", "123456");
        Article article = Article.named("name", "brand", "model");
        ItemToRepair itemToRepair = ItemToRepair.withoutSecurity(article, "nroserie");
        LocalDateTime creationDate = LocalDateTime.now();
        String technician = "technician";
        String notes = "notes";
        String reportedProblem = "reportedProblem";
        RepairRequestCreationInfo creationInfo = RepairRequestCreationInfo.filledWith(creationDate, technician, notes,
            reportedProblem);

        RepairRequest repairRequest = RepairRequest.filledWith(customer, itemToRepair, creationInfo);

        assertThat(repairRequest.getCustomer(), is(customer));
        assertThat(repairRequest.getItemToRepair(), is(itemToRepair));
        assertThat(repairRequest.getCreationDate(), is(creationDate));
        assertThat(repairRequest.getTechnicianRegistered(), is("technician"));
        assertThat(repairRequest.getTechnicianHasIt(), is("technician"));
        assertThat(repairRequest.getNotes(), is(Optional.of(notes)));
        assertThat(repairRequest.getReportedProblems(), is(Optional.of(reportedProblem)));
        assertThat(repairRequest.getStatus(), is("CREATED"));
    }
}