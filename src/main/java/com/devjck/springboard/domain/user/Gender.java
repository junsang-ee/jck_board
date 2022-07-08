package com.devjck.springboard.domain.user;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum Gender implements CodeValue {
    MALE("M", "MALE"),
    FEMALE("W", "FEMALE");

    private String code;
    private String value;

    Gender(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
