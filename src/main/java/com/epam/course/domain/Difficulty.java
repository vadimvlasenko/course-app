package com.epam.course.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Difficulty {
    EASY, MEDIUM, HARD;

    private static final Map<String, Difficulty> DIFFICULTY_BY_NAME =
            Arrays.stream(values()).collect(Collectors.toMap(difficulty -> difficulty.name().toLowerCase(), Function.identity()));

    public static Difficulty fromName(String name) {
        if (name == null) {
            return null;
        }
        return DIFFICULTY_BY_NAME.get(name.toLowerCase());
    }
}
