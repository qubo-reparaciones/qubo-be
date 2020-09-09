package edu.ar.unq.qubobe.configuration.persistence;

import edu.ar.unq.qubobe.repairrequest.persistence.BudgetRegisters;
import edu.ar.unq.qubobe.repairrequest.persistence.BudgetRegistersTransient;
import edu.ar.unq.qubobe.repairrequest.persistence.RepairRequestRegisters;
import edu.ar.unq.qubobe.repairrequest.persistence.RepairRequestRegistersTransient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepairRequestRegistersTransientConfiguration {
    @Bean
    public RepairRequestRegisters repairRequestRegisters() {
        return new RepairRequestRegistersTransient();
    }

    @Bean
    public BudgetRegisters budgetRegisters() {
        return new BudgetRegistersTransient();
    }
}
