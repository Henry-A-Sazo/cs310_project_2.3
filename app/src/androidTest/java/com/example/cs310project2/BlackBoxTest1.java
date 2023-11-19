package com.example.cs310project2;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import android.content.Intent;
import android.view.View;
import android.widget.*;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class BlackBoxTest1 {

    public static final String USERNAME = "cksubram";
    public static final String FRIEND_TO_ADD = "hsazo";
    public static final String FRIEND_TO_REMOVE = "caddei";

    @Test
    public void testAddingFriendUpdatesFriendList() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), friends.class);
        intent.putExtra("user", USERNAME);
        ActivityScenario<friends> activityScenario = ActivityScenario.launch(intent);
        // Check the initial state of the friend list
        int initialFriendCount = getCountOfButtonsWithText(R.id.friends, "Add");

        // Add a new friend
        onView(allOf(withText("Add"), withTagValue(is((Object) FRIEND_TO_ADD))))
                .perform(click());

        // Wait for a short period to ensure the friend list is updated
        try {
            Thread.sleep(1000); // 1 second sleep; adjust as necessary
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if the friend list is updated
        int updatedFriendCount = getCountOfButtonsWithText(R.id.friends, "Add");
        assertThat(updatedFriendCount, is(initialFriendCount - 1));
    }

    @Test
    public void testRemovingFriendUpdatesFriendList() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), friends.class);
        intent.putExtra("user", USERNAME);
        ActivityScenario<friends> activityScenario = ActivityScenario.launch(intent);
        // Check the initial state of the friend list
        int initialFriendCount = getCountOfButtonsWithText(R.id.friends, "Remove");

        // Add a new friend
        onView(allOf(withText("Remove"), withTagValue(is((Object) FRIEND_TO_REMOVE))))
                .perform(click());

        // Wait for a short period to ensure the friend list is updated
        try {
            Thread.sleep(1000); // 1 second sleep; adjust as necessary
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if the friend list is updated
        int updatedFriendCount = getCountOfButtonsWithText(R.id.friends, "Remove");
        assertThat(updatedFriendCount, is(initialFriendCount - 1));
    }

    // Utility method to count items in a LinearLayout
    // Utility method to count buttons with a specific text in a LinearLayout
    private int getCountOfButtonsWithText(int linearLayoutId, String buttonText) {
        final int[] count = {0};
        onView(withId(linearLayoutId)).check(new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException noViewFoundException) {
                if (noViewFoundException != null) {
                    throw noViewFoundException;
                }
                LinearLayout linearLayout = (LinearLayout) view;
                for (int i = 0; i < linearLayout.getChildCount(); i++) {
                    View child = linearLayout.getChildAt(i);
                    if (child instanceof Button && ((Button) child).getText().equals(buttonText)) {
                        count[0]++;
                    }
                }
            }
        });
        return count[0];
    }
}