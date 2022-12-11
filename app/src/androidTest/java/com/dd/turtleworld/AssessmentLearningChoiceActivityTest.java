package com.dd.turtleworld;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
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
 * A class to test the UI of the AssessmentLearningChoiceActivity works as expected
 */
@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class AssessmentLearningChoiceActivityTest {
    static Intent intent;
    static {
        intent = new Intent(getApplicationContext(), AssessmentLearningChoiceActivity.class);
        intent.putExtra(AssessmentLearningChoiceActivity.EXCITEMENT, 5);
    }

    /**
     * Launch the AssessmentLearningChoiceActivity for testing
     */
    @Rule
    public ActivityScenarioRule<AssessmentLearningChoiceActivity> assessmentLearningChoiceActivityActivityScenarioRule =
            new ActivityScenarioRule<>(intent);

    /**
     * Test the 'Next' button appears after a user chooses a question they'd like to learn about
     */
    @Test
    public void nextDisplays() {
        onView(withId(R.id.radio_button_1)).perform(click());
        onView(withId(R.id.button_assessment_choices_next)).check(matches(isDisplayed()));
    }

    /**
     * Test that clicking the 'Next' button starts the final part of the assessment
     */
    @Test
    public void onNext() {
        onView(withId(R.id.radio_button_1)).perform(click());
        onView(withId(R.id.button_assessment_choices_next)).perform(click());
        onView(withId(R.id.fragment_container_recommendation)).check(matches(isDisplayed()));
    }

    /**
     * Test that the corrected recommended topic is displayed
     * for the user's choice of question they'd like to learn about
     */
    @Test
    public void correctRecommendationDisplays() {
        onView(withId(R.id.radio_button_1)).perform(click());
        onView(withId(R.id.button_assessment_choices_next)).perform(click());
        onView(withId(R.id.card_title)).check(matches(withText("How do turtles live?")));
    }

    /**
     * Test that the correct message is displayed for the user's
     * assessment of their excitement levels
     */
    @Test
    public void correctExcitementDisplays() {
        onView(withId(R.id.radio_button_1)).perform(click());
        onView(withId(R.id.button_assessment_choices_next)).perform(click());
        onView(withId(R.id.excitement_message_text)).check(matches(withText("That's great you're excited!")));
    }
}
