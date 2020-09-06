package edu.ar.unq.qubobe.customer;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerTest {
    @Test
    void canNotCreateCustomerWithEmptyName() {
        assertThrows(RuntimeException.class,
            () -> Customer.named("", "lastname", "phoneNumber", "mail"), Customer.NAME_CAN_NOT_BE_EMPTY);
    }

    @Test
    void canNotCreateCustomerWithNoneName() {
        assertThrows(RuntimeException.class,
            () -> Customer.named(null, "lastname", "phoneNumber", "mail"), Customer.NAME_CAN_NOT_BE_EMPTY);
    }

    @Test
    void canNotCreateCustomerWithEmptyLastname() {
        assertThrows(RuntimeException.class,
            () -> Customer.named("name", "", "phoneNumber", "mail"), Customer.LASTNAME_CAN_NOT_BE_EMPTY);
    }

    @Test
    void canNotCreateCustomerWithNoneLastname() {
        assertThrows(RuntimeException.class,
            () -> Customer.named("name", null, "phoneNumber", "mail"), Customer.LASTNAME_CAN_NOT_BE_EMPTY);
    }

    @Test
    void canNotCreateCustomerWithEmptyPhoneNumber() {
        assertThrows(RuntimeException.class,
            () -> Customer.named("name", "lastname", "", "mail"), Customer.PHONE_NUMBER_CAN_NOT_BE_EMPTY);
    }

    @Test
    void canNotCreateCustomerWithNonePhoneNumber() {
        assertThrows(RuntimeException.class,
            () -> Customer.named("name", "lastname", null, "mail"), Customer.PHONE_NUMBER_CAN_NOT_BE_EMPTY);
    }

    @Test
    void canCreateCustomerWithoutMail() {
        Customer customer = Customer.named("name", "lastname", "123456", null);
        assertThat(customer.hasEmail(), is(false));
    }

    @Test
    void canNotCreateCustomerWithInvalidEmail() {
        assertThrows(RuntimeException.class,
            () -> Customer.named("name", "lastname", "123456", ""), Customer.EMAIL_INVALID);
    }

    @Test
    void canCreateCustomerWithValidMail() {
        Customer customer = Customer.named("name", "lastname", "123456", "algo@otracosa");
        assertThat(customer.hasEmail(), is(true));
    }
}