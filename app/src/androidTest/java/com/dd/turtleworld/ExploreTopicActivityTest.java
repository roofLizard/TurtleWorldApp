package com.dd.turtleworld;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Intent;

import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
/**
 * A class to test the UI of the ExploreTopicActivity works as expected
 */
@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class ExploreTopicActivityTest {
    static Intent intent;
    static {
        intent = new Intent(getApplicationContext(), ExploreTopicActivity.class);
        intent.putExtra(ExploreTopicActivity.TOPIC_ID, 0);
    }
    /**
     * Launch the ExploreTopicActivity for testing
     */
    @Rule
    public ActivityScenarioRule<ExploreTopicActivity> topicActivityActivityScenarioRule =
            new ActivityScenarioRule<>(intent);

    /**
     * Test that the 'Complete topic' button appears once a user has swiped to the last slide
     */
    @Test
    public void topicCompleteButtonDisplays() {
        onView(withId(R.id.topic_pager)).perform(swipeLeft());
        onView(withId(R.id.topic_pager)).perform(swipeLeft());
        onView(withId(R.id.topic_pager)).perform(swipeLeft());
        onView(withId(R.id.topic_pager)).perform(swipeLeft());
        onView(withId(R.id.topic_pager)).perform(swipeLeft());
        onView(withId(R.id.topic_pager)).perform(swipeLeft());
        onView(withId(R.id.fab_complete_topic)).check(matches(isDisplayed()));
    }

    /**
     * Test that clicking the 'Complete topic' button brings the user to the topic completed screen
     */
    @Test
    public void onTopicCompleted() {
        onView(withId(R.id.topic_pager)).perform(swipeLeft());
        onView(withId(R.id.topic_pager)).perform(swipeLeft());
        onView(withId(R.id.topic_pager)).perform(swipeLeft());
        onView(withId(R.id.topic_pager)).perform(swipeLeft());
        onView(withId(R.id.topic_pager)).perform(swipeLeft());
        onView(withId(R.id.topic_pager)).perform(swipeLeft());
        onView(withId(R.id.fab_complete_topic)).perform(click());
        onView(withId(R.id.topic_complete_message)).check(matches(isDisplayed()));
    }

    /**
     * Test that the correct topic title is displayed after completing the topic
     */
    @Test
    public void correctCompletedTopicDisplays() {
        onView(withId(R.id.topic_pager)).perform(swipeLeft());
        onView(withId(R.id.topic_pager)).perform(swipeLeft());
        onView(withId(R.id.topic_pager)).perform(swipeLeft());
        onView(withId(R.id.topic_pager)).perform(swipeLeft());
        onView(withId(R.id.topic_pager)).perform(swipeLeft());
        onView(withId(R.id.topic_pager)).perform(swipeLeft());
        onView(withId(R.id.fab_complete_topic)).perform(click());
        onView(withId(R.id.topic_complete_message)).check(matches(withText("You've discovered what makes a turtle!")));
    }


}
