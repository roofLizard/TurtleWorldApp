package com.dd.turtleworld;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Intent;

import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * A class to test the UI of the QuizStartActivity works as expected
 */
@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class QuizStartActivityTest {
    static Intent intent;
    static {
        intent = new Intent(getApplicationContext(), QuizStartActivity.class);
        intent.putExtra(QuizStartActivity.TOPIC_ID, 0);
    }
    /**
     * Launch the QuizStartActivity for testing
     */
    @Rule
    public ActivityScenarioRule<QuizStartActivity> startQuizActivityActivityScenarioRule =
            new ActivityScenarioRule<>(intent);

    /**
     * Test that clicking the 'Start quiz' button starts the quiz
     */
    @Test
    public void onStartQuiz() {
        onView(withId(R.id.button_start_quiz)).perform(click());
        onView(withId(R.id.quiz_question_text)).check(matches(isDisplayed()));
    }
}
