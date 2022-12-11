package com.dd.turtleworld;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
/**
 * A database access object for carrying out queries on the Goal table in the application database
 *
 * @author Declan Duffy
 * @version 2022.08.08
 */
@Dao
public interface GoalDao {
    /**
     * Insert a new goal into the table
     * @param goal  The goal to be inserted
     */
    @Insert(entity = Goal.class, onConflict = OnConflictStrategy.REPLACE)
    void insert(Goal goal);

    /**
     * Delete a goal from the table
     * @param goal  The goal to be deleted
     */
    @Delete(entity = Goal.class)
    void delete(Goal goal);

    /**
     * Update a goal in the table
     * @param goal  The goal to be updated
     */
    @Update(entity = Goal.class)
    void update(Goal goal);

    /**
     * Get all the goals from the table
     * @return  All the goals in the table
     */
    @Query("SELECT * FROM goals")
    List<Goal> getAll();

    /**
     * Delete all the goals from the table
     */
    @Query("DELETE FROM goals")
    void deleteAll();
}