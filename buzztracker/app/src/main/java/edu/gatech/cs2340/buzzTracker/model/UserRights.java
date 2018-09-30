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
    GUEST("GUEST"),
    USER("USER"),
    ADMIN("ADMIN");

//    boolean _writeShelter;
//    boolean _addUser;
//    boolean _deleteUser;
//    boolean _addAdmin;

    private String _userRights;

    UserRights (String userRights) {
        _userRights = userRights;
    }

//    UserRights(boolean writeShelter, boolean adduser, boolean deleteuser, boolean addadmin) {
//        _writeShelter = writeShelter;
//        _addUser = adduser;
//        _deleteUser = deleteuser;
//        _addAdmin = addadmin;
//    }
//
//    /** methods to check rights, not called directly but through User class */
//    public boolean canAddShelter() { return _writeShelter; }
//    public boolean canAddNewUser() { return _addUser; }
//    public boolean canDeleteUser() { return _deleteUser; }
//    public boolean canAddAdmin() { return _addAdmin; }
}
