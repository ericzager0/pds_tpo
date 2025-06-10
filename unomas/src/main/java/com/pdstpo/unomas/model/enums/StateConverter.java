package com.pdstpo.unomas.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StateConverter implements AttributeConverter<StateEnum, String> {

    @Override
    public String convertToDatabaseColumn(StateEnum attribute) {
        if (attribute == null) return null;

        return switch (attribute) {
            case NECESITAMOS_JUGADORES -> "necesitamos_jugadores";
            case PARTIDO_ARMADO -> "partido_armado";
            case CONFIRMADO -> "confirmado";
            case CANCELADO -> "cancelado";
            case EN_JUEGO -> "en_juego";
            case FINALIZADO -> "finalizado";
        };
    }

    @Override
    public StateEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        return switch (dbData) {
            case "necesitamos_jugadores" -> StateEnum.NECESITAMOS_JUGADORES;
            case "partido_armado" -> StateEnum.PARTIDO_ARMADO;
            case "confirmado" -> StateEnum.CONFIRMADO;
            case "cancelado" -> StateEnum.CANCELADO;
            case "en_juego" -> StateEnum.EN_JUEGO;
            case "finalizado" -> StateEnum.FINALIZADO;
            default -> throw new IllegalArgumentException("Unknown state: " + dbData);
        };
    }
}
