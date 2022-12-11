package com.dd.turtleworld;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
/**
 * A class to test the toolbar action menu works as expected
 */
@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class ToolbarActionMenuTest {

    /**
     * Launch the GoalSetActivity for testing, as this activity is not accessible from the action menu
     */
    @Rule
    public ActivityScenarioRule<GoalSetActivity> goalSetActivityActivityScenarioRule =
            new ActivityScenarioRule<>(GoalSetActivity.class);

    /**
     * Test that clicking the 'Home' action brings the user to the home screen
     */
    @Test
    public void onHome() {
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());
        onView(withText("Home")).perform(click());
        onView(withId(R.id.image_main)).check(matches(isDisplayed()));
    }

    /**
     * Test that clicking the 'Explore' action brings the user to the explore screen
     */
    @Test
    public void onExplore() {
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());
        onView(withText("Explore")).perform(click());
        onView(withId(R.id.fragment_container_topics)).check(matches(isDisplayed()));
    }

    /**
     * Test that clicking the 'Goals' action brings the user to the goals screen
     */
    @Test
    public void onGoals() {
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());
        onView(withText("Goals")).perform(click());
        onView(withId(R.id.fragment_container_goals)).check(matches(isDisplayed()));
    }

    /**
     * Test that clicking the 'Reset profile' action brings the user to the reset profile screen
     */
    @Test
    public void onReset() {
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());
        onView(withText("Reset profile")).perform(click());
        onView(withId(R.id.button_reset)).check(matches(isDisplayed()));
    }
}