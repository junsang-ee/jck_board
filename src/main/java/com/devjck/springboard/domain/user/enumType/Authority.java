package com.devjck.springboard.domain.user.enumType;

public enum Authority implements EnumValueModel {
    ADMIN("ROLE_ADMIN"),
    MANAGER("ROLE_MANAGER"),
    USER("ROLE_USER"),
    ROOKIE("ROLE_ROOKIE");


    private String role;

    Authority(String role) {
        this.role = role;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return role;
    }
}
