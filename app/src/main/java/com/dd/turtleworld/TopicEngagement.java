package com.dd.turtleworld;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * A database entity representing a table of records of a user's engagement with the learning topics.
 * Each row in the table represents a record for one topic.
 * It holds the topic's id, along with whether has engaged with the topic or not,
 * and whether the user has completed the topic's quiz or not.
 *
 * @author Declan Duffy
 * @version 2022.08.08
 */
@Entity(tableName = "topic_engagement")
public class TopicEngagement {

    @PrimaryKey
    private int topicId;

    @ColumnInfo (name = "topic_engaged")
    private Boolean topicEngaged;

    @ColumnInfo (name = "quiz_complete")
    private Boolean quizComplete;

    /**
     * Construct a TopicEngagement object for a given topic
     * Set it so a user has not engaged with the topic or completed it's quiz
     *
     * @param topicId   The id of the topic
     */
    public TopicEngagement(@NonNull int topicId) {
        this.topicId = topicId;
        this.topicEngaged = false;
        this.quizComplete = false;
    }

    /**
     * Get a topic's id
     * @return  The topic's id
     */
    public int getTopicId() {
        return topicId;
    }

    /**
     * Get whether a user has engaged with a topic or not
     * @return  Whether a user has engaged with the topic or not
     */
    public Boolean getTopicEngaged() {
        return topicEngaged;
    }

    /**
     * Get whether a user has completed a topic's quiz or not
     * @return  Whether a user has completed a topic's quiz or not
     */
    public Boolean getQuizComplete() {
        return quizComplete;
    }

    /**
     * Set whether a user has engaged with a topic or not
     * @param topicEngaged    Whether a user has engaged with the topic or not
     */
    public void setTopicEngaged(Boolean topicEngaged) {
        this.topicEngaged = topicEngaged;
    }

    /**
     * Get whether a user has completed a topic's quiz or not
     * @param quizComplete   Whether a user has completed a topic's quiz or not
     */
    public void setQuizComplete(Boolean quizComplete) {
        this.quizComplete = quizComplete;
    }
}
