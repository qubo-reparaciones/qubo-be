package edu.ar.unq.qubobe.itemtorepair;

import static edu.ar.unq.qubobe.extensions.ObjectValidations.assertIfNoneOrEmpty;

public class ItemSecurity {
    public static final String TYPE_NOT_BE_EMPTY = "Debe tener un tipo de seguridad";
    public static final String SECURITY_VALUE_NOT_BE_EMPTY = "Debe tener un valor de seguridad";
    private final String type;
    private final String security;

    private ItemSecurity(String type, String security) {
        this.type = type;
        this.security = security;
    }

    public static ItemSecurity create(String type, String security) {
        assertIfNoneOrEmpty(type, TYPE_NOT_BE_EMPTY);
        assertIfNoneOrEmpty(security, SECURITY_VALUE_NOT_BE_EMPTY);
        return new ItemSecurity(type, security);
    }

    public String getType() {
        return type;
    }

    public String getSecurity() {
        return security;
    }
}
