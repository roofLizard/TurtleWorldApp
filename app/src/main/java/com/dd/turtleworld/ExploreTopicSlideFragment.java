package com.dd.turtleworld;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * A fragment that displays a learning content slide from a topic's set of slides.
 * A slideId representing the slide's position in the set is passed to the fragment to
 * identify which slide needs to be displayed.
 */
public class ExploreTopicSlideFragment extends Fragment {
    /**
     * Create the learning slide
     *
     * @param inflater              The inflater for the layout
     * @param container             The container for the fragment
     * @param savedInstanceState    Data the fragment may use to restore a previous state
     * @return                      The learning slide
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the topic id and the number of the slide to be displayed
        int topicId = getArguments().getInt("TOPIC_ID");
        int slideId = getArguments().getInt("SLIDE_ID");
        // Inflate the layout for the slide
        View view = inflater.inflate(R.layout.fragment_explore_topic_slide, container, false);
        //Set the slide image
        ImageView imageView = view.findViewById(R.id.slide_image);
        int imageId = Topic.topics[topicId].getImageId();
        imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), imageId));

        //Set the slide text
        TextView textView = view.findViewById(R.id.slide_text);
        String slideText = Topic.topics[topicId].getSlides()[slideId];
        textView.setText(slideText);

        //Show the correct swipe arrows for the slide
        ImageView backArrow = view.findViewById(R.id.slide_arrow_back);
        ImageView forwardArrow = view.findViewById(R.id.slide_arrow_forward);
        if (slideId == 0) {
            backArrow.setVisibility(View.INVISIBLE);
        } else if (slideId == Topic.topics[topicId].getSlides().length - 1) {
            forwardArrow.setVisibility(View.INVISIBLE);
        }
        // Return the slide
        return view;
    }

}