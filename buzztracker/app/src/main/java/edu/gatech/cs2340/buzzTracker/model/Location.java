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
    private Inventory _inventory;

    public Location() {
    }

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
        _inventory = new Inventory();
    }

    public int getKey() { return _key; }

    public String getName() { return _name; }

    public double getLatitude() { return _latitude; }

    public double getLongitude() { return _longitude; }

    public String getStreet() { return _street; }

    public String getCity() { return _city; }

    public String getState() { return _state; }

    public String getZipcode() { return _zipcode; }

    public String getType() { return _type; }

    public String getPhone() { return _phone; }

    public String getWebsite() { return _website; }

    public boolean equals(Object other) {
        if (this == other) { return true; }
        if (!(other instanceof Location)) {return false; }
        Location that = (Location) other;
        return this._name.equals(that._name) && this._key == that._key
                && this._latitude == that._latitude && this._longitude == that._longitude;
    }

}

