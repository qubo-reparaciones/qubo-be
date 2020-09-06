package edu.ar.unq.qubobe.customer;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerTest {
    @Test
    void whenCreateAValidCustomerHasTheValuesThatReceived() {
        Customer customer = Customer.named("12345678", "name", "lastname", "123456", "algo@otracosa");
        assertThat(customer.getDni(), is("12345678"));
        assertThat(customer.getName(), is("name"));
        assertThat(customer.getLastname(), is("lastname"));
        assertThat(customer.getPhoneNumber(), is("123456"));
        assertThat(customer.getEmail().get(), is("algo@otracosa"));
    }

    @Test
    void canNotCreateCustomerWithEmptyName() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Customer.named("12345678", "", "lastname", "phoneNumber", "mail"));
        assertThat(thrown.getMessage(), is(Customer.NAME_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateCustomerWithNoneName() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Customer.named("12345678", null, "lastname", "phoneNumber", "mail"));
        assertThat(thrown.getMessage(), is(Customer.NAME_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateCustomerWithEmptyLastname() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Customer.named("12345678", "name", "", "phoneNumber", "mail"));
        assertThat(thrown.getMessage(), is(Customer.LASTNAME_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateCustomerWithNoneLastname() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Customer.named("12345678", "name", null, "phoneNumber", "mail"), Customer.LASTNAME_CAN_NOT_BE_EMPTY);
        assertThat(thrown.getMessage(), is(Customer.LASTNAME_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateCustomerWithEmptyPhoneNumber() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Customer.named("12345678", "name", "lastname", "", "mail"));
        assertThat(thrown.getMessage(), is(Customer.PHONE_NUMBER_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateCustomerWithNonePhoneNumber() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Customer.named("12345678", "name", "lastname", null, "mail"), Customer.PHONE_NUMBER_CAN_NOT_BE_EMPTY);
        assertThat(thrown.getMessage(), is(Customer.PHONE_NUMBER_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canCreateCustomerWithoutMail() {
        Customer customer = Customer.named("12345678", "name", "lastname", "123456", null);
        assertThat(customer.hasEmail(), is(false));
    }

    @Test
    void canNotCreateCustomerWithInvalidEmail() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Customer.named("12345678", "name", "lastname", "123456", ""));
        assertThat(thrown.getMessage(), is(Customer.EMAIL_INVALID));
    }

    @Test
    void canCreateCustomerWithValidMail() {
        Customer customer = Customer.named("12345678", "name", "lastname", "123456", "algo@otracosa");
        assertThat(customer.hasEmail(), is(true));
    }

    @Test
    void canNotCreateCustomerWithEmptyDni() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Customer.named("", "name", "lastname", "phoneNumber", "algo@otracosa"));
        assertThat(thrown.getMessage(), is(Customer.DNI_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateCustomerWithNoneDni() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Customer.named(null, "name", "lastname", "phoneNumber", "algo@otracosa"));
        assertThat(thrown.getMessage(), is(Customer.DNI_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateCustomerWithLeetersInDni() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Customer.named("aaaa1111", "name", "lastname", "phoneNumber", "algo@otracosa"));
        assertThat(thrown.getMessage(), is(Customer.DNI_MUST_BE_NUMERIC_WITH_DIGITS));
    }

    @Test
    void canNotCreateCustomerWithInvalidDniLength() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Customer.named("111", "name", "lastname", "phoneNumber", "algo@otracosa"));
        assertThat(thrown.getMessage(), is(Customer.DNI_MUST_BE_NUMERIC_WITH_DIGITS));
    }
}