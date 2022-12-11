package com.dd.turtleworld;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

/**
 * The Goals screen of the application.
 * Displays the list of learning goals the user has set for themselves, and whether each goal
 * has been completed or not.
 *
 * @author Declan Duffy
 * @version 2022.08.08
 */
public class GoalsActivity extends AppCompatActivity {
    /**
     * Display the Goals screen.
     *
     * @param savedInstanceState Data the activity may use to restore a previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
        // Initialise the add goal button and assign an OnClickListener to it
        ExtendedFloatingActionButton fab = findViewById(R.id.fab_add_goal);
        fab.setOnClickListener(new View.OnClickListener() {
            /**
             * Moves to the add goal screen in response
             * to the add goal button being clicked.
             *
             * @param view  The button clicked
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GoalSetActivity.class);
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
     * Goals: stay here
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
            // Do nothing
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
}