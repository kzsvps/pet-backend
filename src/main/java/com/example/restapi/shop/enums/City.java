package com.example.restapi.shop.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum City {
    TAIPEI("台北市"),
    TAICHUNG("台中市"),
    KAOHSIUNG("高雄市");

    private final String label;

    City(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static City fromLabel(String label) {
        for (City city : City.values()) {
            if (city.label.equals(label) || city.name().equalsIgnoreCase(label)) {
                return city;
            }
        }
        throw new IllegalArgumentException("Invalid city: " + label);
    }
}
