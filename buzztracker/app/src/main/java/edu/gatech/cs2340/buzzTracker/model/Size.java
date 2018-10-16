package edu.gatech.cs2340.buzzTracker.model;

public enum Size {

    EXTRA_SMALL("XS"), SMALL("S"), MEDIUM('M'), LARGE("L"), EXTRA_LARGE("XL");

    private final String abbreviation;

    Size(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
