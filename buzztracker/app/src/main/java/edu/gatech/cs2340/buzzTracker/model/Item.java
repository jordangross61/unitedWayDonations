package edu.gatech.cs2340.buzzTracker.model;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Information Holder
 * Primary responsibility is to maintain all the data about a single thing
 */
public class Item implements Serializable {
    private static int NEXT_ID = 0;
    private int id;

    private String time;
    private String shortDescription;
    private String longDescription;
    private double value;
    private String category;
    private String comments;
    private Size size;
    private int locationId;

    /**
     * No-arg constructor for an item
     */
    public Item() {}

    /**
     * constructor for an Item that sets at the instance fields
     *
     * @param time the time that the item is created
     * @param shortDescription the short description of the item
     * @param longDescription the long description of the item
     * @param value the monetary value of the item in USD
     * @param category the category of the item
     * @param comments additional comments on the item
     * @param locationId the identifier for the item's location
     * @param size the size of the item, which is only important for clothing
     */
    public Item(String time, String shortDescription, String longDescription,
                double value, String category, String comments, int locationId, Size size) {
        this.id = NEXT_ID++;
        this.time = makeTime(System.currentTimeMillis());
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.value = value;
        this.category = category;
        this.comments = comments;
        this.locationId = locationId;
        this.size = size;
    }

    /**
     * Converts a long value to a formatted Date
     *
     * @param time a long value representing a time
     * @return the formatted date
     */
    public String makeTime(Long time) {
        Date date = new Date(time);
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone("EST"));
        return formatter.format(date);
    }

    /**
     * getter method for the item's ID
     * @return the item's id
     */
    public int getId() { return id; }

    /**
     * getter method for the time the item was created
     * @return the item's creation time
     */
    public String getTime() { return time; }

    /**
     * getter method for the item's size
     * @return the item's size
     */
    public Size getSize() { return size; }

    /**
     * getter method for the item's short description
     * @return the item's short description
     */
    public String getShortDescription() { return shortDescription; }

    /**
     * getter method for the item's long description
     * @return the item's long description
     */
    public String getLongDescription() { return longDescription; }

    /**
     * getter method for the item's monetary value
     * @return the item's value
     */
    public double getValue() { return value; }

    /**
     * getter method for the item's category
     * @return the item's category
     */
    public String getCategory() { return category; }

    /**
     * getter method for the comments about the item
     * @return the item's comments
     */
    public String getComments() { return comments; }

    /**
     * getter method for the item's location
     * @return the item's location
     */
    public int getLocationId() {return locationId; }

    @Override
    public String toString() {
        return category.toString()+ ": " + shortDescription + " @ Location No. " + locationId;
    }
}
