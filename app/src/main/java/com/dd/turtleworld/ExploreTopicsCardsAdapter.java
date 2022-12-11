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
 * An adapter to set up the cards that display the topics in the ExploreTopicsListFragment.
 * Takes the name and image for each topic and uses this to create a CardView for each.
 */
public class ExploreTopicsCardsAdapter extends RecyclerView.Adapter<ExploreTopicsCardsAdapter.ViewHolder> {

    private final String[] titles;
    private final int[] imageIds;
    private Listener listener;

    interface Listener {
        void onClick(int position);
    }

    /**
     * Constructor for the adapter.
     * Takes inputs of arrays containing the topic titles and images.
     *
     * @param titles    The titles of the topics to be displayed
     * @param imageIds  The images for the topics to be displayed
     */
    public ExploreTopicsCardsAdapter(String[] titles, int[] imageIds) {
        this.titles = titles;
        this.imageIds = imageIds;
    }

    /**
     * Define the ViewHolder for holding the topic cards
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    /**
     * Get the amount of topic cards to be displayed
     * @return  The number of topics
     */
    @Override
    public int getItemCount() {
        return titles.length;
    }

    /**
     * Set the listener for the cards.
     * @param listener  The listener
     */
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    /**
     * Create the ViewHolder for the cards
     * @param parent    The parent ViewGroup
     * @param viewType  The type of views to be held
     * @return          The ViewHolder for the cards
     */
    @Override
    public ExploreTopicsCardsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_topic, parent, false);
        return new ViewHolder(cv);
    }

    /**
     * Set the values for each topic card
     *
     * @param holder    The ViewHolder holding the cards
     * @param position  The position of a card in the ViewHolder
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        //Set the card image
        ImageView imageView = cardView.findViewById(R.id.card_image);
        Drawable drawable;
        drawable = ContextCompat.getDrawable(cardView.getContext(), imageIds[holder.getAdapterPosition()]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(titles[position]);
        //Set the card title
        TextView titleText = cardView.findViewById(R.id.card_title);
        titleText.setText(titles[holder.getAdapterPosition()]);

        //Add an OnClicklistener to the CardViews
        cardView.setOnClickListener(new View.OnClickListener() {
            /**
             * Call the listener's onClick() method when a card is clicked.
             * @param v     The topic card that's been clicked
             */
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(holder.getAdapterPosition());
                }
            }
        });
    }
}
