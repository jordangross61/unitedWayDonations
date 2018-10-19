package edu.gatech.cs2340.buzzTracker.model;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Information Holder
 * Primary responsibility is to maintain all the data about a single thing
 */

public class Item implements Serializable {
    private static int NEXT_ID = 0;
    private int id;

    private String time;
    private Location location;
    private String shortDescription;
    private String longDescription;
    private double value;
    private ItemType category;
    private String comments;

    public Item() {}

    /**
     * Create new element
     *
     */

    public Item(String time, Location location, String shortDescription, String longDescription,
                double value, ItemType category, String comments) {

        this.id = NEXT_ID++;
        this.time = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        this.location = location;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.value = value;
        this.category = category;
        this.comments = comments;
    }

    /*
     Getters for the data elements
     */
    public int getId() { return id; }
    public String getTime() { return time; }
    public Location getLocation() {  return location; }
    public String getShortDescription() { return shortDescription; }
    public String getLongDescription() { return longDescription; }
    public double getValue() { return value; }
    public ItemType getCategory() { return category; }
    public String getComments() { return comments; }

}
