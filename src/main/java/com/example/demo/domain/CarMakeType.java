package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Locale;

@Getter
@AllArgsConstructor
public enum CarMakeType {
    LEXUS("Lexus"),
    RENAULT("Renault");
    // add more

    private final String description;

    public static CarMakeType fromDescription(final String description) {
        return Arrays.stream(CarMakeType.values())
                .filter(makeType -> makeType.getDescription().toLowerCase(Locale.ROOT).equals(description.toLowerCase(Locale.ROOT)))
                .findFirst().orElse(null);
    }
}
