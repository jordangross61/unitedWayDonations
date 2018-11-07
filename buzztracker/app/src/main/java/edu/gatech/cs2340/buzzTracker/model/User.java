package edu.gatech.cs2340.buzzTracker.model;

import java.io.Serializable;

/**
 *
 * Maintains information about the User class
 */
public class User implements Serializable {
    private String password;
    private String name;
    private String email;
    private UserRights rights;
    private Location location;

    /**
     * no-arg constructor for a user
     */
    public User () {}

    /**
     * constructor for a user that does not know which location is belongs to
     *
     * @param name the name of the user
     * @param email the user's email address
     * @param pwd the user's password
     * @param rights the user's rights
     */
    public User (String name, String email, String pwd, UserRights rights) {
        this(name, email, pwd, rights, null);
    }

    /**
     * constructor for a user in which all fields are set
     *
     * @param name the user's name
     * @param email the user's email address
     * @param pwd the user's password
     * @param rights the user's rights
     * @param location the user's location
     */
    public User (String name, String email, String pwd, UserRights rights, Location location) {
        this.name = name;
        this.email = email;
        this.password = pwd;
        this.rights = rights;
        this.location = location;
    }

    /**
     * get the actual user name
     * @return the actual name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * getter method for the user's email
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * getter method for the user's password
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * getter method for the user's rights
     * @return the user's rights
     */
    public UserRights getRights() {
        return rights;
    }

    /**
     * getter method for the user's location
     * @return the user's location
     */
    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("user: ");
        sb.append(name);
        return sb.toString();
    }

    /**
     * sets the user's location
     * @param l the user's new location
     */
    public void setLocation(Location l) {
        this.location = l;
    }

    /**
     * Check that the provided password matches
     * @param pwd the provided password
     * @return true if an exact string match for password, false otherwise
     */
    boolean checkPassword(String pwd) {
        return password.equals(pwd);
    }
    //    public boolean canAddUser() {
    //        return _rights.canAddNewUser();
    //    }

    //    public boolean canEditShelter() {
    //        return _rights.canAddShelter();
    //    }

    //    public boolean canAddAdmin() {
    //        return _rights.canAddAdmin();
    //    }

    //    public boolean canDeleteUser() {
    //        return _rights.canDeleteUser();
    //    }
}