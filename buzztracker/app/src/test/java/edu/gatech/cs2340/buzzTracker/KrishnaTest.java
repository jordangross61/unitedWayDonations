package edu.gatech.cs2340.buzzTracker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import edu.gatech.cs2340.buzzTracker.model.Item;
import edu.gatech.cs2340.buzzTracker.model.ItemType;
import edu.gatech.cs2340.buzzTracker.model.Location;
import edu.gatech.cs2340.buzzTracker.model.Size;

import static org.junit.Assert.assertTrue;

public class KrishnaTest {

    private Location loc;
    private ArrayList<Item> items;

    private Item item1;
    private Item item2;
    private Item item3;
    private Item item4;

    @Before
    public void setUp() {
        loc = new Location(1, "test", 100.0, 100.0, "101 First St.", "Atlanta", "GA", "30332", "donation center",
                "555-5555", "testsite.com");
        items = new ArrayList<>();
        item1 = new Item(null, "Clothing 1", "This is Test Item Clothing 1", 10.00, "Clothing", "Extra Comments",
                1, Size.XS);
        item2 = new Item(null, "Clothing 2", "This is Test Item Clothing 2", 10.00, "Clothing", "Extra Comments",
                1, Size.XS);
        item3 = new Item(null, "Clothing 3", "This is Test Item Clothing 3", 10.00, "Clothing", "Extra Comments",
                1, Size.XS);
        item4 = new Item(null, "Clothing 4", "This is Test Item Clothing 4", 10.00, "Clothing", "Extra Comments",
                1, Size.XS);
    }

    @Test
    public void testNoItemsAtLocation() {
        assertTrue(loc.getItemList().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullList() {
        // itemList will be empty bc no items have been added
        loc.filterCategories(ItemType.CLOTHING);
    }

    @Test
    public void testListWithNoMatchingCategory() {
        // Should return empty array

        // item1, item2, item3, item4 all Items at Location1
        loc.setItemInList(item1);
        loc.setItemInList(item2);
        loc.setItemInList(item3);
        loc.setItemInList(item4);

        // filterCategory will filter all items with the category CLOTHING
        Assert.assertEquals(loc.filterCategories(ItemType.CLOTHING), items);
    }

    @Test
    public void testListWithOnlyMatchingCategory() {

        loc.setItemInList(item1);
        loc.setItemInList(item2);
        loc.setItemInList(item3);
        loc.setItemInList(item4);

        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);

        Assert.assertEquals(loc.filterCategories(ItemType.CLOTHING), items);
    }

    @Test
    public void testListWithManyDifferentCategories() {
        Item item5 = new Item(null, "Clothing 5", "This is Test Item Clothing 5", 10.00, "Electronics", "Extra Comments",
                1, Size.XS);
        Item item6 = new Item(null, "Clothing 6", "This is Test Item Clothing 6", 10.00, "Hat", "Extra Comments",
                1, Size.XS);
        Item item7 = new Item(null, "Clothing 7", "This is Test Item Clothing 7", 10.00, "Other", "Extra Comments",
                1, Size.XS);
        Item item8 = new Item(null, "Clothing 8", "This is Test Item Clothing 8", 10.00, "Household", "Extra Comments",
                1, Size.XS);

        // Add all items to the location
        loc.setItemInList(item1);
        loc.setItemInList(item2);
        loc.setItemInList(item3);
        loc.setItemInList(item4);
        loc.setItemInList(item5);
        loc.setItemInList(item6);
        loc.setItemInList(item7);
        loc.setItemInList(item8);

        // All Items that have a CLOTHING category
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);

        // Check that only items @category CLOTHING is filtered
        Assert.assertEquals(loc.filterCategories(ItemType.CLOTHING), items);
    }

    @Test
    public void testListWithManyDifferentCategoriesForHat() {
        Item electronic = new Item(null, "Electronic", "This is Test Item Clothing 5", 10.00, "Electronics", "Extra Comments",
                1, null);
        Item hat = new Item(null, "Hat", "This is Test Item Clothing 6", 10.00, "Hat", "Extra Comments",
                1, null);
        Item other = new Item(null, "Other", "This is Test Item Clothing 7", 10.00, "Other", "Extra Comments",
                1, null);
        Item household = new Item(null, "Household", "This is Test Item Clothing 8", 10.00, "Household", "Extra Comments",
                1, null);

        // Add all items to the location
        loc.setItemInList(item1);
        loc.setItemInList(item2);
        loc.setItemInList(item3);
        loc.setItemInList(item4);
        loc.setItemInList(electronic);
        loc.setItemInList(hat);
        loc.setItemInList(other);
        loc.setItemInList(household);

        // All Items that have a HAT category
        items.add(hat);

        // Check that only items @category HAT is filtered
        Assert.assertEquals(loc.filterCategories(ItemType.HAT), items);
    }

}
