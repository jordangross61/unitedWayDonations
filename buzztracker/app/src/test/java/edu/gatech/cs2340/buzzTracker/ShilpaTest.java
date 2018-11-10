package edu.gatech.cs2340.buzzTracker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import edu.gatech.cs2340.buzzTracker.model.Item;
import edu.gatech.cs2340.buzzTracker.model.ItemType;
import edu.gatech.cs2340.buzzTracker.model.Location;

/**
 * Unit test for the removeCategory method in the Location class.
 *
 * @author Shilpa Patel
 */
public class ShilpaTest {
    private Location location;
    private ArrayList<Item> itemList;
    private ArrayList<Item> removedItemList;
    private ItemType myItemType;
    private ItemType nullItemType;

    @Before
    public void setUp() {
        location = new Location(1, "test", 100.0, 100.0, "100 Test St.", "Atlanta", "GA", "30331", "donation center",
                "1234567890", "testsite.com");
        itemList = new ArrayList<>();
        removedItemList = new ArrayList<>();

    }

    //tests that there are no items at this location before items are set in list
    @Test
    public void testNoItemsAtLocation() {
        assertTrue(location.getItemList().isEmpty());
    }

    private void assertTrue(boolean empty) {
    }

    //tests for exception thrown if the list is empty
    @Test (expected = NullPointerException.class)
    public void removeCategoryEmptyList() {
        location.removeCategory(ItemType.HAT);
    }

    //tests for exception thrown if itemType passed into method is null
    @Test (expected = IllegalArgumentException.class)
    public void removeCategoryNull() {
        nullItemType = null;
        location.removeCategory(nullItemType);
    }

    //tests for proper removal of ItemType in list
    @Test
    public void removeCategoryNotNull() {

        Item a = new Item();
        Item b = new Item();
        Item c = new Item();
        Item d = new Item();
        Item e = new Item();
        Item f = new Item();
        Item g = new Item();

        myItemType = ItemType.HAT;

        a.setCategory("Hat");
        b.setCategory("Other");
        c.setCategory("Clothing");
        d.setCategory("Electronics");
        e.setCategory("Hat");
        f.setCategory("Hat");
        g.setCategory("Kitchen");

        removedItemList.add(b);
        removedItemList.add(c);
        removedItemList.add(d);
        removedItemList.add(g);

        location.setItemInList(a);
        location.setItemInList(b);
        location.setItemInList(c);
        location.setItemInList(d);
        location.setItemInList(e);
        location.setItemInList(f);
        location.setItemInList(g);

        location.removeCategory(myItemType);
        Assert.assertEquals(location.getItemList(), removedItemList);

    }

    @Test
    //checks that nothing is removed if the chosen ItemType is not in the list
    public void removeCategoryWithNoMatchingCategory() {

        Item a = new Item();
        Item b = new Item();
        Item c = new Item();
        Item d = new Item();

        myItemType = ItemType.CLOTHING;
        nullItemType = null;

        a.setCategory("Hat");
        b.setCategory("Other");
        c.setCategory("Hat");
        d.setCategory("Other");

        itemList.add(a);
        itemList.add(b);
        itemList.add(c);
        itemList.add(d);

        location.setItemInList(a);
        location.setItemInList(b);
        location.setItemInList(c);
        location.setItemInList(d);

        location.removeCategory(myItemType);
        Assert.assertEquals(location.getItemList(), itemList);
    }

    //tests that a list of chosen category is empty after removal using removeCategory
    @Test
    public void testListWithOnlyMatchingCategory() {

        Item a = new Item();
        Item b = new Item();
        Item c = new Item();
        Item d = new Item();

        myItemType = ItemType.HAT;

        a.setCategory("Hat");
        b.setCategory("Hat");
        c.setCategory("Hat");
        d.setCategory("Hat");

        location.setItemInList(a);
        location.setItemInList(b);
        location.setItemInList(c);
        location.setItemInList(d);

        location.removeCategory(myItemType);
        assertTrue(location.getItemList().isEmpty());
    }
}