package com.dd.turtleworld;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

/**
 * A test class to test accessing the Goal table in the database
 * works as expected
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class DatabaseGoalTest {
    private GoalDao goalDao;
    private TurtleWorldDatabase db;

    /**
     * Set up an instance of the database for testing
     */
    @Before
    public void setUpDatabase() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, TurtleWorldDatabase.class).build();
        goalDao = db.goalDao();
    }

    /**
     * Close the database once the testing is done
     * @throws IOException  exception
     */
    @After
    public void closeDatabase() throws IOException {
        db.close();
    }

    /**
     * Test inserting a goal into the database works as expected
     * @throws IOException  exception
     */
    @Test
    public void testInsertGoal() throws Exception {
        Goal goal = new Goal("quiz", 1);
        goalDao.insert(goal);
        List<Goal> allGoals = goalDao.getAll();
        assertEquals(allGoals.get(0).getTitle(), goal.getTitle());
    }

    /**
     * Test getting all the goals from the database works as expected
     * @throws IOException  exception
     */
    @Test
    public void testGetAllGoals() throws Exception {
        Goal goal1 = new Goal("quiz", 1);
        Goal goal2 = new Goal("quiz", 2);
        Goal goal3 = new Goal("engage", 1);
        goalDao.insert(goal1);
        goalDao.insert(goal2);
        goalDao.insert(goal3);
        List<Goal> allGoals = goalDao.getAll();
        assertEquals(allGoals.size(), 3);
    }

    /**
     * Test deleting all the goals from the database works as expected
     * @throws IOException  exception
     */
    @Test
    public void testDeleteAllGoals() throws Exception {
        Goal goal1 = new Goal("quiz", 1);
        Goal goal2 = new Goal("quiz", 2);
        Goal goal3 = new Goal("engage", 1);
        goalDao.insert(goal1);
        goalDao.insert(goal2);
        goalDao.insert(goal3);
        goalDao.deleteAll();
        List<Goal> allGoals = goalDao.getAll();
        assertEquals(allGoals.size(), 0);
    }
}
