package com.waxtadpolegames.android.myplaces;

public enum TypePlace {
    OTHERS("Others", 5),
    RESTAURANT("Restaurant", 2),
    BAR("Bar", 6),
    CAFE("Cafe", 0),
    THEATER("Hotel", 0),
    HOTEL("Hotel", 0),
    SHOPPING("Shopping", 0),
    EDUCATION("Education", 0),
    SPORTS("Sports", 0),
    PARK("Park", 0),
    GAS_STATION("Gas Station", 0);

    private final String text;
    private final int resource;

    TypePlace(String text, int resource){
        this.text = text;
        this.resource = resource;
    }

    public String getText() { return text; }
    public int getResource() { return resource; }
}
