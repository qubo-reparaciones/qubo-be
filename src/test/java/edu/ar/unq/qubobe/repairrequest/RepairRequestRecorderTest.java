package edu.ar.unq.qubobe.repairrequest;

import ar.com.kfgodel.nary.api.optionals.Optional;
import edu.ar.unq.qubobe.customer.model.Customer;
import edu.ar.unq.qubobe.customer.persistence.CustomerAgenda;
import edu.ar.unq.qubobe.itemtorepair.api.ItemSecurityTO;
import edu.ar.unq.qubobe.itemtorepair.api.ItemToRepairTO;
import edu.ar.unq.qubobe.itemtorepair.model.Article;
import edu.ar.unq.qubobe.itemtorepair.model.ItemToRepair;
import edu.ar.unq.qubobe.itemtorepair.persistence.ArticleRegisters;
import edu.ar.unq.qubobe.repairrequest.api.to.BudgetTO;
import edu.ar.unq.qubobe.repairrequest.api.to.RepairRequestCreationInfoTO;
import edu.ar.unq.qubobe.repairrequest.api.to.RepairRequestTO;
import edu.ar.unq.qubobe.repairrequest.model.Budget;
import edu.ar.unq.qubobe.repairrequest.model.RepairRequest;
import edu.ar.unq.qubobe.repairrequest.model.RepairRequestCreationInfo;
import edu.ar.unq.qubobe.repairrequest.persistence.BudgetRegisters;
import edu.ar.unq.qubobe.repairrequest.persistence.RepairRequestRegisters;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RepairRequestRecorderTest {
    private RepairRequestRecorder recorder;

    @Mock
    private RepairRequestRegisters repairRequestRegisters;
    @Mock
    private ArticleRegisters articleRegisters;
    @Mock
    private CustomerAgenda customerAgenda;
    @Mock
    private BudgetRegisters budgetRegisters;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        recorder = new RepairRequestRecorder(repairRequestRegisters, articleRegisters, customerAgenda, budgetRegisters);
    }

    @Test
    void whenRecordAnRepairRequestWithSecuritySaveTheRepairRequestWithTheRightValues() {
        String customer = "name";
        String article = "article";
        String serialNumber = "serialNumber";
        String securityType = "type";
        String securityValue = "value";
        LocalDateTime creationDate = LocalDateTime.now();
        String technician = "pepe";
        String notes = "notes";
        String problems = "problems";
        ItemSecurityTO itemSecurityTO = new ItemSecurityTO(securityType, securityValue);
        ItemToRepairTO itemToRepairTO = new ItemToRepairTO(article, serialNumber, itemSecurityTO);
        RepairRequestCreationInfoTO creationInfo = new RepairRequestCreationInfoTO(creationDate, technician, notes,
            problems);
        RepairRequestTO repairRequestTO = new RepairRequestTO(customer, itemToRepairTO, creationInfo);
        when(customerAgenda.getByName(customer))
            .thenReturn(Optional.of(Customer.named("12345678", "name", "lastname", "phone")));
        when(articleRegisters.getByName(article))
            .thenReturn(Optional.of(Article.named(article, "brand", "model")));

        RepairRequest repairRequest = recorder.record(repairRequestTO);

        assertOnRepairRequest(customer, article, serialNumber, creationDate, technician, notes, problems,
            repairRequest, "CREATED");
        assertThat(repairRequest.getItemToRepair().getSecurity().get().getType(), is(securityType));
        assertThat(repairRequest.getItemToRepair().getSecurity().get().getSecurity(), is(securityValue));
        verify(repairRequestRegisters, times(1)).add(any());
    }

    @Test
    void whenRecordAnRepairRequestWithoutSecuritySaveTheRepairRequestWithTheRightValuesAndNoSecurity() {
        String customer = "name";
        String article = "article";
        String serialNumber = "serialNumber";
        LocalDateTime creationDate = LocalDateTime.now();
        String technician = "pepe";
        String notes = "notes";
        String problems = "problems";
        ItemToRepairTO itemToRepairTO = new ItemToRepairTO(article, serialNumber, null);
        RepairRequestCreationInfoTO creationInfo = new RepairRequestCreationInfoTO(creationDate, technician, notes,
            problems);
        RepairRequestTO repairRequestTO = new RepairRequestTO(customer, itemToRepairTO, creationInfo);
        when(customerAgenda.getByName(customer))
            .thenReturn(Optional.of(Customer.named("12345678", "name", "lastname", "phone")));
        when(articleRegisters.getByName(article))
            .thenReturn(Optional.of(Article.named(article, "brand", "model")));

        RepairRequest repairRequest = recorder.record(repairRequestTO);

        assertOnRepairRequest(customer, article, serialNumber, creationDate, technician, notes, problems,
            repairRequest, "CREATED");
        assertThat(repairRequest.getItemToRepair().getSecurity(), is(Optional.empty()));
        verify(repairRequestRegisters, times(1)).add(any());
    }

    @Test
    void whenWantRecordARepairRequestWithUnknownCustomerReturnsAnErrorAndNotSaveTheRepairRequest() {
        String customer = "name";
        String article = "article";
        String serialNumber = "serialNumber";
        LocalDateTime creationDate = LocalDateTime.now();
        String technician = "pepe";
        String notes = "notes";
        String problems = "problems";
        ItemToRepairTO itemToRepairTO = new ItemToRepairTO(article, serialNumber, null);
        RepairRequestCreationInfoTO creationInfo = new RepairRequestCreationInfoTO(creationDate, technician,
            notes, problems);
        RepairRequestTO repairRequestTO = new RepairRequestTO(customer, itemToRepairTO, creationInfo);
        when(customerAgenda.getByName(customer))
            .thenReturn(Optional.empty());
        when(articleRegisters.getByName(article))
            .thenReturn(Optional.of(Article.named(article, "brand", "model")));

        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> recorder.record(repairRequestTO));
        assertThat(thrown.getMessage(), Matchers.is(RepairRequestRecorder.NON_EXISTENT_CUSTOMER));
        verify(repairRequestRegisters, never()).add(any());
    }

    @Test
    void whenRecordABudgetForARepairRequestThisChangeHisStatusForBudget() {
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
        String repairRequestId = "id";
        BigDecimal cost = BigDecimal.ONE;
        LocalDateTime deliveryDate = LocalDateTime.now();
        String description = "";
        BudgetTO budgetTO = new BudgetTO(repairRequestId, cost, deliveryDate, description);
        when(repairRequestRegisters.getById(repairRequestId)).thenReturn(repairRequest);

        Budget budget = recorder.recordBudget(budgetTO);

        assertOnRepairRequest(customer.getName(), article.getName(), itemToRepair.getSerialNumber(), creationDate,
            technician, notes, creationInfo.getReportedProblem().get(), repairRequest, "BUDGETED");
        verify(budgetRegisters, times(1)).add(budget);
    }

    @Test
    void whenWantRecordARepairRequestWithUnknownArticleReturnsAnErrorAndNotSaveTheRepairRequest() {
        String customer = "name";
        String article = "article";
        String serialNumber = "serialNumber";
        LocalDateTime creationDate = LocalDateTime.now();
        String technician = "pepe";
        String notes = "notes";
        String problems = "problems";
        ItemToRepairTO itemToRepairTO = new ItemToRepairTO(article, serialNumber, null);
        RepairRequestCreationInfoTO creationInfo = new RepairRequestCreationInfoTO(creationDate, technician,
            notes, problems);
        RepairRequestTO repairRequestTO = new RepairRequestTO(customer, itemToRepairTO, creationInfo);
        when(customerAgenda.getByName(customer)).thenReturn(Optional.empty());
        when(articleRegisters.getByName(article)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> recorder.record(repairRequestTO));
        assertThat(thrown.getMessage(), Matchers.is(RepairRequestRecorder.NON_EXISTENT_ARTICLE));
        verify(repairRequestRegisters, never()).add(any());
    }

    private void assertOnRepairRequest(String customer, String article, String serialNumber, LocalDateTime creationDate,
                                       String technician, String notes, String problems, RepairRequest repairRequest,
                                       String repairRequestStatus) {
        assertThat(repairRequest.getCustomer().getName(), is(customer));
        assertThat(repairRequest.getCustomer().getDni(), is("12345678"));
        assertThat(repairRequest.getItemToRepair().getArticle().getName(), is(article));
        assertThat(repairRequest.getItemToRepair().getSerialNumber(), is(serialNumber));
        assertThat(repairRequest.getCreationDate(), is(creationDate));
        assertThat(repairRequest.getTechnicianRegistered(), is(technician));
        assertThat(repairRequest.getTechnicianHasIt(), is(technician));
        assertThat(repairRequest.getNotes(), is(Optional.of(notes)));
        assertThat(repairRequest.getReportedProblems(), is(Optional.of(problems)));
        assertThat(repairRequest.getStatus(), is(repairRequestStatus));
    }
}