package com.example.restapi.shop.enums;

public enum City {
    TAIPEI("台北市"),
    NEW_TAIPEI("新北市"),
    TAICHUNG("台中市"),
    KAOHSIUNG("高雄市");

    private final String displayName;

    City(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    // 從字串取得對應 City enum
    public static City fromString(String city) {
        for (City c : City.values()) {
            if (c.getDisplayName().equals(city)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unknown city: " + city);
    }
}
