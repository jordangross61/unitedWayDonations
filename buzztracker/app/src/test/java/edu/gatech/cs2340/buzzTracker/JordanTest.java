package edu.gatech.cs2340.buzzTracker;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.buzzTracker.model.Item;
import edu.gatech.cs2340.buzzTracker.model.Location;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for the removeItemInList method in the Location class.
 *
 * @author Jordan Gross
 */
public class JordanTest {

    private Location l;
    private Location nullItemList;

    /**
     * What to do before each test
     */
    @Before
    public void setUp() {
        l = new Location(1, "test", 100.0, 100.0, "101 First St.", "Atlanta", "GA", "30332", "donation center",
                "4041234567", "testsite.com");
        nullItemList = new Location();
    }

    /**
     * Test remove when its not null
     */
    @Test
    public void removeNotNull() {
        l.removeItemInList(new Item());
        assertTrue(l.getItemList().isEmpty());
    }

    /**
     * Test remove when its null
     */
    @Test(expected = IllegalArgumentException.class)
    public void removeNull() {
        l.removeItemInList(null);
    }

    /**
     * Test remove when itemList is null
     */
    @Test(expected = IllegalArgumentException.class)
    public void NullList() {
        nullItemList.removeItemInList(new Item());
    }
}