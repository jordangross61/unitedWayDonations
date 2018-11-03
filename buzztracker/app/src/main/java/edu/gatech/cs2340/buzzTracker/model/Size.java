package edu.gatech.cs2340.buzzTracker.model;

/**
 * Represents the possible sizes for donation items that have sizes
 */
public enum Size {
    XS("Extra Small"), S("Small"), M("Medium"), L("Large"), XL("Extra Large");

    private String size;

    Size(String size) {
        this.size = size;
    }

    @Override
    public String toString() { return size; }
}
