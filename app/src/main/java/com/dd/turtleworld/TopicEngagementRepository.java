package com.dd.turtleworld;

import android.app.Application;


import java.util.List;

/**
 * A repository for the TopicEngagement table in the application database
 *
 * @author Declan Duffy
 * @version 2022.08.08
 */
public class TopicEngagementRepository {
    private TopicEngagementDao topicEngagementDao;
    private List<TopicEngagement> allTopicEngagement;

    /**
     * Construct the repository
     * Get a reference to the database, a data access object for the TopicEngagement table
     * and all the stored topic engagement records.
     *
     * @param application   The application
     */
    public TopicEngagementRepository(Application application) {
        TurtleWorldDatabase db = TurtleWorldDatabase.getDatabase(application);
        topicEngagementDao = db.topicEngagementDao();
        allTopicEngagement = topicEngagementDao.getAll();
    }

    /**
     * Get all the topic engagement records
     * @return  All the topic engagement records
     */
    public List<TopicEngagement> getAllTopicEngagement() {
        allTopicEngagement = topicEngagementDao.getAll();
        return allTopicEngagement;
    }

    /**
     * Get the topic engagement record for a specific topic
     * @param id    The id of the required topic
     * @return      The topic engagement record for the required topic
     */
    public TopicEngagement getTopicEngagement(int id) {
        return topicEngagementDao.getTopicEngagement(id);
    }

    /**
     * Insert a new topic engagement record
     * @param topicEngagement   The new topic engagement record
     */
    public void insertTopicEngagement(TopicEngagement topicEngagement) {
        TurtleWorldDatabase.databaseWriteExecutor.execute(() -> {
            topicEngagementDao.insert(topicEngagement);
        });
    }

    /**
     * Update a topic engagement record
     * @param topicEngagement   The topic engagement record to be updated
     */
    public void updateTopicEngagement(TopicEngagement topicEngagement) {
        TurtleWorldDatabase.databaseWriteExecutor.execute(() -> {
            topicEngagementDao.update(topicEngagement);
        });
    }

    /**
     * Get the number of quizzes that have been completed by the user
     * @return  The number of quizzes completed by the user
     */
    public int getTotalQuizzesCompleted() {
        return topicEngagementDao.getTotalQuizzesCompleted();
    }

    /**
     * Get the number of topics that have been engaged with by the user
     * @return      The number of topics engaged with by the user
     */
    public int getTotalTopicsEngaged() {
        return topicEngagementDao.getTotalTopicsEngaged();
    }

}