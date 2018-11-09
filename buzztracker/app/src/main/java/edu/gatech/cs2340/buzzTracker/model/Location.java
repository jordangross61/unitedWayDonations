package edu.gatech.cs2340.buzzTracker.model;

import java.io.Serializable;
import java.util.ArrayList;

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
    private ArrayList<Item> itemList;

    /**
     * no-arg constructor for a location
     */
    public Location() { }

    /**
     * constructor for a Location object that sets all the instance fields
     *
     * @param key a number that is unique to this location
     * @param name the name of the donation location
     * @param latitude the latitude of the location
     * @param longitude the longitude of the location
     * @param street the address of the location
     * @param city the city that the donation location is in
     * @param state the state that the donation location is in
     * @param zipcode the zip code of the location
     * @param type the type of the location
     * @param phone the location's phone number as a String
     * @param website the location's website address
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
        this.itemList = new ArrayList<>();
    }

    /**
     * getter method for the location's key
     * @return the location's key
     */
    public int getKey() { return key; }

    /**
     * getter method for the location's name
     * @return the location's name
     */
    public String getName() { return name; }

    /**
     * getter method for the location's latitude
     * @return the location's latitude
     */
    public double getLatitude() { return latitude; }

    /**
     * getter method for the location's longitude
     * @return the location's longitude
     */
    public double getLongitude() { return longitude; }

    /**
     * getter method for the location's street address
     * @return the location's street address
     */
    public String getStreet() { return street; }

    /**
     * getter method for the location's city
     * @return the location's city
     */
    public String getCity() { return city; }

    /**
     * getter method for the location's state
     * @return the location's state
     */
    public String getState() { return state; }

    /**
     * getter method for the location's zip code
     * @return the location's zip code
     */
    public String getZipcode() { return zipcode; }

    /**
     * getter method for the location's type
     * @return the location's type
     */
    public String getType() { return type; }

    /**
     * getter method for the location's phone number
     * @return the location's phone number
     */
    public String getPhone() { return phone; }

    /**
     * getter method for the location's website
     * @return the location's website
     */
    public String getWebsite() { return website; }

    /**
     * getter method for the location's list of items
     * @return the location's item list
     */
    public ArrayList<Item> getItemList() {
        return itemList;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) { return true; }
        if (!(other instanceof Location)) {return false; }
        Location that = (Location) other;
        return this.name.equals(that.name) && this.key == that.key
                && this.latitude == that.latitude && this.longitude == that.longitude;
    }

    /**
     * Adds an item to the list of items at this location
     * @param myItem the item to be added to the list
     */
    public void setItemInList(Item myItem) {
        if (this.itemList == null) {
            this.itemList = new ArrayList<>();
        }
        if (myItem != null) {
            this.itemList.add(myItem);
        }
    }

    public void removeItemInList(Item myItem) {
        if (myItem == null) {
            throw new IllegalArgumentException();
        }
        if (myItem != null) {
            this.itemList.remove(myItem);
        }
    }

    public boolean checkItemInList(Item myItem) {
        if (this.itemList == null) {
            throw new IllegalArgumentException("Cannot take in null item");
        }
        if (myItem != null) {
            return itemList.contains(myItem);
        }
        return false;
    }

    public ArrayList<Item> returnAllItemsAtLocation(int locationId) {
        ArrayList<Item> temp = new ArrayList<>();

        if (this.itemList == null) {
            throw new NullPointerException();
        }

        for (Item item : this.itemList) {
            if (item.getLocationId() == locationId) {
                temp.add(item);
            }
        }
        return temp;
    }

    public void removeCategory(ItemType myItemType) {
        if (myItemType == null) {
            throw new IllegalArgumentException("Cannot take in null category");
        }
        if (myItemType != null) {
            for (int i = 0; i < itemList.size(); i++) {
                if (itemList.get(i).getCategory().equals(myItemType)) {
                    itemList.remove(i);
                }
            }
        }
    }

}
