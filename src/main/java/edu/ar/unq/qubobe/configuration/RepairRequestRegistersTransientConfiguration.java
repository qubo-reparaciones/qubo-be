package edu.ar.unq.qubobe.configuration;

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
}
