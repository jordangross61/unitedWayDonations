package edu.gatech.cs2340.buzzTracker.model;

public enum ItemType {
    CLOTHING ("Clothing"),
    HAT("Hat"),
    KITCHEN("Kitchen"),
    ELECTRONICS("Electronics"),
    HOUSEHOLD("Household"),
    OTHER("Other");

    private String category;

    ItemType (String category) {
        this.category = category;
    }

    public String toString() {
        return category;
    }

}
