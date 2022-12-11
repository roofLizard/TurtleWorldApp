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
 * A class to test the UI of the GoalsActivity works as expected
 */
@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class GoalsActivityTest {
    /**
     * Launch the GoalsActivity for testing
     */
    @Rule
    public ActivityScenarioRule<GoalsActivity> goalsActivityActivityScenarioRule =
            new ActivityScenarioRule<>(GoalsActivity.class);

    /**
     * Test that clicking the 'Add goal' button brings the user to the set goal screen
     */
    @Test
    public void onAddGoal() {
        onView(withId(R.id.fab_add_goal)).perform(click());
        onView(withId(R.id.new_goal_title)).check(matches(isDisplayed()));
    }
}
