package edu.ar.unq.qubobe.customer;

import ar.com.kfgodel.nary.api.optionals.Optional;

import static edu.ar.unq.qubobe.extensions.StringExtension.isNumber;

public class Customer {
    public static final int DNI_DIGITS = 8;
    public static final String NAME_CAN_NOT_BE_EMPTY = "El nombre no puede ser nulo o vacío";
    public static final String LASTNAME_CAN_NOT_BE_EMPTY = "El apellido no puede ser nulo o vacío";
    public static final String PHONE_NUMBER_CAN_NOT_BE_EMPTY = "El teléfono no puede ser nulo o vacío";
    public static final String EMAIL_INVALID = "El email no tiene el formato válido";
    public static final String DNI_CAN_NOT_BE_EMPTY = "El DNI no puede ser nulo o vacío";
    public static final String DNI_MUST_BE_NUMERIC_WITH_DIGITS = "El DNI tiene que ser numérico con " + DNI_DIGITS + " dígitos";
    private final Optional<String> email;
    private final String name;
    private final String lastname;
    private final String phoneNumber;
    private final String dni;

    private Customer(String dni, String name, String lastname, String phoneNumber, Optional<String> email) {
        this.dni = dni;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
    }

    public static Customer named(String dni, String name, String lastname, String phoneNumber, String email) {
        assertIfEmpty(dni, DNI_CAN_NOT_BE_EMPTY);
        assertValidDni(dni);
        assertIfEmpty(name, NAME_CAN_NOT_BE_EMPTY);
        assertIfEmpty(lastname, LASTNAME_CAN_NOT_BE_EMPTY);
        assertIfEmpty(phoneNumber, PHONE_NUMBER_CAN_NOT_BE_EMPTY);
        assertValidEmail(email);
        return new Customer(dni, name, lastname, phoneNumber, Optional.ofNullable(email));
    }

    private static void assertValidDni(String dni) {
        if (dni != null && (!isNumber(dni) || DNI_DIGITS != dni.length())) {
            throw new RuntimeException(DNI_MUST_BE_NUMERIC_WITH_DIGITS);
        }
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

    @Override
    public String toString() {
        return "DNI: " + dni + ".\n" +
            "Nombre: " + name + ".\n" +
            "Apellido: " + lastname + ".\n" +
            "Telefono: " + phoneNumber + ".\n" +
            "Email: " + email.orElse("no tiene.");
    }
}
