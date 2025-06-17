package com.pdstpo.unomas.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LevelEnumConverter implements AttributeConverter<LevelEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(LevelEnum attribute) {
        if (attribute == null) return null;

        return switch (attribute) {
            case PRINCIPIANTE -> 1;
            case INTERMEDIO -> 2;
            case AVANZADO -> 3;
        };
    }

    @Override
    public LevelEnum convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;

        return switch (dbData) {
            case 1 -> LevelEnum.PRINCIPIANTE;
            case 2 -> LevelEnum.INTERMEDIO;
            case 3 -> LevelEnum.AVANZADO;
            default -> throw new IllegalArgumentException("Unknown state: " + dbData);
        };
    }
}
