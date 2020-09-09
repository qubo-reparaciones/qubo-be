package edu.ar.unq.qubobe.customer;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerTest {
    @Test
    void whenCreateAValidCustomerHasTheValuesThatReceived() {
        Customer customer = Customer.namedWithEmail("12345678", "name", "lastname", "123456", "algo@otracosa");

        assertThat(customer.getDni(), is("12345678"));
        assertThat(customer.getName(), is("name"));
        assertThat(customer.getLastname(), is("lastname"));
        assertThat(customer.getPhoneNumber(), is("123456"));
        assertThat(customer.getEmail().get(), is("algo@otracosa"));
    }

    @Test
    void canNotCreateCustomerWithEmptyName() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Customer.named("12345678", "", "lastname", "phoneNumber"));
        assertThat(thrown.getMessage(), is(Customer.NAME_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateCustomerWithNoneName() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Customer.named("12345678", null, "lastname", "phoneNumber"));
        assertThat(thrown.getMessage(), is(Customer.NAME_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateCustomerWithEmptyLastname() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Customer.named("12345678", "name", "", "phoneNumber"));
        assertThat(thrown.getMessage(), is(Customer.LASTNAME_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateCustomerWithNoneLastname() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Customer.named("12345678", "name", null, "phoneNumber"), Customer.LASTNAME_CAN_NOT_BE_EMPTY);
        assertThat(thrown.getMessage(), is(Customer.LASTNAME_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateCustomerWithEmptyPhoneNumber() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Customer.named("12345678", "name", "lastname", ""));
        assertThat(thrown.getMessage(), is(Customer.PHONE_NUMBER_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateCustomerWithNonePhoneNumber() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Customer.named("12345678", "name", "lastname", null), Customer.PHONE_NUMBER_CAN_NOT_BE_EMPTY);
        assertThat(thrown.getMessage(), is(Customer.PHONE_NUMBER_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canCreateCustomerWithoutMail() {
        Customer customer = Customer.named("12345678", "name", "lastname", "123456");
        assertThat(customer.hasEmail(), is(false));
    }

    @Test
    void canNotCreateCustomerWithInvalidEmail() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Customer.namedWithEmail("12345678", "name", "lastname", "123456", "invalid"));
        assertThat(thrown.getMessage(), is(Customer.EMAIL_INVALID));
    }

    @Test
    void canCreateCustomerWithValidMail() {
        Customer customer = Customer.namedWithEmail("12345678", "name", "lastname", "123456", "mail@mail");
        assertThat(customer.hasEmail(), is(true));
    }

    @Test
    void canNotCreateCustomerWithEmptyDni() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Customer.named("", "name", "lastname", "phoneNumber"));
        assertThat(thrown.getMessage(), is(Customer.DNI_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateCustomerWithNoneDni() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Customer.named(null, "name", "lastname", "phoneNumber"));
        assertThat(thrown.getMessage(), is(Customer.DNI_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateCustomerWithLeetersInDni() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Customer.named("aaaa1111", "name", "lastname", "phoneNumber"));
        assertThat(thrown.getMessage(), is(Customer.DNI_MUST_BE_NUMERIC_WITH_DIGITS));
    }

    @Test
    void canNotCreateCustomerWithInvalidDniLength() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Customer.named("111", "name", "lastname", "phoneNumber"));
        assertThat(thrown.getMessage(), is(Customer.DNI_MUST_BE_NUMERIC_WITH_DIGITS));
    }

    @Test
    void whenSendAsNamedAsToCustomerWithSameNameReturnsTrue() {
        Customer customer = Customer.namedWithEmail("12345678", "name", "lastname", "123456", "mail@mail");
        assertThat(customer.asNamedAs("name"), is(true));
    }

    @Test
    void whenSendAsNamedAsToCustomerWithDifferentNameReturnsFalse() {
        Customer customer = Customer.namedWithEmail("12345678", "name", "lastname", "123456", "mail@mail");
        assertThat(customer.asNamedAs("different"), is(false));
    }
}