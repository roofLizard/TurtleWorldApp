package com.dd.turtleworld;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The screen a user sees after they've completed a quiz.
 * Displays their score in the quiz and either congratulates them on completing it
 * and tells them better luck next time.
 * Prompts the user to retry the quiz or return to the home screen.
 *
 * @author Declan Duffy
 * @version 2022.08.08
 */
public class QuizCompletedActivity extends AppCompatActivity {
    /**
     * The id of the topic the user has completed a quiz on
     */
    public static final String TOPIC_ID = "topic_id";
    /**
     * The user's score in the quiz they've just completed
     */
    public static final String FINAL_SCORE = "final_score";

    private int topicId;
    private TopicEngagementViewModel topicEngagementViewModel;

    /**
     * Display the Quiz completed screen.
     * Display the user's score, and a message letting them know if they completed the quiz or not.
     *
     * @param savedInstanceState Data the activity may use to restore a previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_completed);
        // Get the id of the topic the quiz was about, and the user's score from the quiz
        this.topicId = (Integer) getIntent().getExtras().get(TOPIC_ID);
        int finalScore = (Integer) getIntent().getExtras().get(FINAL_SCORE);
        // Get a reference to the database
        topicEngagementViewModel = new ViewModelProvider(this).get(TopicEngagementViewModel.class);

        // Check if the user completed the quiz or not
        // Update the database if they have completed it
        boolean quizComplete;
        if (finalScore == 3) {
            quizComplete = true;
            updateTopicEngagement(topicId);
        } else {
            quizComplete = false;
        }
        // Display whether the quiz has been completed or not
        displayResultMessage(quizComplete);
        // Display the user's score from the quiz
        displayResult(topicId, finalScore);

        // Create an OnClickListener to handle clicks of the buttons
        View.OnClickListener clickListener = new View.OnClickListener() {
            /**
             * Ensure a specific actions happens when a button is clicked:
             * Retry quiz: Start the quiz again
             * Home: Go to home screen
             *
             * @param view  The button clicked
             */
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.button_retry_quiz) {
                    onRetryQuizPressed(view);
                } else if (view.getId() == R.id.button_home_from_quiz_completed) {
                    onHomePressed(view);
                }
            }
        };
        // Initialise the buttons and assign them the OnClickListener
        Button retryQuizButton = findViewById(R.id.button_retry_quiz);
        retryQuizButton.setOnClickListener(clickListener);
        Button homeButton = findViewById(R.id.button_home_from_quiz_completed);
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
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Move to a specific place after an item in the options menu is clicked
     * Home: Home screen
     * Explore: Explore screen
     * Goals: Goals screen
     * Reset profile: Reset profile screen
     * home(or Up): Start quiz screen
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
            // Navigate back to the Start quiz screen
            Intent intent = new Intent(this, QuizStartActivity.class);
            intent.putExtra(QuizStartActivity.TOPIC_ID, topicId);
            startActivity(intent);
            return true;
        } else {
            // User action was not recognized. Use the superclass to handle it.
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Update the application database to show the user has completed the topic's quiz.
     *
     * @param topicId The id of topic the user has engaged with
     */
    public void updateTopicEngagement(int topicId) {
        TopicEngagement topicEngagement = topicEngagementViewModel.getTopicEngagement(topicId);
        // Only update the database if this is the first time the user has completed the quiz
        if (!(topicEngagement.getQuizComplete())) {
            topicEngagement.setQuizComplete(true);
            topicEngagementViewModel.updateTopicEngagement(topicEngagement);
        }
    }

    /**
     * Display the user's final score in the quiz.
     * Display their score in comparison to the total possible score.
     *
     * @param topicId    The id of the topic the quiz was about
     * @param finalScore The user's score in the quiz
     */
    private void displayResult(int topicId, int finalScore) {
        String result = "" + finalScore + "/" + Topic.topics[topicId].getQuestions().length;
        TextView scoreTextView = findViewById(R.id.quiz_complete_score);
        scoreTextView.setText(result);
    }

    /**
     * Display a message letting the user know if they have completed the quiz or not.
     * Display a starfish image if they have completed it, and an octopus if they have not.
     *
     * @param result Whether the user has completed the quiz or not
     */
    public void displayResultMessage(boolean result) {
        TextView resultTextView = findViewById(R.id.quiz_result_message);
        ImageView resultImageView = findViewById(R.id.quiz_result_image);
        Drawable drawable;
        if (result) {
            resultTextView.setText(R.string.well_done);
            drawable = ContextCompat.getDrawable(this, R.drawable.ic_icon_starfish_color);
            resultImageView.setImageDrawable(drawable);
        } else {
            resultTextView.setText(R.string.better_luck_next_time);
            drawable = ContextCompat.getDrawable(this, R.drawable.ic_icon_octopus_color);
            resultImageView.setImageDrawable(drawable);
        }
    }

    /**
     * Restart the quiz in response to a button click.
     *
     * @param view The button clicked
     */
    private void onRetryQuizPressed(View view) {
        Intent intent = new Intent(this, QuizStartActivity.class);
        intent.putExtra(QuizStartActivity.TOPIC_ID, topicId);
        startActivity(intent);
    }

    /**
     * Navigate to the home screen in response to a button click.
     *
     * @param view The button clicked
     */
    private void onHomePressed(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}