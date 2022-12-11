package com.dd.turtleworld;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.List;

/**
 * A ViewModel object for interacting with the repository for the Goal table in the application database
 */
public class GoalViewModel extends AndroidViewModel {

    private GoalRepository goalRepository;
    private List<Goal> allGoals;

    /**
     * Construct the ViewModel, get a reference to the Goal repository and all the stored goals.
     * @param application   The application
     */
    public GoalViewModel(Application application) {
        super(application);
        goalRepository = new GoalRepository(application);
        allGoals = goalRepository.getAllGoals();
    }

    /**
     * Get all the stored goals
     * @return  All the goals
     */
    public List<Goal> getAllGoals() {
        allGoals = goalRepository.getAllGoals();
        return allGoals;
    }

    /**
     * Insert a new goal
     * @param goal  The new goal
     */
    public void insertGoal(Goal goal) {
        goalRepository.insertGoal(goal);
    }

    /**
     * Update a goal
     * @param goal  The goal to be updated
     */
    public void updateGoal(Goal goal) {
        goalRepository.updateGoal(goal);
    }

    /**
     * Delete all the goals
     */
    public void deleteAllGoals() {
        goalRepository.deleteAllGoals();
    }
}
