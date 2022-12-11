package com.dd.turtleworld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
 * A test class to test accessing the TopicEngagement table in the database
 * works as expected
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class DatabaseTopicEngagementTest {
    private TopicEngagementDao topicEngagementDao;
    private TurtleWorldDatabase db;

    /**
     * Set up an instance of the database for testing
     */
    @Before
    public void setUpDatabase() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, TurtleWorldDatabase.class).build();
        topicEngagementDao = db.topicEngagementDao();
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
     * Test inserting a topic engagement record into the database works as expected
     * @throws IOException  exception
     */
    @Test
    public void testInsertTopicEngagement() throws Exception {
        TopicEngagement topicEngagement = new TopicEngagement(3);
        topicEngagementDao.insert(topicEngagement);
        List<TopicEngagement> allTopicEngagement = topicEngagementDao.getAll();
        assertEquals(allTopicEngagement.get(0).getTopicId(), 3);
    }

    /**
     * Test getting a topic engagement record from the database works as expected
     * @throws IOException  exception
     */
    @Test
    public void testGetTopicEngagement() throws Exception {
        TopicEngagement topicEngagement = new TopicEngagement(1);
        topicEngagementDao.insert(topicEngagement);
        TopicEngagement topicEngagement2 = topicEngagementDao.getTopicEngagement(1);
        assertEquals(topicEngagement.getTopicId(), topicEngagement2.getTopicId());
    }

    /**
     * Test updating a topic engagement record in the database works as expected
     * @throws IOException  exception
     */
    @Test
    public void testUpdateTopicEngagement() throws Exception {
        TopicEngagement topicEngagement = new TopicEngagement(1);
        topicEngagementDao.insert(topicEngagement);
        topicEngagement.setQuizComplete(true);
        topicEngagementDao.update(topicEngagement);
        assertTrue(topicEngagementDao.getTopicEngagement(1).getQuizComplete());
    }

    /**
     * Test getting all the topic engagement records from the database works as expected
     * @throws IOException  exception
     */
    @Test
    public void testGetAllTopicEngagement() throws Exception {
        TopicEngagement topicEngagement1 = new TopicEngagement(0);
        TopicEngagement topicEngagement2 = new TopicEngagement(1);
        TopicEngagement topicEngagement3 = new TopicEngagement(2);
        topicEngagementDao.insert(topicEngagement1);
        topicEngagementDao.insert(topicEngagement2);
        topicEngagementDao.insert(topicEngagement3);
        List<TopicEngagement> allTopicEngagement = topicEngagementDao.getAll();
        assertEquals(allTopicEngagement.size(), 3);
    }

    /**
     * Test getting the number of quizzes completed by a user works as expected
     * @throws IOException  exception
     */
    @Test
    public void testGetTotalQuizzesCompleted() throws Exception {
        TopicEngagement topicEngagement1 = new TopicEngagement(0);
        topicEngagement1.setQuizComplete(true);
        TopicEngagement topicEngagement2 = new TopicEngagement(1);
        topicEngagement2.setQuizComplete(true);
        TopicEngagement topicEngagement3 = new TopicEngagement(2);
        topicEngagementDao.insert(topicEngagement1);
        topicEngagementDao.insert(topicEngagement2);
        topicEngagementDao.insert(topicEngagement3);
        assertEquals(topicEngagementDao.getTotalQuizzesCompleted(), 2);
    }

    /**
     * Test getting the number of topics engaged with by a user works as expected
     * @throws IOException  exception
     */
    @Test
    public void testGetTotalTopicEngaged() throws Exception {
        TopicEngagement topicEngagement1 = new TopicEngagement(0);
        topicEngagement1.setTopicEngaged(true);
        TopicEngagement topicEngagement2 = new TopicEngagement(1);
        topicEngagement2.setTopicEngaged(true);
        TopicEngagement topicEngagement3 = new TopicEngagement(2);
        topicEngagementDao.insert(topicEngagement1);
        topicEngagementDao.insert(topicEngagement2);
        topicEngagementDao.insert(topicEngagement3);
        assertEquals(topicEngagementDao.getTotalTopicsEngaged(), 2);
    }
}
