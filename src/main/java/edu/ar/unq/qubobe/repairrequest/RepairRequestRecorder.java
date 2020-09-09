package edu.ar.unq.qubobe.repairrequest;

import ar.com.kfgodel.nary.api.optionals.Optional;
import edu.ar.unq.qubobe.customer.model.Customer;
import edu.ar.unq.qubobe.customer.persistence.CustomerAgenda;
import edu.ar.unq.qubobe.itemtorepair.api.ItemToRepairTO;
import edu.ar.unq.qubobe.itemtorepair.model.Article;
import edu.ar.unq.qubobe.itemtorepair.model.ItemSecurity;
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

public class RepairRequestRecorder {
    public static final String NON_EXISTENT_CUSTOMER = "No existe el cliente buscado";
    public static final String NON_EXISTENT_ARTICLE = "No existe el articulo buscado";
    private final RepairRequestRegisters repairRequestRegisters;
    private final ArticleRegisters articleRegisters;
    private final CustomerAgenda customerAgenda;
    private final BudgetRegisters budgetRegisters;

    public RepairRequestRecorder(RepairRequestRegisters repairRequestRegisters, ArticleRegisters articleRegisters,
                                 CustomerAgenda customerAgenda, BudgetRegisters budgetRegisters) {
        this.repairRequestRegisters = repairRequestRegisters;
        this.articleRegisters = articleRegisters;
        this.customerAgenda = customerAgenda;
        this.budgetRegisters = budgetRegisters;
    }

    public RepairRequest record(RepairRequestTO repairRequestTO) {
        ItemToRepair itemToRepair = getItemToRepair(repairRequestTO.getItemToRepair());
        RepairRequestCreationInfo creationInfo = getCreationInfo(repairRequestTO.getCreationInfo());
        Customer customer = getCustomer(repairRequestTO.getCustomer());
        RepairRequest repairRequest = RepairRequest.filledWith(customer, itemToRepair, creationInfo);
        repairRequestRegisters.add(repairRequest);
        return repairRequest;
    }

    public Budget recordBudget(BudgetTO budgetTO) {
        RepairRequest repairRequest = repairRequestRegisters.getById(budgetTO.getRepairRequestId());
        Budget budget = Budget.create(repairRequest, budgetTO.getCost(), budgetTO.getDeliveryDate(),
            budgetTO.getDescription());
        repairRequest.budgeted();
        budgetRegisters.add(budget);
        return budget;
    }

    private ItemToRepair getItemToRepair(ItemToRepairTO itemToRepairTO) {
        Article article = articleRegisters
            .getByName(itemToRepairTO.getArticle())
            .orElseThrow(() -> new RuntimeException(NON_EXISTENT_ARTICLE));
        Optional<ItemSecurity> itemSecurityOptional = Optional.create(itemToRepairTO
            .getItemSecurity()
            .map(itemSecurityTO ->
                ItemSecurity.create(itemSecurityTO.getSecurityType(), itemSecurityTO.getSecurityValue()))
            .findFirst());

        return itemSecurityOptional.isPresent()
            ? ItemToRepair.withSecurity(article, itemToRepairTO.getSerialNumber(), itemSecurityOptional.get())
            : ItemToRepair.withoutSecurity(article, itemToRepairTO.getSerialNumber());
    }

    private RepairRequestCreationInfo getCreationInfo(RepairRequestCreationInfoTO creationInfo) {
        return RepairRequestCreationInfo.filledWith(creationInfo.getCreationDate(), creationInfo.getTechnician(),
            creationInfo.getNotes(), creationInfo.getReportedProblem());
    }

    private Customer getCustomer(String customer) {
        return customerAgenda.getByName(customer).orElseThrow(() -> new RuntimeException(NON_EXISTENT_CUSTOMER));
    }
}
