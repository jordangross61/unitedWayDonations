package edu.gatech.cs2340.buzzTracker.model;

import java.io.Serializable;

/**
 *
 * Information Holder
 *
 * Maintains information about Locations
 */


public class Location implements Serializable{
    private int key;
    private String name;
    private double latitude;
    private double longitude;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String type;
    private String phone;
    private String website;


    public Location() {
    }

    /**
     * Creates a new Location
     */
    public Location(int key, String name, double latitude, double longitude, String street,
                    String city, String state, String zipcode, String type, String phone,
                    String website) {

        this.key = key;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.type = type;
        this.phone = phone;
        this.website = website;
    }

    public int getKey() { return key; }

    public String getName() { return name; }

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }

    public String getStreet() { return street; }

    public String getCity() { return city; }

    public String getState() { return state; }

    public String getZipcode() { return zipcode; }

    public String getType() { return type; }

    public String getPhone() { return phone; }

    public String getWebsite() { return website; }

    public boolean equals(Object other) {
        if (this == other) { return true; }
        if (!(other instanceof Location)) {return false; }
        Location that = (Location) other;
        return this.name.equals(that.name) && this.key == that.key
                && this.latitude == that.latitude && this.longitude == that.longitude;
    }

}

