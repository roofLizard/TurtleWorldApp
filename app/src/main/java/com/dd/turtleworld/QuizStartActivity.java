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
 * The beginning of a quiz to evaluate a user's learning on a specific topic.
 * Explains the conditions for completing the quiz and prompts the user to start it.
 *
 * @author  Declan Duffy
 * @version 2022.08.08
 */
public class QuizStartActivity extends AppCompatActivity {
    /**
     * The id of the topic the user is taking a quiz on
     */
    public static final String TOPIC_ID = "topic_id";
    private int topicId;

    /**
     * Display the Start quiz screen.
     * Display the title and image for the topic the quiz is related to.
     *
     * @param savedInstanceState    Data the activity may use to restore a previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_start);

        // Get the topic id and use it to display the topic title and image
        topicId = (Integer) getIntent().getExtras().get(TOPIC_ID);
        displayQuizTopic(topicId);

        // Set an OnClickListener for the button
        Button startQuizButton = findViewById(R.id.button_start_quiz);
        startQuizButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Start the quiz when the start quiz button is clicked.
             *
             * @param view      The button clicked
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                intent.putExtra(QuizActivity.TOPIC_ID, topicId);
                startActivity(intent);
            }
        });

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
     * home(or Up): Topic completed screen
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
            // Navigate back to the completed topic screen
            Intent intent = new Intent(this, ExploreTopicCompletedActivity.class);
            intent.putExtra(ExploreTopicCompletedActivity.TOPIC_ID, topicId);
            startActivity(intent);
            return true;
        } else {
            // User action was not recognized. Use the superclass to handle it.
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Display the title and image of the topic the quiz is based on.
     *
     * @param topicId   The id of the topic the quiz is based on
     */
    public void displayQuizTopic(int topicId) {
        TextView textView = findViewById(R.id.quiz_title);
        String title = Topic.topics[topicId].getTitle();
        textView.setText(title);
        Drawable drawable;
        drawable = ContextCompat.getDrawable(this, Topic.topics[topicId].getImageId());
        ImageView imageView = findViewById(R.id.start_quiz_image);
        imageView.setImageDrawable(drawable);
    }
}