package edu.ar.unq.qubobe.customer;

import edu.ar.unq.qubobe.customer.persistence.CustomerAgenda;

import java.util.List;

public class CustomerRegistrar {
    public static final String DNI_DUPLICATED = "Existe otro cliente con el mismo DNI.";

    private final CustomerAgenda customerAgenda;

    public CustomerRegistrar(CustomerAgenda customerAgenda) {
        this.customerAgenda = customerAgenda;
    }

    public void register(Customer customer) {
        if (this.customerAgenda.hasCustomerIdentifiedAs(customer.getDni())) {
            throw new RuntimeException(DNI_DUPLICATED);
        }
        this.customerAgenda.add(customer);
    }

    public List<Customer> getAll() {
        return this.customerAgenda.getAll();
    }
}
