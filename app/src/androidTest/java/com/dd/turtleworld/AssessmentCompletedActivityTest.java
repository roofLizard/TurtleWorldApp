package com.dd.turtleworld;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Intent;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
/**
 * A class to test the UI of the AssessmentCompletedActivity works as expected
 */
@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class AssessmentCompletedActivityTest {
    static Intent intent;
    static {
        intent = new Intent(getApplicationContext(), AssessmentCompletedActivity.class);
        intent.putExtra(AssessmentCompletedActivity.EXCITEMENT, 1);
        intent.putExtra(AssessmentCompletedActivity.TOPIC_ID, 2);
    }
    /**
     * Launch the AssessmentCompletedActivity for testing
     */
    @Rule
    public ActivityScenarioRule<AssessmentCompletedActivity> assessmentCompletedActivityActivityScenarioRule =
            new ActivityScenarioRule<>(intent);

    /**
     * Test that clicking the recommended topic brings the user to the topic slides
     */
    @Test
    public void onRecommendation() {
        onView(withId(R.id.topics_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.topic_pager)).check(matches(isDisplayed()));
    }

    /**
     * Test the correct topic slides are displayed after clicking the recommended topic
     */
    @Test
    public void correctRecommendationSlideDisplays() {
        onView(withId(R.id.topics_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.slide_text)).check(matches(withText("All turtles hatch from eggs...")));
    }

    /**
     * Test that clicking the 'Home' button brings the user to the home screen
     */
    @Test
    public void onHome() {
        onView(withId(R.id.button_home_from_assessment)).perform(click());
        onView(withId(R.id.image_main)).check(matches(isDisplayed()));
    }
}
