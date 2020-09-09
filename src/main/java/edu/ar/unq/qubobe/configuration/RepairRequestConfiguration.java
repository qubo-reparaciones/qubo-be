package edu.ar.unq.qubobe.configuration;

import edu.ar.unq.qubobe.customer.persistence.CustomerAgenda;
import edu.ar.unq.qubobe.itemtorepair.persistence.ArticleRegisters;
import edu.ar.unq.qubobe.repairrequest.persistence.BudgetRegisters;
import edu.ar.unq.qubobe.repairrequest.RepairRequestRecorder;
import edu.ar.unq.qubobe.repairrequest.persistence.RepairRequestRegisters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepairRequestConfiguration {
    @Bean
    public RepairRequestRecorder repairRequestRecorder(RepairRequestRegisters repairRequestRegisters,
                                                       ArticleRegisters articleRegisters, CustomerAgenda customerAgenda,
                                                       BudgetRegisters budgetRegisters) {
        return new RepairRequestRecorder(repairRequestRegisters, articleRegisters, customerAgenda, budgetRegisters);
    }
}
