package com.example.restapi.shop.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ShopType {
    FOOD("美食"),
    DRINK("飲品"),
    BAR("酒吧"),
    SPORT("運動"),
    ART("藝文");

    private final String label;

    ShopType(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static ShopType fromLabel(String label) {
        for (ShopType type : ShopType.values()) {
            if (type.label.equals(label) || type.name().equalsIgnoreCase(label)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid shop type: " + label);
    }
}
