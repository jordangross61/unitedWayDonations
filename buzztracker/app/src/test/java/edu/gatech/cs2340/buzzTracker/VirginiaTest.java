package edu.gatech.cs2340.buzzTracker;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.buzzTracker.model.Item;
import edu.gatech.cs2340.buzzTracker.model.Location;

import static org.junit.Assert.assertTrue;

/**
 * Class to test checkItemNull() method
 */
public class VirginiaTest {

    private Location l;
    private Location nullItemList;

    /**
     * Set up before testing
     */
    @Before
    public void setUp() {
        l = new Location(1, "test", 100.0, 100.0, "101 First St.", "Atlanta", "GA", "30332", "donation center",
                "4041234567", "testsite.com");
        nullItemList = new Location();
    }

    /**
     * Test when contain not null
     */
    @Test
    public void containNotNull() {
        l.checkItemInList(new Item());
        assertTrue(l.getItemList().isEmpty());
    }

    /**
     * Test if item is null
     */
    @Test
    public void containNull() {
        boolean answer = l.checkItemInList(null);
        assertTrue(answer == false);
    }



    /**
     * Test when itemList null
     */
    @Test(expected = Exception.class)
    public void NullList() {
        nullItemList.removeItemInList(new Item());
    }
}