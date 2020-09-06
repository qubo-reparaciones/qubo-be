package edu.ar.unq.qubobe.customer;

import ar.com.kfgodel.nary.api.optionals.Optional;

public class Customer {
    public static final String NAME_CAN_NOT_BE_EMPTY = "El nombre no puede ser nulo o vacío";
    public static final String LASTNAME_CAN_NOT_BE_EMPTY = "El apellido no puede ser nulo o vacío";
    public static final String PHONE_NUMBER_CAN_NOT_BE_EMPTY = "El teléfono no puede ser nulo o vacío";
    public static final String EMAIL_INVALID = "El email no tiene el formato válido";
    private final Optional<String> email;
    private final String name;
    private final String lastname;
    private final String phoneNumber;

    private Customer(String name, String lastname, String phoneNumber, Optional<String> email) {
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
    }

    public static Customer named(String name, String lastname, String phoneNumber, String email) {
        assertIfEmpty(name, NAME_CAN_NOT_BE_EMPTY);
        assertIfEmpty(lastname, LASTNAME_CAN_NOT_BE_EMPTY);
        assertIfEmpty(phoneNumber, PHONE_NUMBER_CAN_NOT_BE_EMPTY);
        assertValidEmail(email);
        return new Customer(name, lastname, phoneNumber, Optional.ofNullable(email));
    }

    private static void assertValidEmail(String email) {
        if (email != null && !email.contains("@")) {
            throw new RuntimeException(EMAIL_INVALID);
        }
    }

    private static void assertIfEmpty(String value, String ifNone) {
        if (value == null || value.isEmpty()) throw new RuntimeException(ifNone);
    }

    public Boolean hasEmail() {
        return !email.isAbsent();
    }
}
