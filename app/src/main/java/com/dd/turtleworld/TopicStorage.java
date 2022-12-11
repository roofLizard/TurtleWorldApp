package com.dd.turtleworld;
/**
 * A class for storing the data used to form the TurtleWorld learning topics.
 * To create a new topic, create the following values in this class:
 *
 * String:      -   The title of the topic.
 * int:         -   The id of the image used to represent the topic.
 * String[]:    -   The learning slides for the topic.
 *                  Each string in the array represents the text for one slide.
 * String[][]   -   The quiz questions for the topic.
 *                  Each array represents a question and three possible answers.
 *                  There must be 4 strings in each array.
 *                  The first string [0] is the question.
 *                  The second string [1] is the correct answer.
 *                  The remaining strings are the incorrect answers.
 */
public class TopicStorage {
    // What makes a turtle
    /**
     * The title of the 'What makes a turtle?' topic
     */
    public static String whatMakesATurtleTitle = "What makes a turtle?";
    /**
     * The image for the 'What makes a turtle?' topic
     */
    public static int whatMakesATurtleImage = R.drawable.ic_icon_turtle_color;
    /**
     * The summary of the 'What makes a turtle?' topic
     */
    public static String whatMakesATurtleSummary = "what makes a turtle";
    /**
     * The learning slides for the 'What makes a turtle?' topic
     */
    public static String[] whatMakesATurtleSlides = {
            "Turtles are an amazing group of reptiles...",
            "...what makes them unique is their bony shells that cover their bodies.",
            "There are 3 main types of turtle:",
            "Sea turtles live their lives in the ocean,",
            "Tortoises spend their whole life on land,",
            "Terrapins live both on land and in the water.",
            "Why not research all 3 types and decide which is your favourite?"
    };
    /**
     * The quiz questions slides for the 'What makes a turtle?' topic
     */
    public static String[][] whatMakesATurtleQuestions = {
            {"What makes turtles unique?", "Shells", "Scales", "Toes"},
            {"Which turtle lives on land and in water?", "Terrapin", "Sea turtle", "Tortoise"},
            {"What are turtles a group of?", "Reptiles", "Fish", "Dinosaurs"}
    };


    //What do turtles eat?
    /**
     * The title of the 'What do turtles eat?' topic
     */
    public static String whatDoTurtlesEatTitle = "What do turtles eat?";
    /**
     * The image for the 'What do turtles eat?' topic
     */
    public static int whatDoTurtlesEatImage = R.drawable.ic_icon_food_color;
    /**
     * The summary of the 'What do turtles eat?' topic
     */
    public static String whatDoTurtlesEatSummary = "what turtles eat";
    /**
     * The learning slides for the 'What do turtles eat?' topic
     */
    public static String[] whatDoTurtlesEatSlides = {
            "Different turtles eat all sorts of food...",
            "...this can range from shrimp and crabs to plants and leaves.",
            "Turtles don’t have any teeth, instead they have sharp beaks...",
            "...they don’t chew their food, they use their beaks to chop it into small pieces.",
            "Turtle beaks are made of keratin, the same stuff human fingernails are made of.",
            "Lots of turtles are in danger from accidentally eating plastic...",
            "...maybe you could look up how you can help turtles by cutting down on plastic?"
    };
    /**
     * The quiz questions for the 'What do turtles eat?' topic
     */
    public static String[][] whatDoTurtlesEatQuestions = {
            {"What do turtles eat with?", "Beaks", "Teeth", "Claws"},
            {"What should turtles not be eating?", "Plastic", "Shrimp", "Plants"},
            {"What are turtle beaks made of?", "Keratin", "Bone", "Metal"}
    };


    //How do turtles live?
    /**
     * The title of the 'How do turtles live?' topic
     */
    public static String howDoTurtlesLiveTitle = "How do turtles live?";
    /**
     * The image for the 'How do turtles live?' topic
     */
    public static int howDoTurtlesLiveImage = R.drawable.ic_icon_island_color;
    /**
     * The summary of the 'How do turtles live?' topic
     */
    public static String howDoTurtlesLiveSummary = "how turtles live";
    /**
     * The learning slides for the 'How do turtles live?' topic
     */
    public static String[] howDoTurtlesLiveSlides = {
            "All turtles hatch from eggs...",
            "...every turtle lays their eggs on land, even the ones that live in the sea.",
            "Mother turtles bury their eggs in the ground to keep them safe...",
            "...they leave them to hatch and grow on their own.",
            "Lots of animals eat baby turtles, so they need to be very lucky to grow into adults.",
            "Once they grow up, turtles are protected by their tough shells...",
            "...but they can still be in danger from big predators like sharks and crocodiles.",
            "Some turtles can live for a very long time, especially sea turtles and giant tortoises...",
            "...maybe you could find out which turtle has lived the longest?"
    };
    /**
     * The quiz questions for the 'How do turtles live?' topic
     */
    public static String[][] howDoTurtlesLiveQuestions = {
            {"How do mother turtles keep their eggs safe?", "Bury them", "Sit on them", "Watch them"},
            {"Where do all turtles lay their eggs?", "On land", "Underwater", "In trees"},
            {"What might try eat an adult turtle?", "Crocodile", "Elephant", "Blue whale"}
    };
}