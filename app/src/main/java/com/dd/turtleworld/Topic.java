package com.dd.turtleworld;

/**
 * A class representing a TurtleWorld learning topic.
 * Contains the topic's title, summary, learning slides, quiz questions and image
 */
public class Topic {
    private final String title;
    private final int imageId;
    private final String summary;
    private final String[] slides;
    private final String[][] questions;
    /**
     * The collection of existing learning topics
     */
    public static final Topic[] topics = {
            new Topic(TopicStorage.whatMakesATurtleTitle, TopicStorage.whatMakesATurtleImage, TopicStorage.whatMakesATurtleSummary, TopicStorage.whatMakesATurtleSlides, TopicStorage.whatMakesATurtleQuestions),
            new Topic(TopicStorage.whatDoTurtlesEatTitle, TopicStorage.whatDoTurtlesEatImage, TopicStorage.whatDoTurtlesEatSummary, TopicStorage.whatDoTurtlesEatSlides, TopicStorage.whatDoTurtlesEatQuestions),
            new Topic(TopicStorage.howDoTurtlesLiveTitle, TopicStorage.howDoTurtlesLiveImage, TopicStorage.howDoTurtlesLiveSummary, TopicStorage.howDoTurtlesLiveSlides, TopicStorage.howDoTurtlesLiveQuestions)
    };

    /**
     * Construct a new learning topic
     *
     * @param title         The title of the topic
     * @param imageId       The id of the image to visually represent the topic
     * @param summary       The summary of what the topic is about
     * @param slides        The learning slides for the topic
     * @param questions     The quiz questions for the topic
     */
    public Topic(String title, int imageId, String summary, String[] slides, String[][] questions) {
        this.title = title;
        this.imageId = imageId;
        this.summary = summary;
        this.slides = slides;
        this.questions = questions;
    }

    /**
     * Get the title of the topic
     * @return  The topic's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the id of topic's image
     * @return  The topic's image's id
     */
    public int getImageId() {
        return imageId;
    }

    /**
     * Get the summary of what the topic is about
     * @return  The topic's summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Get the learning slides for the topic
     * @return  The topic's learning slides
     */
    public String[] getSlides() {
        return slides;
    }

    /**
     * Get the quiz questions for the topic
     * @return  The topic's quiz questions
     */
    public String[][] getQuestions() {
        return questions;
    }
}
