package edu.ar.unq.qubobe.customer.api.to;

import edu.ar.unq.qubobe.customer.Customer;

public class CustomerTO {
    private String dni;
    private String name;
    private String lastname;
    private String phoneNumber;
    private String email;

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Customer get() {
        return Customer.named(dni, name, lastname, phoneNumber, email);
    }
}
