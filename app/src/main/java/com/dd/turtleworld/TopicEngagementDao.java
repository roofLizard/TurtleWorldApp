package com.dd.turtleworld;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
/**
 * A database access object for carrying out queries on the TopicEngagement table in the application database
 *
 * @author Declan Duffy
 * @version 2022.08.08
 */
@Dao
public interface TopicEngagementDao {
    /**
     * Insert a new topic engagement record into the table
     * @param topicEngagement  The topic engagement record to be inserted
     */
    @Insert(entity = TopicEngagement.class, onConflict = OnConflictStrategy.REPLACE)
    void insert(TopicEngagement topicEngagement);

    /**
     * Delete a topic engagement record from the table
     * @param topicEngagement  The topic engagement record to be deleted
     */
    @Delete(entity = TopicEngagement.class)
    void delete(TopicEngagement topicEngagement);

    /**
     * Update a topic engagement record in the table
     * @param topicEngagement  The topic engagement record to be updated
     */
    @Update(entity = TopicEngagement.class)
    void update(TopicEngagement topicEngagement);

    /**
     * Get all the topic engagement records from the table
     * @return  All the topic engagement records
     */
    @Query("SELECT * FROM topic_engagement")
    List<TopicEngagement> getAll();

    /**
     * Delete all the topic engagement records from the table
     */
    @Query("DELETE FROM topic_engagement")
    void deleteAll();

    /**
     * Get a specific topic engagement record from the table
     * @param id    The id of the required topic
     * @return      The topic engagement record for the required topic
     */
    @Query("SELECT * FROM topic_engagement WHERE topicId = :id")
    TopicEngagement getTopicEngagement(int id);

    /**
     * Get the number of quizzes that have been completed by the user
     * @return  The number of quizzes completed by the user
     */
    @Query("SELECT COUNT(*) FROM topic_engagement WHERE quiz_complete = 1")
    int getTotalQuizzesCompleted();

    /**
     * Get the number of topics that have been engaged with by the user
     * @return      The number of topics engaged with by the user
     */
    @Query("SELECT COUNT(*) FROM topic_engagement WHERE topic_engaged = 1")
    int getTotalTopicsEngaged();
}