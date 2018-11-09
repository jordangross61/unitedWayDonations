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

/**
 * Unit test for the filterCategory method in the Location class.
 *
 * @author Krishna Patel
 */
public class KrishnaTest {

    private Location loc;
    private ArrayList<Item> items;

    private Item clothing1;
    private Item clothing2;
    private Item clothing3;
    private Item clothing4;
    private Item household;
    private Item hat;
    private Item electronic;
    private Item other;

    @Before
    public void setUp() {
        loc = new Location(1, "test", 100.0, 100.0, "101 First St.", "Atlanta", "GA", "30332", "donation center",
                "555-5555", "testsite.com");
        items = new ArrayList<>();

        clothing1 = new Item(null, "Clothing 1", "This is Test Item Clothing 1", 10.00, "Clothing", "Extra Comments",
                1, Size.XS);
        clothing2 = new Item(null, "Clothing 2", "This is Test Item Clothing 2", 10.00, "Clothing", "Extra Comments",
                1, Size.XS);
        clothing3 = new Item(null, "Clothing 3", "This is Test Item Clothing 3", 10.00, "Clothing", "Extra Comments",
                1, Size.XS);
        clothing4 = new Item(null, "Clothing 4", "This is Test Item Clothing 4", 10.00, "Clothing", "Extra Comments",
                1, Size.XS);
        household = new Item(null, "Household", "This is Test Item Clothing 8", 10.00, "Household", "Extra Comments",
                1, null);
        hat = new Item(null, "Hat", "This is Test Item Clothing 6", 10.00, "Hat", "Extra Comments",
                1, null);
        electronic = new Item(null, "Electronic", "This is Test Item Clothing 5", 10.00, "Electronics", "Extra Comments",
                1, null);
        other = new Item(null, "Other", "This is Test Item Clothing 7", 10.00, "Other", "Extra Comments",
                1, null);
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
    // Should return empty array
    public void testListWithNoMatchingCategory() {

        // household, electronic, hat, other all Items at Location1 not of ItemType CLOTHING
        loc.setItemInList(household);
        loc.setItemInList(electronic);
        loc.setItemInList(hat);
        loc.setItemInList(other);

        // filterCategory will filter all items with the category CLOTHING (result in empty array)
        Assert.assertEquals(loc.filterCategories(ItemType.CLOTHING), items);
    }

    @Test
    public void testListWithOnlyMatchingCategory() {

        loc.setItemInList(clothing1);
        loc.setItemInList(clothing2);
        loc.setItemInList(clothing3);
        loc.setItemInList(clothing4);

        items.add(clothing1);
        items.add(clothing2);
        items.add(clothing3);
        items.add(clothing4);

        //return all items at that location
        Assert.assertEquals(loc.filterCategories(ItemType.CLOTHING), items);
    }

    @Test
    public void testListWithManyDifferentCategories() {
        // Add all items to the location
        loc.setItemInList(clothing1);
        loc.setItemInList(clothing2);
        loc.setItemInList(clothing3);
        loc.setItemInList(clothing4);
        loc.setItemInList(hat);
        loc.setItemInList(electronic);
        loc.setItemInList(household);
        loc.setItemInList(other);

        // All Items that have a CLOTHING category
        items.add(clothing1);
        items.add(clothing2);
        items.add(clothing3);
        items.add(clothing4);

        // Check that only items @category CLOTHING is filtered
        Assert.assertEquals(loc.filterCategories(ItemType.CLOTHING), items);
    }

    @Test
    public void testListWithManyDifferentCategoriesForHat() {
        // Add all items to the location
        loc.setItemInList(clothing1);
        loc.setItemInList(clothing2);
        loc.setItemInList(clothing3);
        loc.setItemInList(clothing4);
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
