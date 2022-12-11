package com.dd.turtleworld;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A fragment that displays the topic the user is recommended to learn about at the end
 * of the readiness to learn assessment.
 * Clicking on the topic displays the topic's slides to the user.
 */
public class AssessmentRecommendationFragment extends Fragment {
    /**
     * Create the list (only showing one topic) to be displayed
     * on the final screen of the learning assessment.
     *
     * @param inflater              The inflater for the layout
     * @param container             The container for the fragment
     * @param savedInstanceState    Data the fragment may use to restore a previous state
     * @return                      The RecyclerView that displays the topics
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView topicsRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_explore_topics_list, container, false);

        int topicId = requireArguments().getInt("TOPIC_ID");

        //Add the topic title and image to single object arrays
        String[] topicTitle = new String[1];
        topicTitle[0] = Topic.topics[topicId].getTitle();
        int[] topicImage = new int[1];
        topicImage[0] = Topic.topics[topicId].getImageId();

        //Pass the arrays to the adapter
        ExploreTopicsCardsAdapter adapter = new ExploreTopicsCardsAdapter(topicTitle, topicImage);
        topicsRecycler.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        topicsRecycler.setLayoutManager(layoutManager);

        //Implement the listener in the fragment
        adapter.setListener(new ExploreTopicsCardsAdapter.Listener() {
            /**
             * Start displaying the content for a topic when a user clicks on the topic.
             * Pass the topic id to the next activity.
             *
             * @param position  The position of the clicked topic (will always be 0 in this fragment)
             */
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), ExploreTopicActivity.class);
                intent.putExtra(ExploreTopicActivity.TOPIC_ID, topicId);
                getActivity().startActivity(intent);
            }
        });
        return topicsRecycler;
    }
}