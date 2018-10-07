package edu.gatech.cs2340.buzzTracker.model;

/**
 *
 * Information Holder
 *
 * Maintains information about Locations
 */

public class Location {
    private int _key;
    private String _name;
    private double _latitude;
    private double _longitude;
    private String _street;
    private String _city;
    private String _state;
    private String _zipcode;
    private String _type;
    private String _phone;
    private String _website;

    public Location() {}

    /**
     * Creates a new Location
     */
    public Location(int key, String name, double latitude, double longitude, String street,
                    String city, String state, String zipcode, String type, String phone,
                    String website) {
        _key = key;
        _name = name;
        _latitude = latitude;
        _longitude = longitude;
        _street = street;
        _city = city;
        _state = state;
        _zipcode = zipcode;
        _type = type;
        _phone = phone;
        _website = website;
    }

    /**
     *
     * @return  the latitude value
     */
    public double getLatitude() { return _latitude; }

    /**
     *
     * @return the longitude value
     */
    public double getLongitude() { return _longitude; }

    /**
     *
     * @return the longitude value
     */
    public String getName() { return _name; }

    public int getKey() { return _key; }
}

