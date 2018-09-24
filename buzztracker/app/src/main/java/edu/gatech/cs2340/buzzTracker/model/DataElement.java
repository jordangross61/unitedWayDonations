package edu.gatech.cs2340.buzzTracker.model;

/**
 * Information Holder
 * Primary responsibility is to maintain all the data about a single thing
 */

public class DataElement {
    private static int Next_ID = 1;
    private int _id;
    private String _name;
    private String _description;

    /**
     * Create new element
     * @param name   the name of the element
     * @param desc   a textual description
     */
    public DataElement(String name, String desc) {
        _name = name;
        _description = desc;
        _id = Next_ID++;
    }

    @Override
    public String toString() {
        return  _name + "\n" + _description;
    }

    /*
     Getters for the data elements
     */
    public String getName() { return _name;}
    public String getDescription() {  return _description; }

}
