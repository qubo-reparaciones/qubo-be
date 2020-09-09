package edu.ar.unq.qubobe.customer.persistence;

import ar.com.kfgodel.nary.api.optionals.Optional;
import edu.ar.unq.qubobe.customer.Customer;

import java.util.List;

public interface CustomerAgenda {
    boolean hasCustomerIdentifiedAs(String dni);

    void add(Customer customer);

    List<Customer> getAll();

    Optional<Customer> getByName(String customer);
}
