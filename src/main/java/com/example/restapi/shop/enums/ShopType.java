package com.example.restapi.shop.enums;

public enum ShopType {
    FOOD("美食"),
    DRINK("飲品"),
    BAR("酒吧"),
    SPORTS("運動"),
    ART("藝文");

    private final String displayName;

    ShopType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ShopType fromString(String type) {
        for (ShopType t : ShopType.values()) {
            if (t.getDisplayName().equals(type)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Unknown shop type: " + type);
    }
}
