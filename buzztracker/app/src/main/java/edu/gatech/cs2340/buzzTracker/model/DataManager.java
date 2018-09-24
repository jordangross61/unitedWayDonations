package edu.gatech.cs2340.buzzTracker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * primary responsibility is to manage a group of DataElements
 *
 */

class DataManager {
    List<DataElement> theData;

    DataManager() {
        theData = new ArrayList<>();
        makeSomeData();
    }

    private void makeSomeData() {
            addReport(new DataElement("Coke Zero", "Sam's Deli"));
            addReport(new DataElement("Pepsi", "Grandma Garage"));
    }

    void addReport(DataElement de) {
        theData.add(de);
    }

    List<DataElement> getData() { return theData; }

}
