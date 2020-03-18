package com.poseiden.domain.role;

public enum Role {
    Employee("Employee");
    private String value;

    public String value() {
        return this.value;
    }

    Role(String value) {
        this.value = value;
    }
}
