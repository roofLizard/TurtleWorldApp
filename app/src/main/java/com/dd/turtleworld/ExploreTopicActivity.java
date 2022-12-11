package com.dd.turtleworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

/**
 * Displays the learning content for a specific topic onscreen.
 * The learning content is presented as a series of slides the user can swipe through.
 *
 * @author Declan Duffy
 * @version 2022.08.08
 */
public class ExploreTopicActivity extends AppCompatActivity {
    /**
     * The id of the topic being explored
     */
    public static final String TOPIC_ID = "topic_id";
    private int topicId;
    private int numberOfSlides;
    private ExtendedFloatingActionButton fab;

    /**
     * Display the explore topic screen
     * @param savedInstanceState    Data the activity may use to restore a previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_topic);

        //Get the id of the topic
        if (savedInstanceState != null) {
            topicId = savedInstanceState.getInt("topic_ic");
        } else {
            topicId = (Integer) getIntent().getExtras().get(TOPIC_ID);
        }

        numberOfSlides = Topic.topics[topicId].getSlides().length;

        // Initialise the FAB and hide it
        fab = findViewById(R.id.fab_complete_topic);
        fab.setOnClickListener(new View.OnClickListener() {
            /**
             * Call the onCompleteTopicPressed() method when the FAB is clicked.
             *
             * @param view  The button clicked
             */
            @Override
            public void onClick(View view) {
                onCompleteTopicPressed(view);
            }
        });
        fab.hide();

        //Get the adapter to be used for displaying the slides
        TopicSlidesAdapter adapter = new TopicSlidesAdapter(getSupportFragmentManager(),
                getLifecycle());
        // Initialise the ViewPager for the slides, and set the adapter to iy
        ViewPager2 pager = findViewById(R.id.topic_pager);
        pager.setAdapter(adapter);

        //Show the FAB once the user reaches the last slide
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == (numberOfSlides - 1)) {
                    fab.show();
                }
            }
        });

        // Add the toolbar to the layout and add the back button to it
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Save the topic id if the activity closes unexpectedly
     *
     * @param savedInstanceState Data used to restore a previous state
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("topic_ic", topicId);
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Create the options menu in the toolbar
     *
     * @param menu The menu item to be inflated
     * @return The created menu
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
     * Explore: Explor screen
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
     * Adapter class used to display the individual slides onscreen in the ViewPager
     */
    public class TopicSlidesAdapter extends FragmentStateAdapter {
        /**
         * Constructor for the slides adapter
         *
         * @param fragmentManager A fragment manager object
         * @param lifecycle       The application lifecycle
         */
        public TopicSlidesAdapter(@NonNull FragmentManager fragmentManager,
                                  @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        /**
         * Display the correct slide onscreen in response to the user swiping through the slides.
         *
         * @param position The user's position in the set of slides
         * @return The slide to be displayed
         */
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Bundle bundle = new Bundle();
            bundle.putInt("TOPIC_ID", topicId);
            while (true) {
                ExploreTopicSlideFragment slide = new ExploreTopicSlideFragment();
                bundle.putInt("SLIDE_ID", position);
                slide.setArguments(bundle);
                return slide;
            }
        }

        /**
         * Get the number of items to be displayed in the ViewPager
         *
         * @return The number of slides for the topic
         */
        @Override
        public int getItemCount() {
            return numberOfSlides;
        }
    }

    /**
     * Move to the topic completed screen after a button is clicked.
     * Call updateTopicEngagement() to update the database.
     *
     * @param view The button clicked
     */
    public void onCompleteTopicPressed(View view) {
        updateTopicEngagement(topicId);
        Intent intent = new Intent(getApplicationContext(), ExploreTopicCompletedActivity.class);
        intent.putExtra(ExploreTopicCompletedActivity.TOPIC_ID, topicId);
        startActivity(intent);
    }

    /**
     * Update the application database to show the user has engaged with the topic.
     *
     * @param topicId The id of topic the user has engaged with
     */
    public void updateTopicEngagement(int topicId) {
        TopicEngagementViewModel topicEngagementViewModel = new ViewModelProvider(this).get(TopicEngagementViewModel.class);
        TopicEngagement topicEngagement = topicEngagementViewModel.getTopicEngagement(topicId);
        // Only update the database if this is the first time the user has engaged with the topic
        if (!(topicEngagement.getTopicEngaged())) {
            topicEngagement.setTopicEngaged(true);
            topicEngagementViewModel.updateTopicEngagement(topicEngagement);
        }
    }
}