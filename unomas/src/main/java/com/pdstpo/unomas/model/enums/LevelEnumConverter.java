package com.pdstpo.unomas.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LevelEnumConverter implements AttributeConverter<LevelEnum, String> {

    @Override
    public String convertToDatabaseColumn(LevelEnum attribute) {
        if (attribute == null) return null;

        return switch (attribute) {
            case PRINCIPIANTE -> "principiante";
            case INTERMEDIO -> "intermedio";
            case AVANZADO -> "avanzado";
        };
    }

    @Override
    public LevelEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        return switch (dbData) {
            case "principiante" -> LevelEnum.PRINCIPIANTE;
            case "intermedio" -> LevelEnum.INTERMEDIO;
            case "avanzado" -> LevelEnum.AVANZADO;
            default -> throw new IllegalArgumentException("Unknown state: " + dbData);
        };
    }
}
