package edu.ar.unq.qubobe.customer;

import edu.ar.unq.qubobe.customer.model.Customer;
import edu.ar.unq.qubobe.customer.persistence.CustomerAgendaTransient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerRegistrarTest {
    private CustomerRegistrar customerRegistrar;
    private CustomerAgendaTransient customerAgenda;

    @BeforeEach
    void init() {
        customerAgenda = new CustomerAgendaTransient();
        customerRegistrar = new CustomerRegistrar(customerAgenda);
    }

    @Test
    void canRegisterACustomer() {
        Customer toRegister = Customer.named("12345678", "name", "lastname", "123456");

        Customer register = customerRegistrar.register(toRegister);

        assertThat(register.getDni(), is("12345678"));
        assertThat(register.getName(), is("name"));
        assertThat(register.getLastname(), is("lastname"));
        assertThat(register.getPhoneNumber(), is("123456"));
        assertThat(customerAgenda.getAll().contains(toRegister), is(true));
        assertThat(customerAgenda.getAll().size(), is(1));
    }

    @Test
    void canNotRegisterACustomerWithExistentDni() {
        Customer registeredCustomer = Customer.named("12345678", "name", "lastname", "123456");
        customerRegistrar.register(registeredCustomer);
        Customer repeteadCustomer = Customer.named("12345678", "otherName", "otherLastname", "1234567");

        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> customerRegistrar.register(repeteadCustomer));
        assertThat(thrown.getMessage(), is(CustomerRegistrar.DNI_DUPLICATED));
        assertThat(customerAgenda.getAll().size(), is(1));
    }
}