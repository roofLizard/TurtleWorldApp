package com.dd.turtleworld;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * A fragment that displays the user's learning goals and whether the goals have been completed or not.
 */
public class GoalListFragment extends Fragment {
    private GoalViewModel goalViewModel;
    private TopicEngagementViewModel topicEngagementViewModel;

    /**
     * Create the list of topics to be displayed on the Goals screen
     *
     * @param inflater              The inflater for the layout
     * @param container             The container for the fragment
     * @param savedInstanceState    Data the fragment may use to restore a previous state
     * @return                      The RecyclerView that displays the list of goals
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView goalsRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_goal_list, container, false);

        // Get the database ViewModels for the goals and topic engagement
        goalViewModel = new ViewModelProvider(requireActivity()).get(GoalViewModel.class);
        topicEngagementViewModel = new ViewModelProvider(requireActivity()).get(TopicEngagementViewModel.class);
        // Get the Goals, the number of topics engaged and the number of quizzes completed
        List<Goal> allGoals = goalViewModel.getAllGoals();
        int totalTopicsEngaged = topicEngagementViewModel.getTotalTopicsEngaged();
        int totalQuizzesCompleted = topicEngagementViewModel.getTotalQuizzesCompleted();

        //Add all the Goals to an array
        Goal[] goals = new Goal[allGoals.size()];
        for (int i = 0; i < allGoals.size(); i++) {
            goals[i] = allGoals.get(i);
        }

        //Pass the goals, the number of topics engaged and the number of quizzes completed to the adapter
        GoalsCardsAdapter adapter = new GoalsCardsAdapter(goals, totalTopicsEngaged, totalQuizzesCompleted);
        goalsRecycler.setAdapter(adapter);
        // Set the list to display as a linear layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        goalsRecycler.setLayoutManager(layoutManager);

        //Implement the listener in the fragment
        adapter.setListener(new GoalsCardsAdapter.Listener() {
            @Override
            public void onClick(int position) {
                // Do nothing
            }
        });

        return goalsRecycler;
    }
}