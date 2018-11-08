package edu.gatech.cs2340.buzzTracker;
package edu.gatech.cs2340.buzzTracker.model.Location;

import com.google.firebase.database.DataSnapshot;


import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import edu.gatech.cs2340.buzzTracker.controllers.DataItemListActivity;
import edu.gatech.cs2340.buzzTracker.controllers.LocationListActivity;
import edu.gatech.cs2340.buzzTracker.model.Location;

import static junit.framework.TestCase.assertEquals;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.cs2340.buzzTracker.controllers.AddCategoryActivity;
import edu.gatech.cs2340.buzzTracker.model.ItemType;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
/**
 * Created by robertwaters on 3/16/16.
 */
public class ModelTest {
    private Location l;
    private Location l2;
    private LocationListActivity.SimpleItemRecyclerViewAdapter adapter;


    @Before
    public void setUp() {
        l = new Location(1, "test", 100.0, 100.0, "101 First St.", "Atlanta", "GA", "30332", "donation center",
                "4041234567", "testsite.com");
        l2 = new Location(1, "test1", 101.0, 101.0, "100 First St.", "Atlant", "GJ", "38332", "donation center",
                        "4041234568", "testsits.com");
        List<Location> locations = new LinkedList<>();
        adapter = new LocationListActivity().SimpleItemRecyclerViewAdapter(locations);
    }

    @Test
    public void testDatabase() {
        DataSnapshot db = mock(DataSnapshot.class);
        DataSnapshot db2 = mock(DataSnapshot.class);
        DataSnapshot db3 = mock(DataSnapshot.class);
        List<DataSnapshot> snaps = new LinkedList<>();
        snaps.add(db2);
        snaps.add(db3);
        when(db.getChildren()).thenReturn(snaps);
        when(db2.getValue(Location.class)).thenReturn(l);
        when(db3.getValue(Location.class)).thenReturn(l2);


        onView(withId())
        getLocationDataToUpdateView(db);

        assertEquals(l, locations.get(0));
        assertEquals(l2, locations.get(1));
        assertEquals(2, locations.size());
    }
}
