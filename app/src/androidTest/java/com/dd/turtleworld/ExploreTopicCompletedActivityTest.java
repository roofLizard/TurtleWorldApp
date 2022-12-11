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
 * A class to test the UI of the ExploreTopicCompletedActivity works as expected
 */
@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class ExploreTopicCompletedActivityTest {
    static Intent intent;
    static {
        intent = new Intent(getApplicationContext(), ExploreTopicCompletedActivity.class);
        intent.putExtra(ExploreTopicCompletedActivity.TOPIC_ID, 0);
    }

    /**
     * Launch the ExploreTopicCompletedActivity for testing
     */
    @Rule
    public ActivityScenarioRule<ExploreTopicCompletedActivity> topicCompletedActivityActivityScenarioRule =
            new ActivityScenarioRule<>(intent);

    /**
     * Test that clicking the 'Take quiz' button brings the user to the start quiz screen
     */
    @Test
    public void onTakeQuiz() {
        onView(withId(R.id.button_take_quiz)).perform(click());
        onView(withId(R.id.quiz_title)).check(matches(isDisplayed()));
    }

    /**
     * Test that clicking the 'Home' button brings the user to the home screen
     */
    @Test
    public void onHome() {
        onView(withId(R.id.button_home_from_topic_completed)).perform(click());
        onView(withId(R.id.image_main)).check(matches(isDisplayed()));
    }

    /**
     * Test that the correct quiz title is displayed after choosing to take the quiz
     */
    @Test
    public void correctQuizTitleDisplayed() {
        onView(withId(R.id.button_take_quiz)).perform(click());
        onView(withId(R.id.quiz_title)).check(matches(withText("What makes a turtle?")));
    }
}
