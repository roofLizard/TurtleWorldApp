package com.dd.turtleworld;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * A class to test the UI of the GoalSetActivity works as expected
 */
@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class GoalSetActivityTest {
    /**
     * Launch the GoalSetActivity for testing
     */
    @Rule
    public ActivityScenarioRule<GoalSetActivity> goalSetActivityActivityScenarioRule =
            new ActivityScenarioRule<>(GoalSetActivity.class);

    /**
     * Test that choosing a goal type causes the set target menu to appear
     */
    @Test
    public void targetsDisplay() {
        onView(withId(R.id.spinner_choose_goal)).perform(click());
        onView(withText("Complete a number of quizzes")).perform(click());
        onView(withId(R.id.spinner_set_goal_target)).check(matches(isDisplayed()));
    }

    /**
     * Test that the title of the user's new goal appears once they have chosen a goal type and target
     */
    @Test
    public void goalTitleDisplays() {
        onView(withId(R.id.spinner_choose_goal)).perform(click());
        onView(withText("Complete a number of quizzes")).perform(click());
        onView(withId(R.id.spinner_set_goal_target)).perform(click());
        onView(withText("1")).perform(click());
        onView(withId(R.id.goal_title_text)).check(matches(isDisplayed()));
    }

    /**
     * Test that the correct goal title displays once a user has chosen a goal type and target
     */
    @Test
    public void goalTitleDisplaysCorrect() {
        String correctGoalTitle = "Complete 2 quizzes";
        onView(withId(R.id.spinner_choose_goal)).perform(click());
        onView(withText("Complete a number of quizzes")).perform(click());
        onView(withId(R.id.spinner_set_goal_target)).perform(click());
        onView(withText("2")).perform(click());
        onView(withId(R.id.goal_title_text)).check(matches(withText(correctGoalTitle)));
    }

    /**
     * Test that the 'Set goal' button appears once a user has chosen a goal type and target
     */
    @Test
    public void goalSetButtonDisplays() {
        onView(withId(R.id.spinner_choose_goal)).perform(click());
        onView(withText("Complete a number of quizzes")).perform(click());
        onView(withId(R.id.spinner_set_goal_target)).perform(click());
        onView(withText("1")).perform(click());
        onView(withId(R.id.fab_set_goal)).check(matches(isDisplayed()));
    }

    /**
     * Test that clicking the 'Set goal' button brings a user to the goals screen
     */
    @Test
    public void onGoalSet() {
        onView(withId(R.id.spinner_choose_goal)).perform(click());
        onView(withText("Complete a number of quizzes")).perform(click());
        onView(withId(R.id.spinner_set_goal_target)).perform(click());
        onView(withText("3")).perform(click());
        onView(withId(R.id.fab_set_goal)).perform(click());
        onView(withId(R.id.fragment_container_goals)).check(matches(isDisplayed()));
    }
}
