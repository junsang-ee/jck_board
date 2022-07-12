package com.devjck.springboard.domain.user;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class UserAuthorityConverter implements AttributeConverter<Authority, String > {
    @Override
    public String convertToDatabaseColumn(Authority attribute) {
        return attribute.getValue();
    }

    @Override
    public Authority convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(Authority.class).stream()
                .filter(e->e.getValue().equals(dbData))
                .findAny()
                .orElseThrow(()-> new NoSuchElementException());
    }
}
