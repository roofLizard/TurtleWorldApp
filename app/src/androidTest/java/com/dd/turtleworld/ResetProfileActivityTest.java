package com.dd.turtleworld;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * A class to test the UI of the ResetProfileActivity works as expected
 */
@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class ResetProfileActivityTest {
    /**
     * Launch the ResetProfileActivity for testing
     */
    @Rule
    public ActivityScenarioRule<ResetProfileActivity> resetProfileActivityActivityScenarioRule =
            new ActivityScenarioRule<>(ResetProfileActivity.class);

    /**
     * Test that clicking the 'Home' button brings the user to the home screen
     */
    @Test
    public void onHome() {
        onView(withId(R.id.button_home_from_reset)).perform(click());
        onView(withId(R.id.image_main)).check(matches(isDisplayed()));
    }

    /**
     * Test that clicking the 'Home' button after resetting their profile
     * brings the user to the start of the readiness to learn assessment
     */
    @Test
    public void onHomeAfterReset() {
        onView(withId(R.id.button_reset)).perform(click());
        onView(withId(R.id.button_home_from_reset)).perform(click());
        onView(withId(R.id.button_get_started)).check(matches(isDisplayed()));
    }
}
