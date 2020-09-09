package edu.ar.unq.qubobe.configuration.persistence;

import edu.ar.unq.qubobe.customer.persistence.CustomerAgenda;
import edu.ar.unq.qubobe.customer.persistence.CustomerAgendaTransient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerTransientConfiguration {
    @Bean
    public CustomerAgenda customerAgendaTransient() {
        return new CustomerAgendaTransient();
    }
}
