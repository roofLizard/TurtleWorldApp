package com.dd.turtleworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

/**
 * The second part of an assessment of a user's readiness to learn.
 * Prompts a user to rate how excited they are to start learning.
 *
 * @author  Declan Duffy
 * @version 2022.08.08
 */
public class AssessmentExcitementRatingActivity extends AppCompatActivity {
    /**
     * Display the excitement rating screen of the assessment.
     *
     * @param savedInstanceState    Data the activity may use to restore a previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_excitement_rating);

        // Initialise the rating bar and button
        RatingBar ratingBar = findViewById(R.id.ratingBar_excitement);
        Button nextButton = findViewById(R.id.button_excitement_next);
        nextButton.setVisibility(View.INVISIBLE);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                nextButton.setVisibility(View.VISIBLE);
            }
        });
        // Set an OnClickListener for the button
        nextButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Moves to the next screen of the assessment, passing on the excitement rating,
             * in response to the next button being clicked.
             *
             * @param view  The button clicked
             */
            @Override
            public void onClick(View view) {
                int stars = (int) ratingBar.getRating();
                Intent intent = new Intent(getApplicationContext(), AssessmentLearningChoiceActivity.class);
                intent.putExtra(AssessmentLearningChoiceActivity.EXCITEMENT, stars);
                startActivity(intent);
            }
        });
    }
}