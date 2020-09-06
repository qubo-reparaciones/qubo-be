package edu.ar.unq.qubobe.customer.persistence;

import edu.ar.unq.qubobe.customer.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerAgendaTransient implements CustomerAgenda {
    private List<Customer> customers;

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
}
