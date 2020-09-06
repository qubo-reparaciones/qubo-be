package edu.ar.unq.qubobe.extensions;

public class StringExtension {
    public static Boolean isNumber(String toEvaluate) {
        try {
            Long.parseLong(toEvaluate); // Or the number type you need
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
