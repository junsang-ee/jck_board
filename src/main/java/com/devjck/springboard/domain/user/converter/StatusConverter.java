package com.devjck.springboard.domain.user.converter;

import com.devjck.springboard.domain.user.enumType.Status;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status attribute) {
        return attribute.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(Status.class).stream()
                .filter(e->e.getValue().equals(dbData))
                .findAny()
                .orElseThrow(()-> new NoSuchElementException());
    }
}
