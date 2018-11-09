package edu.gatech.cs2340.buzzTracker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import edu.gatech.cs2340.buzzTracker.model.Item;
import edu.gatech.cs2340.buzzTracker.model.Location;
import edu.gatech.cs2340.buzzTracker.model.Size;

import static org.junit.Assert.assertTrue;

public class KrishnaTest {

    private Location loc;
    private Location nullItemList;
    private ArrayList<Item> items;

    @Before
    public void setUp() {
        loc = new Location(1, "test", 100.0, 100.0, "101 First St.", "Atlanta", "GA", "30332", "donation center",
                "4041234567", "testsite.com");
    }

    @Test
    public void testNoItemsAtLocation() {
        assertTrue(loc.getItemList().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullList() {
        loc.returnAllItemsAtLocation(1);
    }

    @Test
    public void testListWithOnlyLocationLookingFor() {
        Item item1 = new Item(null, "Clothing 1", "This is Test Item Clothing 1", 10.00, "CLOTHING", "Extra Comments",
                1, Size.XS);
        Item item2 = new Item(null, "Clothing 2", "This is Test Item Clothing 2", 10.00, "CLOTHING", "Extra Comments",
                1, Size.XS);
        Item item3 = new Item(null, "Clothing 3", "This is Test Item Clothing 3", 10.00, "CLOTHING", "Extra Comments",
                1, Size.XS);
        Item item4 = new Item(null, "Clothing 4", "This is Test Item Clothing 4", 10.00, "CLOTHING", "Extra Comments",
                1, Size.XS);

        loc.setItemInList(item1);
        loc.setItemInList(item2);
        loc.setItemInList(item3);
        loc.setItemInList(item4);

        items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);

        Assert.assertEquals(loc.returnAllItemsAtLocation(1), items);
    }

    @Test
    public void testListWithManyDifferentLocations() {
        Item item1 = new Item(null, "Clothing 1", "This is Test Item Clothing 1", 10.00, "CLOTHING", "Extra Comments",
                1, Size.XS);
        Item item2 = new Item(null, "Clothing 2", "This is Test Item Clothing 2", 10.00, "CLOTHING", "Extra Comments",
                1, Size.XS);
        Item item3 = new Item(null, "Clothing 3", "This is Test Item Clothing 3", 10.00, "CLOTHING", "Extra Comments",
                1, Size.XS);
        Item item4 = new Item(null, "Clothing 4", "This is Test Item Clothing 4", 10.00, "CLOTHING", "Extra Comments",
                1, Size.XS);
        Item item5 = new Item(null, "Clothing 5", "This is Test Item Clothing 5", 10.00, "CLOTHING", "Extra Comments",
                2, Size.XS);
        Item item6 = new Item(null, "Clothing 6", "This is Test Item Clothing 6", 10.00, "CLOTHING", "Extra Comments",
                3, Size.XS);
        Item item7 = new Item(null, "Clothing 7", "This is Test Item Clothing 7", 10.00, "CLOTHING", "Extra Comments",
                4, Size.XS);
        Item item8 = new Item(null, "Clothing 8", "This is Test Item Clothing 8", 10.00, "CLOTHING", "Extra Comments",
                0, Size.XS);

        // Add all items to the location
        loc.setItemInList(item1);
        loc.setItemInList(item2);
        loc.setItemInList(item3);
        loc.setItemInList(item4);
        loc.setItemInList(item5);
        loc.setItemInList(item6);
        loc.setItemInList(item7);
        loc.setItemInList(item8);

        // All locations that are only at Location 1 to check against
        items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);

        // Check that only items @locationId 1 is filtered
        Assert.assertEquals(loc.returnAllItemsAtLocation(1), items);
    }

}
