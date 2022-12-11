package com.dd.turtleworld;

import android.app.Application;

import java.util.List;

/**
 * A repository for the Goal table in the application database
 *
 * @author Declan Duffy
 * @version 2022.08.08
 */
public class GoalRepository {
    private GoalDao goalDao;
    private List<Goal> allGoals;

    /**
     * Construct the repository
     * Get a reference to the database, a data access object for the Goal table
     * and all the stored goals.
     *
     * @param application   The application
     */
    public GoalRepository(Application application) {
        TurtleWorldDatabase db = TurtleWorldDatabase.getDatabase(application);
        goalDao = db.goalDao();
        allGoals = goalDao.getAll();
    }

    /**
     * Get all the stored goals
     * @return  All the goals
     */
    public List<Goal> getAllGoals() {
        allGoals = goalDao.getAll();
        return allGoals;
    }

    /**
     * Insert a new goal
     * @param goal  The new goal
     */
    public void insertGoal(Goal goal) {
        TurtleWorldDatabase.databaseWriteExecutor.execute(() -> {
            goalDao.insert(goal);
        });
    }

    /**
     * Update a goal
     * @param goal  The goal to be updated
     */
    public void updateGoal(Goal goal) {
        TurtleWorldDatabase.databaseWriteExecutor.execute(() -> {
            goalDao.update(goal);
        });
    }

    /**
     * Delete all the goals
     */
    public void deleteAllGoals() {
        TurtleWorldDatabase.databaseWriteExecutor.execute(() -> {
            goalDao.deleteAll();
        });
    }
}
