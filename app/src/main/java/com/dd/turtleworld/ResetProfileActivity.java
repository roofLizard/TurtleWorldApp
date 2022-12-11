package com.dd.turtleworld;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

/**
 * The Reset profile screen of the app.
 * Allows the user to reset their learning progress, setting all topics and quizzes to incomplete
 * and removing all of their set learning goals.
 */
public class ResetProfileActivity extends AppCompatActivity {
    /**
     * Display the reset profile screen.
     * Explain to the user what is meant by resetting their profile.
     *
     * @param savedInstanceState    Data the activity may use to restore a previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_profile);

        // Create an OnClickListener to handle clicks of the buttons
        View.OnClickListener clickListener = new View.OnClickListener() {
            /**
             * Ensure a specific action happens when a button is clicked:
             * Reset: Reset the user's profile
             * Home: Go to home screen
             *
             * @param view  The button clicked
             */
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.button_reset) {
                    onResetClicked(view);
                } else if (view.getId() == R.id.button_home_from_reset) {
                    onHomeClicked(view);
                }
            }
        };
        // Initialise the buttons and assign them the OnClickListener
        Button resetButton = findViewById(R.id.button_reset);
        resetButton.setOnClickListener(clickListener);
        Button homeButton = findViewById(R.id.button_home_from_reset);
        homeButton.setOnClickListener(clickListener);
        // Add the toolbar to the layout and add the Up button to it
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Set up the options menu
     *
     * @param menu The menu object to be set up
     * @return The created options menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Move to a specific place after an item in the options menu is clicked
     * Home: Home screen
     * Explore: Explore screen
     * Goals: Goals screen
     * Reset profile: stay here
     *
     * @param item  The menu item clicked
     * @return      true for defined actions, call to super() for undefined action
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_home) {
            // Navigate to the home screen
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.action_explore) {
            // Navigate to the Explore screen
            Intent intent = new Intent(this, ExploreActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.action_goals) {
            // Navigate to the Goals screen
            Intent intent = new Intent(this, GoalsActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.action_reset_profile) {
            // Do nothing
            return true;
        } else {
            // User action was not recognized. Use the superclass to handle it.
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Reset the users profile in response to a button click.
     * Set all topics and quizzes to incomplete and delete all learning goals.
     * Set it so the next visit to the home screen is classed as the user's first.
     *
     * @param view The button clicked
     */
    private void onResetClicked(View view) {
        resetTopicEngagement();
        resetGoals();
        resetFirstUse();
        Toast toast = Toast.makeText(this, "Profile reset!", Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Set all topics and quizzes to incomplete
     */
    private void resetTopicEngagement() {
        TopicEngagementViewModel topicEngagementViewModel = new ViewModelProvider(this).get(TopicEngagementViewModel.class);
        List<TopicEngagement> allTopicEngagement = topicEngagementViewModel.getAllTopicEngagement();
        for (TopicEngagement topicEngagement : allTopicEngagement) {
            topicEngagement.setQuizComplete(false);
            topicEngagement.setTopicEngaged(false);
            topicEngagementViewModel.updateTopicEngagement(topicEngagement);
        }
    }

    /**
     * Delete all learning goals
     */
    private void resetGoals() {
        GoalViewModel goalViewModel = new ViewModelProvider(this).get(GoalViewModel.class);
        goalViewModel.deleteAllGoals();
    }

    /**
     * Set it so the next visit to the home screen is classed as the user's first
     */
    private void resetFirstUse() {
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("firstUse", true).apply();
    }

    /**
     * Navigate to the Home screen in response to a button click.
     *
     * @param view The button clicked
     */
    private void onHomeClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}