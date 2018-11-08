package edu.gatech.cs2340.buzzTracker;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.buzzTracker.model.Item;
import edu.gatech.cs2340.buzzTracker.model.Location;

import static org.junit.Assert.assertTrue;

public class JordanTest {

    private Location l;
    private Location nullItemList;

    @Before
    public void setUp() {
        l = new Location(1, "test", 100.0, 100.0, "101 First St.", "Atlanta", "GA", "30332", "donation center",
                "4041234567", "testsite.com");
        nullItemList = new Location();
    }

    @Test
    public void removeNotNull() {
        l.removeItemInList(new Item());
        assertTrue(l.getItemList().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNull() {
        l.removeItemInList(null);
    }

    @Test(expected = Exception.class)
    public void NullList() {
        nullItemList.removeItemInList(new Item());
    }
}