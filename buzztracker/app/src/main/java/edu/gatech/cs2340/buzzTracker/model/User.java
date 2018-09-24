package edu.gatech.cs2340.buzzTracker.model;

/**
 *
 * Maintains information about the User class
 */

public class User {
    private String _username;
    private String _password;
    private String _name;
    private UserRights _rights;

    /**
     * Make a new User
     * @param uid  the user id for login
     * @param pwd  the password for login
     * @param name the actual name of the user
     */
    public User (String uid, String pwd, String name, UserRights rights) {
        _username = uid;
        _password = pwd;
        _name = name;
        _rights = rights;
    }

    /**
     * Check that the provided password matches
     * @param pwd the provided password
     * @return true if an exact string match for password, false otherwise
     */
    boolean checkPassword(String pwd) {
        return _password.equals(pwd);
    }

    /**
     * get the actual user name
     *
     * @return the actual name of the user
     */
    public String getName() { return _name; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("user: ");
        sb.append(_name);
        return sb.toString();
    }

    /**
     * Check whether user can add a new user or not
     * @return true if able to add user
     */
    public boolean canAddUser() {
        return _rights.canAddNewUser();
    }

    /**
     * Check whether the user can add or edit existing shelter information
     * @return true if able to add shelter
     */
    public boolean canEditShelter() {
        return _rights.canAddShelter();
    }

    /**
     * Check whether user can add an administrator type
     * @return true if able to add admin
     */
    public boolean canAddAdmin() {
        return _rights.canAddAdmin();
    }

    /**
     * Check whether user can delete a user from system
     * @return true if able to delete the user
     */
    public boolean canDeleteUser() {
        return _rights.canDeleteUser();
    }
}
