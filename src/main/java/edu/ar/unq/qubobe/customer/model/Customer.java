package edu.ar.unq.qubobe.customer;

import ar.com.kfgodel.nary.api.optionals.Optional;

import static edu.ar.unq.qubobe.extensions.ObjectValidations.assertIfNoneOrEmpty;
import static edu.ar.unq.qubobe.extensions.ObjectValidations.assertValidDni;
import static edu.ar.unq.qubobe.extensions.ObjectValidations.assertValidEmail;

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

    public static Customer named(String dni, String name, String lastname, String phoneNumber) {
        assertIfNoneOrEmpty(dni, DNI_CAN_NOT_BE_EMPTY);
        assertValidDni(dni);
        assertIfNoneOrEmpty(name, NAME_CAN_NOT_BE_EMPTY);
        assertIfNoneOrEmpty(lastname, LASTNAME_CAN_NOT_BE_EMPTY);
        assertIfNoneOrEmpty(phoneNumber, PHONE_NUMBER_CAN_NOT_BE_EMPTY);
        return new Customer(dni, name, lastname, phoneNumber, Optional.empty());
    }

    public static Customer namedWithEmail(String dni, String name, String lastname, String phoneNumber, String email) {
        assertIfNoneOrEmpty(dni, DNI_CAN_NOT_BE_EMPTY);
        assertValidDni(dni);
        assertIfNoneOrEmpty(name, NAME_CAN_NOT_BE_EMPTY);
        assertIfNoneOrEmpty(lastname, LASTNAME_CAN_NOT_BE_EMPTY);
        assertIfNoneOrEmpty(phoneNumber, PHONE_NUMBER_CAN_NOT_BE_EMPTY);
        assertValidEmail(email);
        return new Customer(dni, name, lastname, phoneNumber, Optional.ofNullable(email));
    }

    public Optional<String> getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Boolean hasEmail() {
        return !email.isAbsent();
    }

    public String getDni() {
        return dni;
    }

    public boolean isIdentifiedAs(String dni) {
        return this.dni.equals(dni);
    }

    @Override
    public String toString() {
        return "DNI: " + dni + ".\n" +
            "Nombre: " + name + ".\n" +
            "Apellido: " + lastname + ".\n" +
            "Telefono: " + phoneNumber + ".\n" +
            "Email: " + email.orElse("no tiene.");
    }

    public boolean asNamedAs(String name) {
        return this.name.equals(name);
    }
}
