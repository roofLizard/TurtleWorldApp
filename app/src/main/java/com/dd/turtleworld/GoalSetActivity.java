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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;

/**
 * The Set goal screen of the application.
 *
 * Allows a user to create a goal by choosing a learning goal type and target,
 * and set this goal for themselves to track their learning progress.
 *
 * @author  Declan Duffy
 * @version 2022.08.08
 */
public class GoalSetActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner chooseGoalSpinner;
    private Spinner setGoalTargetSpinner;
    private int goalType;
    private int goalTarget;
    private ExtendedFloatingActionButton fab;
    private GoalViewModel goalViewModel;
    private List<Goal> allGoals;
    private Goal goal;
    private TextView goalTitleTextView;

    /**
     * Display the set goal screen
     *
     * Present the user with the opportunity to:
     *  Choose a goal type
     *  Choose a goal target
     *  Set their goal
     * Present each step once the previous step is completed
     *
     * @param savedInstanceState    Data the activity may use to restore a previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_set);

        // Initialise the goal objective and target as 0
        this.goalType = 0;
        this.goalTarget = 0;

        // Get a reference to the database, and get all existing goals from it
        this.goalViewModel = new ViewModelProvider(this).get(GoalViewModel.class);
        this.allGoals = goalViewModel.getAllGoals();
        // Initialise the spinner for choosing the goal type, and create an adapter for it
        this.chooseGoalSpinner = findViewById(R.id.spinner_choose_goal);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinnner_values_choose_goal, android.R.layout.simple_spinner_item);
        // Specify the layout for the spinner to use
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Assign the adapter to the spinner
        chooseGoalSpinner.setAdapter(adapter);
        chooseGoalSpinner.setOnItemSelectedListener(this);
        // Initialise the spinner for choosing the goal target, and create an adapter for it
        this.setGoalTargetSpinner = findViewById(R.id.spinner_set_goal_target);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.spinnner_values_set_goal_target, android.R.layout.simple_spinner_item);
        // Specify the layout for the spinner to use
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Assign the adapter to the spinner
        setGoalTargetSpinner.setAdapter(adapter2);
        setGoalTargetSpinner.setOnItemSelectedListener(this);
        setGoalTargetSpinner.setVisibility(View.INVISIBLE);
        // Initialise the set goal button and assign it an OnClickListener
        this.fab = findViewById(R.id.fab_set_goal);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSetGoalClicked(view);
            }
        });
        // Hide the set goal button
        fab.setVisibility(View.INVISIBLE);
        // Hide the text that will display the chosen goal title
        this.goalTitleTextView = findViewById(R.id.goal_title_text);
        goalTitleTextView.setVisibility(View.INVISIBLE);

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
        } else {
            // User action was not recognized. Use the superclass to handle it.
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Handle when the user makes selections in the spinners
     *
     * When a goal type is selected:
     *  Display the goal target spinner
     *
     * If the goal type is deselected:
     *  Hide the goal target spinner
     *  Hide the new goal title (if displayed)
     *  Hide the set goal title (if displayed)
     *
     * When a goal target is selected:
     *  Display the new goal title
     *  Display the set goal button
     *
     * If the goal target is deselected:
     *  Hide the new goal title
     *  Hide the set goal button
     *
     * @param spinner   The spinner
     * @param view      The view selected
     * @param position  The position of the selected view
     * @param id        The id of the selected view
     */
    @Override
    public void onItemSelected(AdapterView<?> spinner, View view, int position, long id) {
        if (spinner.getId() == chooseGoalSpinner.getId()) {
            goalType = position;
            setGoalTargetSpinner.setSelection(0);
            if (position > 0) {
                setGoalTargetSpinner.setVisibility(View.VISIBLE);
            } else {
                setGoalTargetSpinner.setVisibility(View.INVISIBLE);
            }
        }
        if (spinner.getId() == setGoalTargetSpinner.getId()) {
            goalTarget = position;
            if (position > 0) {
                goal = createGoal(goalType, goalTarget);
                goalTitleTextView.setText(goal.getTitle());
                goalTitleTextView.setVisibility(View.VISIBLE);
                fab.setVisibility(View.VISIBLE);
            } else {
                fab.setVisibility(View.INVISIBLE);
                goalTitleTextView.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> spinner) {
        // Required method for spinner. Do nothing
    }

    /**
     * Create a new Goal object in response to the user's selections of type and target
     *
     * @param type      The goal type's position in the spinner
     * @param target    The goal target
     * @return          The Goal object
     */
    private Goal createGoal(int type, int target) {
        String goalType;
        // Use the value from the spinner to set the type
        if (type == 1) {
            goalType = "quiz";
        } else {
            goalType = "engage";
        }
        Goal goal = new Goal(goalType, target);
        return goal;
    }

    /**
     * Add a new goal to the database in response to a button click.
     *
     * @param view  The button clicked
     */
    private void onSetGoalClicked(View view) {
        // Make sure both fields have been selected in the spinners
        if (goalType > 0 && goalTarget > 0) {
            // Make sure the goal is new before adding it, let the user know if it's not new
            if (checkGoalIsNew(goal)) {
                goalViewModel.insertGoal(goal);
                Toast toast = Toast.makeText(getApplicationContext(), "Goal set", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(this, GoalsActivity.class);
                startActivity(intent);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "You already have that goal!", Toast.LENGTH_SHORT);
                toast.show();
            }
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Pick a goal and target", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * Check if a goal is new or if the same goal already exists
     *
     * @param goal  The goal to be checked
     * @return      Whether the goal is new or not
     */
    private boolean checkGoalIsNew(Goal goal) {
        String goalTitle = goal.getTitle();
        boolean goalIsNew = true;
        for (Goal existingGoal : allGoals) {
            if (existingGoal.getTitle().equals(goalTitle)) {
                goalIsNew = false;
                break;
            }
        }
        return goalIsNew;
    }
}