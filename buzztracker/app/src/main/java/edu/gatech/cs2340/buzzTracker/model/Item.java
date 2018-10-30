package edu.gatech.cs2340.buzzTracker.model;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

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

    public Item() {}

    /**
     * Create new element
     *
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

    public String makeTime(Long time) {
        Date date = new Date(time);
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone("EST"));
        return formatter.format(date);
    }

    /*
     Getters for the data elements
     */
    public int getId() { return id; }
    public String getTime() { return time; }
    public Size getSize() { return size; }
    public String getShortDescription() { return shortDescription; }
    public String getLongDescription() { return longDescription; }
    public double getValue() { return value; }
    public String getCategory() { return category; }
    public String getComments() { return comments; }
    public int getLocationId() {return locationId;};

    public String toString() { return category.toString()+ ": " + shortDescription + " @ Location No. " + locationId; }

}
