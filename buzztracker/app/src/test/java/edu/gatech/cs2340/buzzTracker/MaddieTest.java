package edu.gatech.cs2340.buzzTracker;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.buzzTracker.model.Item;
import edu.gatech.cs2340.buzzTracker.model.Size;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;


/**
 * Unit test for the setSize method in the Item class. Items that are not of the category "Clothing" should
 * have a null size
 * @author Maddie Brickell
 */
public class MaddieTest {

    private Item clothingItem;
    private Item nonClothingItem;

    @Before
    public void setUp() {
        clothingItem = new Item(null, "Shirt", "A very fluffy shirt",
        23, "Clothing", "", 1, Size.S);
        nonClothingItem = new Item(null, "Hat", "A very fluffy hat",
                5, "Hat", "", 1, Size.S);
    }

    @Test
    public void clothingContainsSize() {
        assertNotNull(clothingItem.getSize());
    }

    @Test
    public void nonClothingDoesNotContainSize() {
        assertNull(nonClothingItem.getSize());
    }
}
