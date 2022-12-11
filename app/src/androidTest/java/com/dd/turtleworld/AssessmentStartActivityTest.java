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
 * A class to test the UI of the AssessmentStartActivity works as expected
 */
@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class AssessmentStartActivityTest {
    /**
     * Launch the AssessmentStartActivity for testing
     */
    @Rule
    public ActivityScenarioRule<AssessmentStartActivity> assessmentActivity1ActivityScenarioRule =
            new ActivityScenarioRule<>(AssessmentStartActivity.class);

    /**
     * Test that clicking the 'Get Started' button starts the excitement rating part of the assessment
     */
    @Test
    public void onGetStarted() {
        onView(withId(R.id.button_get_started)).perform(click());
        onView(withId(R.id.ratingBar_excitement)).check(matches(isDisplayed()));
    }
}
