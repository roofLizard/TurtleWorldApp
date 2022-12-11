package com.dd.turtleworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * The beginning of an assessment of a user's readiness to learn.
 * Welcomes the user to the app and prompts them to get started.
 *
 * @author  Declan Duffy
 * @version 2022.08.08
 */
public class AssessmentStartActivity extends AppCompatActivity {
    /**
     * Display the assessment start screen.
     *
     * @param savedInstanceState    Data the activity may use to restore a previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_start);

        // Initialise the button and assign it an OnClickListener
        Button getStartedButton = findViewById(R.id.button_get_started);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Moves to the next screen of the assessment in response
             * to the get started button being clicked.
             *
             * @param view  The button clicked
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AssessmentExcitementRatingActivity.class);
                startActivity(intent);
            }
        });
    }
}