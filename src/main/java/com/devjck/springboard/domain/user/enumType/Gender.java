package com.devjck.springboard.domain.user.enumType;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum Gender implements EnumValueModel {
    M("MALE"),
    W("FEMALE");

    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return gender;
    }
}
