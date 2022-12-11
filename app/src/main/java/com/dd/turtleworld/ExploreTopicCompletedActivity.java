package com.dd.turtleworld;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * The screen a user sees after they've completed the slides for a particular topic.
 * Congratulates the user on completing the topic and prompts them to take a quiz
 * to evaluate their learning.
 *
 * @author  Declan Duffy
 * @version 2022.08.08
 */

public class ExploreTopicCompletedActivity extends AppCompatActivity {
    /**
     * The id of the topic the user has completed
     */
    public static final String TOPIC_ID = "topic_id";
    private int topicId;

    /**
     * Display the Topic completed screen.
     * Display a message congratulating the user on completing the topic, and the topic's image.
     *
     * @param savedInstanceState    Data the activity may use to restore a previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_topic_completed);
        // Get the id of the completed topic and use it to set the congratulations message and image
        topicId = (Integer) getIntent().getExtras().get(TOPIC_ID);
        displayTopicCompleteMessageAndImage(topicId);

        // Create an OnClickListener to handle clicks of the buttons
        View.OnClickListener clickListener = new View.OnClickListener() {
            /**
             * Ensure a specific action happens when each button is clicked:
             * Start quiz: Go to start quiz screen
             * Home: Go to home screen
             *
             * @param view  The button clicked
             */
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.button_take_quiz) {
                    onTakeQuizClicked(view);
                }
                else if (view.getId() == R.id.button_home_from_topic_completed) {
                    onHomeClicked(view);
                }
            }
        };
        // Initialise the buttons and assign them the OnClickListener
        Button startQuizButton = findViewById(R.id.button_take_quiz);
        startQuizButton.setOnClickListener(clickListener);
        Button homeButton = findViewById(R.id.button_home_from_topic_completed);
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
     * @param menu  The menu object to be set up
     * @return      The created options menu
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
     * Reset profile: Reset profile screen
     * home(or Up): Slides for the completed topic
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
            // Navigate to the Reset profile screen
            Intent intent = new Intent(this, ResetProfileActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == android.R.id.home) {
            // Navigate back to the topic slides
            Intent intent = new Intent(this, ExploreTopicActivity.class);
            intent.putExtra(ExploreTopicActivity.TOPIC_ID, topicId);
            startActivity(intent);
            return true;
        } else {
            // User action was not recognized. Use the superclass to handle it.
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Display the message of congratulations for completing the topic, and the topic's image
     *
     * @param topicId   The id of the topic the user has completed
     */
    public void displayTopicCompleteMessageAndImage(int topicId) {
        TextView textView = findViewById(R.id.topic_complete_message);
        String message = "You've discovered " + Topic.topics[topicId].getSummary() + "!";
        textView.setText(message);
        Drawable drawable;
        drawable = ContextCompat.getDrawable(this, Topic.topics[topicId].getImageId());
        ImageView imageView = findViewById(R.id.topic_complete_image);
        imageView.setImageDrawable(drawable);
    }

    /**
     * Start the quiz after a button click.
     *
     * @param view  The button clicked
     */
    public void onTakeQuizClicked(View view) {
        Intent intent = new Intent(this, QuizStartActivity.class);
        intent.putExtra(QuizStartActivity.TOPIC_ID, topicId);
        startActivity(intent);
    }

    /**
     * Navigate to the Home screen after a button click.
     *
     * @param view  The button clicked
     */
    public void onHomeClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}