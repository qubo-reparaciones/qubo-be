package edu.ar.unq.qubobe.extensions;

import edu.ar.unq.qubobe.customer.Customer;

import static edu.ar.unq.qubobe.extensions.StringExtension.isNumber;

public class ObjectValidations {
    public static void assertIfNoneOrEmpty(String value, String ifNone) {
        if (value == null || value.isEmpty()) throw new RuntimeException(ifNone);
    }

    public static void assertIfNone(Object value, String ifNone) {
        if (value == null) throw new RuntimeException(ifNone);
    }

    public static void assertValidDni(String dni) {
        if (dni != null && (!isNumber(dni) || Customer.DNI_DIGITS != dni.length())) {
            throw new RuntimeException(Customer.DNI_MUST_BE_NUMERIC_WITH_DIGITS);
        }
    }

    public static void assertValidEmail(String email) {
        if (email != null && !email.contains("@")) {
            throw new RuntimeException(Customer.EMAIL_INVALID);
        }
    }
}
