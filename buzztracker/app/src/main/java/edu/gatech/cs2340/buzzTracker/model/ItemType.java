package edu.gatech.cs2340.buzzTracker.model;

/**
 * class representing the types of items that can be donated
 */
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

    @Override
    public String toString() {
        return category;
    }

}
