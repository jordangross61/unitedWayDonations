package edu.gatech.cs2340.buzzTracker.model;

import android.os.Parcel;
import android.os.Parcelable;

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
     * Make a new User
     *
     */

    public User () {}

    public User (String name, String email, String pwd, UserRights rights) {
        this(name, email, pwd, rights, null);
    }

    public User (String name, String email, String pwd, UserRights rights, Location location) {
        this.name = name;
        this.email = email;
        this.password = pwd;
        this.rights = rights;
        this.location = location;
    }

    /**
     * get the actual user name
     *
     * @return the actual name of the user
     */
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRights getRights() {
        return rights;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("user: ");
        sb.append(name);
        return sb.toString();
    }

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
    /**
     * Check whether user can add a new user or not
     * @return true if able to add user
     */
//    public boolean canAddUser() {
//        return _rights.canAddNewUser();
//    }

    /**
     * Check whether the user can add or edit existing shelter information
     * @return true if able to add shelter
     */
//    public boolean canEditShelter() {
//        return _rights.canAddShelter();
//    }

    /**
     * Check whether user can add an administrator type
     * @return true if able to add admin
     */
//    public boolean canAddAdmin() {
//        return _rights.canAddAdmin();
//    }

    /**
     * Check whether user can delete a user from system
     * @return true if able to delete the user
     */
//    public boolean canDeleteUser() {
//        return _rights.canDeleteUser();
//    }
}
