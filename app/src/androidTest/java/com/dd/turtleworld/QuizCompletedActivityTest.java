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
 * A class to test the UI of the QuizCompletedActivity works as expected
 */
@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class QuizCompletedActivityTest {
    static Intent intent;
    static {
        intent = new Intent(getApplicationContext(), QuizCompletedActivity.class);
        intent.putExtra(QuizCompletedActivity.TOPIC_ID, 0);
        intent.putExtra(QuizCompletedActivity.FINAL_SCORE, 1);
    }
    /**
     * Launch the QuizCompletedActivity for testing
     */
    @Rule
    public ActivityScenarioRule<QuizCompletedActivity> quizCompletedActivityActivityScenarioRule =
            new ActivityScenarioRule<>(intent);

    /**
     * Test that clicking the 'Retry Quiz' button brings the user to the start quiz screen
     */
    @Test
    public void onRetryQuiz() {
        onView(withId(R.id.button_retry_quiz)).perform(click());
        onView(withId(R.id.quiz_title)).check(matches(isDisplayed()));
    }

    /**
     * Test that clicking the 'Home' button brings the user to the home screen
     */
    @Test
    public void onHome() {
        onView(withId(R.id.button_home_from_quiz_completed)).perform(click());
        onView(withId(R.id.image_main)).check(matches(isDisplayed()));
    }

}
