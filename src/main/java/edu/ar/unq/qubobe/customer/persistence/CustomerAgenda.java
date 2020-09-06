package edu.ar.unq.qubobe.customer.persistence;

import edu.ar.unq.qubobe.customer.Customer;

import java.util.List;

public interface CustomerAgenda {
    boolean hasCustomerIdentifiedAs(String dni);

    void add(Customer customer);

    List<Customer> getAll();
}
