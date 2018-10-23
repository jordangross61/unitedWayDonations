package edu.gatech.cs2340.buzzTracker.model;

// would be used to create a hat or clothing item that have a size
public class SizeItem extends Item {

    private String time;
    private Location location;
    private String shortDescription;
    private String longDescription;
    private double value;
    private ItemType category;
    private String comments;
    private Size size;

    public SizeItem(String time, Location location, String shortDescription, String longDescription, double value, ItemType category, String comments) {}

    public SizeItem(String time, Location location, String shortDescription, String longDescription,
               double value, ItemType category, String comments, Size size) {

        this (time, location, shortDescription, longDescription, value, category, comments);
        this.size = size;
    }
}
