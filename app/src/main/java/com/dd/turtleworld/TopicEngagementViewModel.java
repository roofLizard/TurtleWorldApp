package com.dd.turtleworld;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.List;

/**
 * A ViewModel object for interacting with the repository for the TopicEngagement table in the application database
 */
public class TopicEngagementViewModel extends AndroidViewModel {

    private TopicEngagementRepository topicEngagementRepository;
    private List<TopicEngagement> allTopicEngagement;

    /**
     * Construct the ViewModel, get a reference to the TopicEngagement repository
     * and all the stored topic engagement records.
     *
     * @param application   The application
     */
    public TopicEngagementViewModel(Application application) {
        super(application);
        topicEngagementRepository = new TopicEngagementRepository(application);
        allTopicEngagement = topicEngagementRepository.getAllTopicEngagement();
    }

    /**
     * Get all the topic engagement records
     * @return  All the topic engagement records
     */
    public List<TopicEngagement> getAllTopicEngagement() {
        allTopicEngagement = topicEngagementRepository.getAllTopicEngagement();
        return allTopicEngagement;
    }

    /**
     * Get the topic engagement record for a specific topic
     * @param id    The id of the required topic
     * @return      The topic engagement record for the required topic
     */
    public TopicEngagement getTopicEngagement(int id) {
        return topicEngagementRepository.getTopicEngagement(id);
    }

    /**
     * Insert a new topic engagement record
     * @param topicEngagement   The new topic engagement record
     */
    public void insertTopicEngagement(TopicEngagement topicEngagement) {
        topicEngagementRepository.insertTopicEngagement(topicEngagement);
    }

    /**
     * Update a topic engagement record
     * @param topicEngagement   The topic engagement record to be updated
     */
    public void updateTopicEngagement(TopicEngagement topicEngagement) {
        topicEngagementRepository.updateTopicEngagement(topicEngagement);
    }

    /**
     * Get the number of quizzes that have been completed by the user
     * @return  The number of quizzes completed by the user
     */
    public int getTotalQuizzesCompleted() {
        return topicEngagementRepository.getTotalQuizzesCompleted();
    }

    /**
     * Get the number of topics that have been engaged with by the user
     * @return      The number of topics engaged with by the user
     */
    public int getTotalTopicsEngaged() {
        return topicEngagementRepository.getTotalTopicsEngaged();
    }
}
