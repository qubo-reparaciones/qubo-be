package edu.ar.unq.qubobe.itemtorepair.api;

public class ItemSecurityTO {
    private String securityType;
    private String securityValue;

    public ItemSecurityTO(String securityType, String securityValue) {
        this.securityType = securityType;
        this.securityValue = securityValue;
    }

    public String getSecurityType() {
        return securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    public String getSecurityValue() {
        return securityValue;
    }

    public void setSecurityValue(String securityValue) {
        this.securityValue = securityValue;
    }
}