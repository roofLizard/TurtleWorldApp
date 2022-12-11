package com.dd.turtleworld;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The database for the TurtleWorld application.
 * Contains two tables, TopicEngagement & Goal
 */
@Database(entities = {TopicEngagement.class, Goal.class}, version = 1, exportSchema = false)
public abstract class TurtleWorldDatabase extends RoomDatabase {
    /**
     * Create a database access object for the TopicEngagement table
     * @return  The database access object for the TopicEngagement table
     */
    public abstract TopicEngagementDao topicEngagementDao();
    /**
     * Create a database access object for the Goal table
     * @return  The database access object for the Goal table
     */
    public abstract GoalDao goalDao();

    private static volatile TurtleWorldDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     * Create the TurtleWorld database
     * @param context   The current state of the application
     * @return          The TurtleWorld database
     */
    static TurtleWorldDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TurtleWorldDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TurtleWorldDatabase.class, "turtle_world_database")
                            .allowMainThreadQueries()
                            .addCallback(turtleWorldDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Populate the TopicEngagement table with records for all topics
     */
    private static final Callback turtleWorldDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                TopicEngagementDao dao = INSTANCE.topicEngagementDao();
                dao.deleteAll();
                int numberOfTopics = Topic.topics.length;
                int i = 0;
                while (i < numberOfTopics) {
                    TopicEngagement topicEngagement = new TopicEngagement(i);
                    dao.insert(topicEngagement);
                    i++;
                }
            });
        }
    };
}
