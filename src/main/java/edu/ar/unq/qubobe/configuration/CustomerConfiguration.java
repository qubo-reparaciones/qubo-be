package edu.ar.unq.qubobe.configuration;

import edu.ar.unq.qubobe.customer.persistence.CustomerAgenda;
import edu.ar.unq.qubobe.customer.CustomerRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfiguration {
    @Bean
    public CustomerRegistrar customerRegistrar(CustomerAgenda customerAgenda) {
        return new CustomerRegistrar(customerAgenda);
    }
}
