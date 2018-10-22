package edu.gatech.cs2340.buzzTracker.model;

public enum Size {
    XS("Extra Small"), S("Small"), M("Medium"), L("Large"), XL("Extra Small");

    private String size;

    Size(String size) {
        this.size = size;
    }

    public String toString() { return size; }
}
