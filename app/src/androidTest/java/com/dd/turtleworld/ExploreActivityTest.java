package com.dd.turtleworld;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * A class to test the UI of the ExploreActivity works as expected
 */
@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class ExploreActivityTest {
    /**
     * Launch the ExploreActivity for testing
     */
    @Rule
    public ActivityScenarioRule<ExploreActivity> exploreActivityActivityScenarioRule =
            new ActivityScenarioRule<>(ExploreActivity.class);

    /**
     * Test that clicking a topic brings the user to the topic slides
     */
    @Test
    public void onTopicChosen() {
        onView(withId(R.id.topics_recycler)).perform(RecyclerViewActions.
                actionOnItemAtPosition(0, click()));
        onView(withId(R.id.topic_pager)).check(matches(isDisplayed()));
    }

    /**
     * Test the correct topic slides are displayed after choosing the 'What makes a turtle?' topic
     */
    @Test
    public void onTopic0Chosen() {
        onView(withId(R.id.topics_recycler)).perform(RecyclerViewActions.
                actionOnItemAtPosition(0, click()));
        onView(withId(R.id.slide_text)).
                check(matches(withText("Turtles are an amazing group of reptiles...")));
    }

    /**
     * Test the correct topic slides are displayed after choosing the 'What do turtles eat?' topic
     */
    @Test
    public void onTopic1Chosen() {
        onView(withId(R.id.topics_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.slide_text)).check(matches(withText("Different turtles eat all sorts of food...")));
    }

    /**
     * Test the correct topic slides are displayed after choosing the 'How do turtles live?' topic
     */
    @Test
    public void onTopic2Chosen() {
        onView(withId(R.id.topics_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.slide_text)).check(matches(withText("All turtles hatch from eggs...")));
    }
}

