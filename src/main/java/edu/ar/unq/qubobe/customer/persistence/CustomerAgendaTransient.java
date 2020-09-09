package edu.ar.unq.qubobe.customer.persistence;

import ar.com.kfgodel.nary.api.optionals.Optional;
import edu.ar.unq.qubobe.customer.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerAgendaTransient implements CustomerAgenda {
    private final List<Customer> customers;

    public CustomerAgendaTransient() {
        this.customers = new ArrayList<>();
    }

    @Override
    public boolean hasCustomerIdentifiedAs(String dni) {
        return this.customers.stream().anyMatch(customer -> customer.isIdentifiedAs(dni));
    }

    @Override
    public void add(Customer customer) {
        this.customers.add(customer);
    }

    @Override
    public List<Customer> getAll() {
        return this.customers;
    }

    @Override
    public Optional<Customer> getByName(String name) {
        return Optional.create(this.customers.stream().filter(customer -> customer.asNamedAs(name)).findFirst());
    }

    @Override
    public Optional<Customer> getByDni(String dni) {
        return Optional.create(this.customers.stream().filter(customer -> customer.isIdentifiedAs(dni)).findFirst());
    }
}
