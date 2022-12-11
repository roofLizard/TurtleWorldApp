package com.dd.turtleworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * The third part of an assessment of a user's readiness to learn.
 * Prompts a user to choose something they are most excited to learn about.
 *
 * @author  Declan Duffy
 * @version 2022.08.08
 */
public class AssessmentLearningChoiceActivity extends AppCompatActivity {
    /**
     * The user's excitement level from the previous activity
     */
    public static final String EXCITEMENT = "excitement";
    private int excitement;

    /**
     * Display the learning choices screen of the assessment.
     *
     * @param savedInstanceState    Data the activity may use to restore a previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_learning_choice);

        // Get the user's excitement rating from the previous activity
        excitement = (Integer) getIntent().getExtras().get(EXCITEMENT);

        // Initialise the next button and make it invisible
        Button nextButton = findViewById(R.id.button_assessment_choices_next);
        nextButton.setVisibility(View.INVISIBLE);
        // Initialise the radio buttons that display the learning choices
        RadioButton radioButton1 = findViewById(R.id.radio_button_0);
        RadioButton radioButton2 = findViewById(R.id.radio_button_1);
        RadioButton radioButton3 = findViewById(R.id.radio_button_2);
        // Create an OnClickListener for the buttons
        View.OnClickListener clickListener = new View.OnClickListener() {
            /**
             * Makes the next button appear once a learning question is clicked.
             * Moves to the next screen of the assessment if the next button is clicked.
             *
             * @param view  The button clicked
             */
            @Override
            public void onClick(View view) {
                if (view.getId() ==  R.id.button_assessment_choices_next) {
                    onNextClicked(view);
                }
                else {
                    nextButton.setVisibility(View.VISIBLE);
                }
            }
        };
        //Assign the OnClickListener to all the buttons
        nextButton.setOnClickListener(clickListener);
        radioButton1.setOnClickListener(clickListener);
        radioButton2.setOnClickListener(clickListener);
        radioButton3.setOnClickListener(clickListener);

    }

    /**
     * Moves to the next screen of the assessment in response
     * to a button being clicked.
     *
     * Takes the user's excitement rating from the previous activity and passes it to the next activity.
     * Matches a learning topic to the user's selection of what they're most excited to learn about
     * and passes it's id to the next activity.
     *
     * @param view  The button clicked
     */
    private void onNextClicked(View view) {
        RadioGroup radioGroup = findViewById(R.id.radio_group_assessment_choices);
        int id = radioGroup.getCheckedRadioButtonId();
        int topicId;
        if (id == R.id.radio_button_0) {
          topicId = 1;
        } else if (id == R.id.radio_button_1) {
            topicId = 2;
        } else {
            topicId = 0;
        }
        Intent intent = new Intent(this, AssessmentCompletedActivity.class);
        intent.putExtra(AssessmentCompletedActivity.EXCITEMENT, excitement);
        intent.putExtra(AssessmentCompletedActivity.TOPIC_ID, topicId);
        startActivity(intent);
    }
}