package com.dd.turtleworld;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * An adapter to set up the cards that display the user's learning goals in the GoalListFragment.
 * Takes the Goal objects from the TurtleWorldDatabase and uses them to create a CardView for each.
 */
public class GoalsCardsAdapter extends RecyclerView.Adapter<GoalsCardsAdapter.ViewHolder> {

    private final Goal[] goals;
    private final int totalTopicsEngaged;
    private final int totalQuizzesCompleted;
    private Listener listener;

    private final int completeIcon;
    private final int inProgressIcon;

    interface Listener {
        void onClick(int position);
    }

    /**
     * Constructor for the adapter.
     * Takes inputs of an array containing the Goal objects, the number of topics engaged
     * and the number of quizzes completed.
     *
     * @param goals                     The user's learning goals
     * @param totalTopicsEngaged        The number of topics the user has engaged with
     * @param totalQuizzesCompleted     The number of quizzes the user has completed
     */
    public GoalsCardsAdapter(Goal[] goals, int totalTopicsEngaged, int totalQuizzesCompleted) {
        this.goals = goals;
        this.totalTopicsEngaged = totalTopicsEngaged;
        this.totalQuizzesCompleted = totalQuizzesCompleted;
        this.completeIcon = R.drawable.ic_goal_icon_complete_dark;
        this.inProgressIcon = R.drawable.ic_goal_icon_in_progress;
    }

    /**
     * Define the ViewHolder for holding the goal cards
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    /**
     * Get the amount of goal cards to be displayed
     * @return  The number of learning goals
     */
    @Override
    public int getItemCount() {
        return goals.length;
    }

    /**
     * Set the listener for the cards.
     * @param listener  The listener
     */
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    /**
     * Create the ViewHolder for displaying the cards
     *
     * @param parent    The parent ViewGroup
     * @param viewType  The type of views to be held
     * @return          The ViewHolder for the cards
     */
    @Override
    public GoalsCardsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Code to instantiate the ViewHolder
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_goal, parent, false);
        return new ViewHolder(cv);
    }

    /**
     * Set the values for each goal card
     *
     * @param holder    The ViewHolder holding the cards
     * @param position  The position of a card in the ViewHolder
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        Goal goal = goals[holder.getAdapterPosition()];
        //Set the goal title
        String goalTitle = goal.getTitle();
        TextView titleText = cardView.findViewById(R.id.card_title);
        titleText.setText(goalTitle);

        //Set the progress icon
        ImageView imageView = cardView.findViewById(R.id.card_image);
        Drawable drawable;
        Boolean goalComplete = checkGoalComplete(goal);
        if (goalComplete) {
            drawable = ContextCompat.getDrawable(cardView.getContext(), completeIcon);
            imageView.setImageDrawable(drawable);
            imageView.setContentDescription("Goal complete icon");
        } else {
            drawable = ContextCompat.getDrawable(cardView.getContext(), inProgressIcon);
            imageView.setImageDrawable(drawable);
            imageView.setContentDescription("Goal in progress icon");
        }

        //Add the listener to the cardview
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(holder.getAdapterPosition());
                }
            }
        });
    }

    /**
     * Check if a learning goal has been completed or not.
     *
     * @param goal  The goal set by the user
     * @return      Whether the goal has been completed or not
     */
    public Boolean checkGoalComplete(Goal goal) {
        String goalType = goal.getGoalType();
        int goalTarget = goal.getTarget();
        if (goalType.equals("engage") && totalTopicsEngaged >= goalTarget) {
            return true;
        } else if (goalType.equals("quiz") && totalQuizzesCompleted >= goalTarget) {
            return true;
        } else {
            return false;
        }
    }
}
