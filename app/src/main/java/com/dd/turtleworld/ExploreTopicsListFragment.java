package com.dd.turtleworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A fragment that displays the topics the user can choose to learn about.
 * Clicking on a topic displays the topic's slides to the user.
 */
public class ExploreTopicsListFragment extends Fragment {
    /**
     * Create the list of topics to be displayed on the Explore screen
     *
     * @param inflater              The inflater for the layout
     * @param container             The container for the fragment
     * @param savedInstanceState    Data the fragment may use to restore a previous state
     * @return                      The RecyclerView that displays the list of topics
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView topicsRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_explore_topics_list, container, false);

        //Add all the topic data to arrays
        String[] topicTitles = new String[Topic.topics.length];
        for (int i = 0; i < topicTitles.length; i++ ) {
            topicTitles[i] = Topic.topics[i].getTitle();
        }

        int[] topicImages = new int[Topic.topics.length];
        for (int i = 0; i < topicImages.length; i++ ) {
            topicImages[i] = Topic.topics[i].getImageId();
        }

        // Pass the arrays to the adapter
        ExploreTopicsCardsAdapter adapter = new ExploreTopicsCardsAdapter(topicTitles, topicImages);
        topicsRecycler.setAdapter(adapter);
        // Set the list to display as a linear layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        topicsRecycler.setLayoutManager(layoutManager);

        //Implement the listener in the fragment
        adapter.setListener(new ExploreTopicsCardsAdapter.Listener() {
            /**
             * Start displaying the content for a topic when a user clicks on that topic in the list.
             * Pass the topic id to the next activity.
             *
             * @param position  The position of the clicked topic
             */
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), ExploreTopicActivity.class);
                intent.putExtra(ExploreTopicActivity.TOPIC_ID, position);
                getActivity().startActivity(intent);
            }
        });

        return topicsRecycler;
    }
}