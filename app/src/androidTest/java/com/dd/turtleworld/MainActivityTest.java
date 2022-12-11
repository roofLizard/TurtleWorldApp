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
 * A class to test the UI of the MainActivity works as expected
 */
@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class MainActivityTest {
    /**
     * Launch the MainActivity for testing
     */
    @Rule
    public ActivityScenarioRule<MainActivity> activityActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Test that clicking the 'Explore' button brings the user to the explore screen
     */
    @Test
    public void onExplore() {
        onView(withId(R.id.button_explore)).perform(click());
        onView(withId(R.id.fragment_container_topics)).check(matches(isDisplayed()));
    }

    /**
     * Test that clicking the 'Goals' button brings the user to the goals screen
     */
    @Test
    public void onGoals() {
        onView(withId(R.id.button_goals)).perform(click());
        onView(withId(R.id.fragment_container_goals)).check(matches(isDisplayed()));
    }

}
