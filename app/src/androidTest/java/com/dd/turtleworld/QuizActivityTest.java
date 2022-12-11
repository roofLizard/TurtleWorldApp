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
 * A class to test the UI of the QuizActivity works as expected
 */
@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class QuizActivityTest {
    static Intent intent;
    static {
        intent = new Intent(getApplicationContext(), QuizActivity.class);
        intent.putExtra(QuizActivity.TOPIC_ID, 0);
    }
    /**
     * Launch the QuizActivity for testing
     */
    @Rule
    public ActivityScenarioRule<QuizActivity> quizActivityActivityScenarioRule =
            new ActivityScenarioRule<>(intent);

    /**
     * Test the 'Next' button appears after a user clicks an answer
     */
    @Test
    public void nextButtonAppears() {
        onView(withId(R.id.quiz_button_1)).perform(click());
        onView(withId(R.id.quiz_next_button)).check(matches(isDisplayed()));
    }

    /**
     * Test the 'Finish' button appears after a user answers the final question
     */
    @Test
    public void finishButtonAppears() {
        onView(withId(R.id.quiz_button_1)).perform(click());
        onView(withId(R.id.quiz_next_button)).perform(click());
        onView(withId(R.id.quiz_button_2)).perform(click());
        onView(withId(R.id.quiz_next_button)).perform(click());
        onView(withId(R.id.quiz_button_3)).perform(click());
        onView(withId(R.id.quiz_next_button)).check(matches(withText("Finish")));
    }

    /**
     * Test that clicking the 'Finish' button brings the user to the quiz completed screen
     */
    @Test
    public void onFinish() {
        onView(withId(R.id.quiz_button_1)).perform(click());
        onView(withId(R.id.quiz_next_button)).perform(click());
        onView(withId(R.id.quiz_button_2)).perform(click());
        onView(withId(R.id.quiz_next_button)).perform(click());
        onView(withId(R.id.quiz_button_3)).perform(click());
        onView(withId(R.id.quiz_next_button)).perform(click());
        onView(withId(R.id.quiz_result_message)).check(matches(isDisplayed()));
    }

    /**
     * Test that the correct result message is displayed if a user completes the quiz
     */
    @Test
    public void correctResultDisplaysQuizCompleted() {
        onView(withText("Shells")).perform(click());
        onView(withId(R.id.quiz_next_button)).perform(click());
        onView(withText("Terrapin")).perform(click());
        onView(withId(R.id.quiz_next_button)).perform(click());
        onView(withText("Reptiles")).perform(click());
        onView(withId(R.id.quiz_next_button)).perform(click());
        onView(withId(R.id.quiz_result_message)).check(matches(withText("Well done!")));
    }

    /**
     * Test that the correct result message is displayed if a user does not complete the quiz
     */
    @Test
    public void correctResultDisplaysQuizNotCompleted() {
        onView(withText("Toes")).perform(click());
        onView(withId(R.id.quiz_next_button)).perform(click());
        onView(withText("Terrapin")).perform(click());
        onView(withId(R.id.quiz_next_button)).perform(click());
        onView(withText("Reptiles")).perform(click());
        onView(withId(R.id.quiz_next_button)).perform(click());
        onView(withId(R.id.quiz_result_message)).check(matches(withText("Better luck next time!")));
    }

    /**
     * Test that the correct score is displayed when a user finishes a quiz
     */
    @Test
    public void correctScoreDisplays() {
        onView(withText("Toes")).perform(click());
        onView(withId(R.id.quiz_next_button)).perform(click());
        onView(withText("Terrapin")).perform(click());
        onView(withId(R.id.quiz_next_button)).perform(click());
        onView(withText("Reptiles")).perform(click());
        onView(withId(R.id.quiz_next_button)).perform(click());
        onView(withId(R.id.quiz_complete_score)).check(matches(withText("2/3")));
    }
}
