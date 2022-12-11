package com.dd.turtleworld;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Presents a user with a quiz on a specific learning topic.
 * The user is prompted to answer a series of multiple choice questions.
 * There are 3 possible answers for each question, and a user is told upon answering whether they
 * are correct or not.
 *
 * @author Declan Duffy
 * @version 2022.08.08
 */
public class QuizActivity extends AppCompatActivity {
    /**
     * The id of the topic the user is taking a quiz on
     */
    public static final String TOPIC_ID = "topic_id";
    private static final String TAG = "QuizActivity";
    private int topicId;

    private int score;
    private int questionNum;
    private String correctAnswer;
    private String[][] questions;

    private TextView questionText;
    private Button button1, button2, button3, next_button;

    /**
     * Display the quiz screen
     *
     * @param savedInstanceState    Data the activity may use to restore a previous stat
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Get the quiz questions from the Topic class
        topicId = (Integer) getIntent().getExtras().get(TOPIC_ID);
        questions = Topic.topics[topicId].getQuestions();

        // Create the OnClickListener to handle clicks of the buttons
        View.OnClickListener clickListener = new View.OnClickListener() {
            /**
             * Ensures a specific action happens when a button is clicked:
             * Any answer button: Answer the question
             * Next/Finish button: Show next question/Finish the quiz
             *
             * @param view  The button clicked
             */
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.quiz_next_button) {
                    onNextClicked(view);
                }
                else {
                    onAnswerClicked(view);
                }
            }
        };

        // Set the user's score and question number to 0
        score = 0;
        questionNum = 0;

        //Initialise all the views needed for the quiz
        questionText = findViewById(R.id.quiz_question_text);
        button1 = findViewById(R.id.quiz_button_1);
        button2 = findViewById(R.id.quiz_button_2);
        button3 = findViewById(R.id.quiz_button_3);
        next_button = findViewById(R.id.quiz_next_button);
        next_button.setText(R.string.next);
        next_button.setVisibility(View.INVISIBLE);

        //Set the onClickListener for all the buttons
        button1.setOnClickListener(clickListener);
        button2.setOnClickListener(clickListener);
        button3.setOnClickListener(clickListener);
        next_button.setOnClickListener(clickListener);

        // Add the toolbar to the layout and add the Up button to it
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Show a question
        startQuestion();
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
            // Navigate back to the Start quiz screen slides
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
     * Display a question to the user.
     * Show the question text and display the 3 possible answers on buttons
     */
    public void startQuestion() {
        String question = questions[questionNum][0];
        questionText.setText(question);

        ArrayList<String> answers = makeAnswers();

        button1.setText(answers.get(0));
        button2.setText(answers.get(1));
        button3.setText(answers.get(2));
    }

    /**
     * Get the list of possible answers to the question
     * @return  The list of possible answers
     */
    public ArrayList<String> makeAnswers() {

        String[] questionArray = questions[questionNum];
        ArrayList<String> answers = new ArrayList<>();
        for (int i = 1; i < questionArray.length; i++) {
            answers.add(questionArray[i]);
        }
        correctAnswer = answers.get(0);
        Collections.shuffle(answers);
        return answers;
    }

    /**
     * Respond to the user clicking an answer button.
     * Tell them whether they are correct or not and show the next button.
     * Increase the user's score if the question is correct.
     * If they've answered the last question, change the next button to a finish button.
     *
     * @param view  The button clicked
     */
    private void onAnswerClicked(View view) {
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        String ans = ((Button) view).getText().toString();
        if (ans.equals(correctAnswer)) {
            score++;
            Toast toast = Toast.makeText(this, R.string.thats_right, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(this, R.string.thats_wrong, Toast.LENGTH_SHORT);
            toast.show();
        }

        if (questionNum == (questions.length - 1)) {
            next_button.setText(R.string.finish);
        }
        next_button.setVisibility(View.VISIBLE);
    }

    /**
     * Respond to the user clicking the next button.
     * If there are still questions to display, show the next question and hide the next button.
     * If there are no more questions, move to the Quiz completed screen and pass it the topic id and user's score.
     *
     * @param view  The button clicked
     */
    private void onNextClicked(View view) {
        questionNum++;
        if (questionNum < questions.length) {
            button1.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(true);
            next_button.setVisibility(View.INVISIBLE);
            startQuestion();
        } else {
            Intent intent = new Intent(this, QuizCompletedActivity.class);
            intent.putExtra(QuizCompletedActivity.TOPIC_ID, topicId);
            intent.putExtra(QuizCompletedActivity.FINAL_SCORE, score);
            startActivity(intent);
        }
    }
}