package com.pdstpo.unomas.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MatchmakingStrategyConverter implements AttributeConverter<MatchmakingStrategyEnum, String> {

    @Override
    public String convertToDatabaseColumn(MatchmakingStrategyEnum attribute) {
        if (attribute == null) return null;

        return switch (attribute) {
            case LEVEL -> "level";
            case PROXIMITY -> "proximity";
            case HISTORY -> "history";
        };
    }

    @Override
    public MatchmakingStrategyEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        return switch (dbData) {
            case "level" -> MatchmakingStrategyEnum.LEVEL;
            case "proximity" -> MatchmakingStrategyEnum.PROXIMITY;
            case "history" -> MatchmakingStrategyEnum.HISTORY;
            default -> throw new IllegalArgumentException("Unknown state: " + dbData);
        };
    }
}
