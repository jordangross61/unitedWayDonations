package edu.gatech.cs2340.buzzTracker;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.buzzTracker.model.Item;
import edu.gatech.cs2340.buzzTracker.model.Location;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for the setItemInList method in the Location class. Tests for null and non-null list,
 * as well as null and non-null Items.
 *
 * @author Kate Schaffer
 */
public class LocationTest {

    private Location l;
    private Location nullItemList;

    @Before
    public void setUp() {
        l = new Location(1, "test", 100.0, 100.0, "101 First St.", "Atlanta", "GA", "30332", "donation center",
                "4041234567", "testsite.com");
        nullItemList = new Location();
    }

    @Test
    public void setItemInListNotNull() {
        assertTrue(l.getItemList().isEmpty());
        l.setItemInList(new Item());
        assertFalse(l.getItemList().isEmpty());
    }

    @Test
    public void setItemInNullList() {
        assertTrue(nullItemList.getItemList() == null);
        nullItemList.setItemInList(new Item());
        assertFalse(nullItemList.getItemList().isEmpty());
    }

    @Test
    public void putNullInList() {
        assertTrue(l.getItemList().isEmpty());
        l.setItemInList(null);
        assertTrue(l.getItemList().isEmpty());
    }

    @Test
    public void putNullInNullList() {
        assertTrue(nullItemList.getItemList() == null);
        nullItemList.setItemInList(null);
        assertTrue(nullItemList.getItemList() != null);
        assertTrue(nullItemList.getItemList().isEmpty());
    }
}
