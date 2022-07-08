package com.devjck.springboard.domain.user;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class UserGenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender attribute) {
        return attribute.getValue();
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(Gender.class).stream()
                .filter(e->e.getValue().equals(dbData))
                .findAny()
                .orElseThrow(()-> new NoSuchElementException());
    }
}
