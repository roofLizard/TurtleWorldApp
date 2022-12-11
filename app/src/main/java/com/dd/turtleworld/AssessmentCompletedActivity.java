package com.dd.turtleworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * The end of an assessment of a user's readiness to learn.
 * Presents a message based on the user's excitement level from earlier in the assessment.
 * Presents a recommended learning topic to start with, based on the user's choice from earlier in
 * the assessment.
 *
 * Prompts to user to start learning about their recommended topic, or start exploring the app
 * by navigating to the home screen.
 *
 * @author  Declan Duffy
 * @version 2022.08.08
 */
public class AssessmentCompletedActivity extends AppCompatActivity {
    /**
     * The user's excitement from earlier in the assessment
     */
    public static final String EXCITEMENT = "excitement";
    /**
     * The id of the topic to be recommended to the user based on their choice in the previous activity
     */
    public static final String TOPIC_ID = "topicID";

    /**
     * Display the final screen of the assessment.
     * Display a message based on a user's excitement levels.
     * Display a topic recommendation based on the user's choice in the previous activity.
     *
     * @param savedInstanceState    Data the activity may use to restore a previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_completed);

        // Get the excitement rating and recommended topic id
        int excitement = (Integer) getIntent().getExtras().get(EXCITEMENT);
        int topicId = (Integer) getIntent().getExtras().get(TOPIC_ID);

        // Display a message based on how excited the user is to start learning
        displayAssessmentResultText(excitement);

        // Get a reference to the fragment that shows the list of topics.
        // Pass it the id of the recommended topic so only the recommended topic is displayed.
        Bundle bundle = new Bundle();
        bundle.putInt("TOPIC_ID", topicId);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_recommendation, AssessmentRecommendationFragment.class, bundle)
                .commit();

        // Initialise the home button
        Button homeButton = findViewById(R.id.button_home_from_assessment);
        homeButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Moves to the home screen of the assessment in response
             * to the home button being clicked.
             *
             * @param view  The button clicked
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Display a message that relates to how excited the user is to start learning.
     *
     * @param excitement    The user's excitement level (from 1 - 5)
     */
    private void displayAssessmentResultText(int excitement) {
        TextView excitementText = findViewById(R.id.excitement_message_text);
        TextView recommendationText = findViewById(R.id.recommendation_message_text);
        if (excitement < 3) {
            excitementText.setText(R.string.excitement_message_not_excited);
            recommendationText.setText(R.string.recommendation_message_not_excited);

        } else {
            excitementText.setText(R.string.excitement_message_excited);
            recommendationText.setText(R.string.recommendation_message_excited);
        }
    }
}