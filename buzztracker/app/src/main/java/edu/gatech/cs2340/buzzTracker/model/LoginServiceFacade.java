package edu.gatech.cs2340.buzzTracker.model;

import android.util.Log;

public class LoginServiceFacade {
    private static LoginServiceFacade INSTANCE = new LoginServiceFacade();
    private User currentUser = null;

    /**
     * This is the singleton pattern accessor.  Call this to get the instance of this class
     * @return  the one instance of the facade
     */
    public static LoginServiceFacade getInstance() { return INSTANCE; }

    /**  the class that manages all the users */
    private UserManager _userManager;

    /**
     *   Private constructor since this is a singleton.
     */
    private LoginServiceFacade() {
        _userManager = new UserManager();
    }

    public UserManager getUserManager() {return _userManager;}

    /**
     * Call this method to get the currently logged in user, will return
     * a null if there is no one logged in
     *
     * @return  the last logged in user,
     */
    public User getCurrentUser() {return currentUser; }

    /**
     * Handle a Login request,  delegate the action to the actual model classes
     * Sets the current user value if login successful
     * @param username  the user id of the person trying to log in
     * @param password  the password of the person trying to log in
     * @return  true if login success, false otherwise
     */
    public boolean doLogin(String username, String password) {
        //don't do anything if someone is already logged in
        if (hasLoggedInUser()) return false;
        //get the user from the model
        User user = _userManager.checkLogin(username, password);
        //check for success
        if (user != null) {
            currentUser = user;
            return true;
        }
        return false;
    }

    /**
     * Call this method for logout, which sets the currentUser to null
     */
    public void logout() {
        currentUser = null;
    }

    /**
     * Call this method to check if anyone is logged in
     */
    public boolean hasLoggedInUser() { return currentUser != null; }
}
