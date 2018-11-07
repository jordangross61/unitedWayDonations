package edu.gatech.cs2340.buzzTracker.model;

/**
 *
 * <<enumeration>>
 *
 *  Holds the different user rights.
 *
 *  implements capability based security
 */

public enum UserRights {
    // GUEST("G"),
    USER("U"),
    EMPLOYEE("E"),
    MANAGER("M"),
    ADMIN("A");

    private String _userRights;

    UserRights (String userRights) {
        String _userRights = userRights;
    }

    public String getUserRights() {
        return _userRights;
    }
}
