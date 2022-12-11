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
 * A class to test the UI of the AssessmentExcitementRatingActivity works as expected
 */
@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class AssessmentExcitementRatingActivityTest {
    /**
     * Launch the AssessmentExcitementRatingActivity for testing
     */
    @Rule
    public ActivityScenarioRule<AssessmentExcitementRatingActivity> assessmentExcitementRatingActivityActivityScenarioRule =
            new ActivityScenarioRule<>(AssessmentExcitementRatingActivity.class);

    /**
     * Test the 'Next' button appears after a user rates their excitement
     */
    @Test
    public void nextDisplays() {
        onView(withId(R.id.ratingBar_excitement)).perform(click());
        onView(withId(R.id.button_excitement_next)).check(matches(isDisplayed()));
    }

    /**
     * Test that clicking the 'Next' button starts the learning choices part of the assessment
     */
    @Test
    public void onNext() {
        onView(withId(R.id.ratingBar_excitement)).perform(click());
        onView(withId(R.id.button_excitement_next)).perform(click());
        onView(withId(R.id.radio_group_assessment_choices)).check(matches(isDisplayed()));
    }
}
