package com.dd.turtleworld;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * A database entity representing a table of learning goals
 * Each row in the table stores a goal's type, title and target, along with a unique id for each goal
 *
 * @author Declan Duffy
 * @version 2022.08.08
 */
@Entity(tableName = "goals")
public class Goal {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "goal_type")
    private String goalType;

    @ColumnInfo(name = "target")
    private int target;

    @ColumnInfo(name = "title")
    private String title;

    /**
     * Construct a goal object
     *
     * @param goalType  The goal's type
     * @param target    The goal's target
     */
    public Goal(String goalType, int target) {
        this.goalType = goalType;
        this.target = target;
        this.title = makeGoalTitle(goalType, target);
    }

    /**
     * Get a goal's type
     * @return  The goal's type
     */
    public String getGoalType() {
        return goalType;
    }

    /**
     * Set the type of a goal
     * @param goalType  The goal's type
     */
    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }

    /**
     * Get a goal's target
     * @return  The goal's target
     */
    public int getTarget() {
        return target;
    }

    /**
     * Set the target of a goal
     * @param target  The goal's target
     */
    public void setTarget(int target) {
        this.target = target;
    }

    /**
     * Get a goal's title
     * @return  The goal's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of a goal
     * @param title  The goal's title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get a goal's id
     * @return  The goal's id
     */
    public int getId() {
        return id;
    }

    /**
     * Set a goal's id
     * @param id   The goal's id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Use a goal's type and target to create it's title
     *
     * @param type      The goal's type
     * @param target    The goal's target
     * @return          The goal's title
     */
    private String makeGoalTitle(String type, int target) {
        String goalTitle;
        if (type.equals("quiz")) {
            if (target == 1) {
                goalTitle = "Complete 1 quiz";
            } else {
                goalTitle = "Complete " + target + " quizzes";
            }
        } else {
            if (target == 1) {
                goalTitle = "Engage with 1 topic";
            } else {
                goalTitle = "Engage with " + target + " topics";
            }
        }
        return goalTitle;
    }
}
