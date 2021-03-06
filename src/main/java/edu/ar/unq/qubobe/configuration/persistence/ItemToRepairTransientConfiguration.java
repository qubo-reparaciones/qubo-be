package edu.ar.unq.qubobe.configuration.persistence;

import edu.ar.unq.qubobe.itemtorepair.persistence.ArticleRegisters;
import edu.ar.unq.qubobe.itemtorepair.persistence.ArticleRegistersTransient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemToRepairTransientConfiguration {
    @Bean
    public ArticleRegisters articleRegisters() {
        return new ArticleRegistersTransient();
    }
}