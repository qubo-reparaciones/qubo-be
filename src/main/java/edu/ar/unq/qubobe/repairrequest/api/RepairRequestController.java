package edu.ar.unq.qubobe.repairrequest.api;

import edu.ar.unq.qubobe.repairrequest.RepairRequestRecorder;
import edu.ar.unq.qubobe.repairrequest.api.to.BudgetTO;
import edu.ar.unq.qubobe.repairrequest.api.to.RepairRequestTO;
import edu.ar.unq.qubobe.repairrequest.model.Budget;
import edu.ar.unq.qubobe.repairrequest.model.RepairRequest;
import edu.ar.unq.qubobe.repairrequest.persistence.BudgetRegisters;
import edu.ar.unq.qubobe.repairrequest.persistence.RepairRequestRegisters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = RepairRequestController.basePath, consumes = MediaType.APPLICATION_JSON_VALUE)
public class RepairRequestController {
    public static final String basePath = "/repair-request";
    public static Logger logger = LoggerFactory.getLogger(RepairRequestController.class);
    private final RepairRequestRecorder repairRequestRecorder;
    private final RepairRequestRegisters repairRequestRegisters;
    private final BudgetRegisters budgetRegisters;

    @Autowired
    public RepairRequestController(RepairRequestRecorder repairRequestRecorder,
                                   RepairRequestRegisters repairRequestRegisters, BudgetRegisters budgetRegisters) {
        this.repairRequestRecorder = repairRequestRecorder;
        this.repairRequestRegisters = repairRequestRegisters;
        this.budgetRegisters = budgetRegisters;
    }

    @PostMapping
    public RepairRequest register(@RequestBody RepairRequestTO repairRequestTO) {
        RepairRequest repairRequest = repairRequestRecorder.record(repairRequestTO);
        logger.info("Pedido de reparación creado: " + repairRequest.toString());
        return repairRequest;
    }

    @PostMapping("budget")
    public Budget createBudget(@RequestBody BudgetTO budgetTO) {
        Budget budget = repairRequestRecorder.recordBudget(budgetTO);
        logger.info("Presupuesto generado: " + budget.toString());
        return budget;
    }

    @GetMapping
    public List<RepairRequest> allRepairRequest() {
        return repairRequestRegisters.getAll();
    }

    @GetMapping("budgets")
    public List<Budget> allBudget() {
        return budgetRegisters.getAll();
    }
}