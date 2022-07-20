package com.devjck.springboard.domain.user.enumType;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum Status implements EnumValueModel {
    NORMAL("NORMAL"),
    DORMANCY("DORMANCY"),
    SECESSION("SECESSION");

    private String status;

    Status(String status) {
        this.status = status;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return status;
    }
}
