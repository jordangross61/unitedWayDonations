package edu.gatech.cs2340.buzzTracker.model;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * <<Structurer>>
 * This class holds all the known users for the app.  It allows for searching for a user
 * and performing valid actions on users like login.
 *
 */

public class UserManager {
    /** the map of current users  Key = username  Value = User object */
    public Map<String, User> _users;

    /**
     * Adds a user to the map of known users
     * Username must be unique, so if you try to add the same username, add will fail
     *
     * @param password  the password of the new user
     * @param name  the name of the new user
     * @return  true if add was successful, false otherwise
     */

    public boolean addUser(String name, String email, String password, UserRights rights) {

        User user = new User(name, email, password, rights);

        //check that username is not already in collection
        if (_users.containsKey(email)) return false;

        _users.put(email, user);
        return true;
    }

    /**
     * Check for a successful login by looking up the user and checking their password.
     *
     * @param email  the login user id
     * @param password  the login password
     * @return User object if successful, null otherwise
     */
    public User checkLogin(String email, String password) {
        User user = null;
        if (_users.containsKey(email)) {
            user = _users.get(email);
            if (user.checkPassword(password)) {
                return user;
            }
            return null;
        } else {
            return null;
        }

    }

    /**
     * Constructs a new object
     * will be replaced when you can actually create people through the registration feature
     */
    public UserManager() {
        _users = new HashMap<>();
    }
}
