package com.epam.course.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DifficultyConverter implements AttributeConverter<Difficulty, String> {
    @Override
    public String convertToDatabaseColumn(Difficulty difficulty) {
        return difficulty.name();
    }

    @Override
    public Difficulty convertToEntityAttribute(String difficultyLabel) {
        return Difficulty.fromName(difficultyLabel);
    }
}
