package com.dd.turtleworld;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * The main activity of the application.
 * Displays the home screen that users see whenever they open the app (after their first use).
 *
 * Contains buttons that navigate to the Explore and Goals screens.
 *
 * @author  Declan Duffy
 * @version 2022.08.08
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Display the home screen.
     * If this is the first use of the app, navigate to the Assessment start screen
     *
     * @param savedInstanceState    Data the activity may use to restore a previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check the data needed to check if this is the first use of the app
        boolean firstUse = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("firstUse", true);
        // If it is the first use, start the readiness to learn assessment
        if (firstUse) {
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("firstUse", false).apply();

            Intent intent = new Intent(this, AssessmentStartActivity.class);
            startActivity(intent);
        }

        // Create an OnClickListener to handle clicks of the buttons
        View.OnClickListener clickListener = new View.OnClickListener() {
            /**
             * Ensure a specific action happens when a button is clicked:
             * Explore: Go to explore screen
             * Goals: Go to goals screen
             *
             * @param view  The button clicked
             */
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.button_explore) {
                    onExploreClicked(view);
                }
                else if (view.getId() == R.id.button_goals) {
                    onGoalsClicked(view);
                }
            }
        };

        // Initialise the buttons and assign them the OnClickListener
        Button exploreButton = findViewById(R.id.button_explore);
        exploreButton.setOnClickListener(clickListener);
        Button goalsButton = findViewById(R.id.button_goals);
        goalsButton.setOnClickListener(clickListener);

        // Add the toolbar to the layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Create the options menu in the toolbar
     *
     * @param menu  The menu item to be inflated
     * @return      The created menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Move to a specific place after an item in the options menu is clicked
     * Home: stay here
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
            // Do nothing
            return true;
        }
        else if (itemId == R.id.action_explore) {
            // Navigate to the Explore screen
            Intent intent = new Intent(this, ExploreActivity.class);
            startActivity(intent);
            return true;
        }
        else if (itemId == R.id.action_goals) {
            // Navigate to the Goals screen
            Intent intent = new Intent(this, GoalsActivity.class);
            startActivity(intent);
            return true;
        }
        else if (itemId == R.id.action_reset_profile) {
            // Navigate to the Reset profile screen
            Intent intent = new Intent(this, ResetProfileActivity.class);
            startActivity(intent);
            return true;
        }
        else {
            // User action was not recognized. Use the superclass to handle it.
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Navigate to the Explore screen in response to a button click.
     *
     * @param view  The button clicked
     */
    private void onExploreClicked(View view) {
        Intent intent = new Intent(this, ExploreActivity.class);
        startActivity(intent);
    }

    /**
     * Navigate to the Goals screen in response to a button click.
     *
     * @param view  The button clicked
     */
    private void onGoalsClicked(View view) {
        Intent intent = new Intent(this, GoalsActivity.class);
        startActivity(intent);
    }
}