package co.jaypandya.geoquiz;

/**
 * Created by Jay on 2/14/2016.
 */
/*
    A model object holds the application’s data and “business logic.”
    Model classes are typically designed to model the things your app is concerned with,
    such as a user, a product in a store, a photo on a server, or a television show.
    Or a true-false question.

    Model objects have no knowledge of the user interface;
    their sole purpose is holding and managing data.

    In Android applications, model classes are generally custom classes you create.
    All of the model objects in your application compose its model layer.
    GeoQuiz’s model layer consists of the Question class.
*/

public class Question {

    // Question text. It's an int since it'll refer to the question string's id.
    private int mTextResId;

    // Question answer, true/false, thus a bool
    private boolean mAnswerTrue;

    // Getter for mTextResId
    public int getTextResId() {
        return mTextResId;
    }

    // Getter for mAnswerTrue
    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    // Setter for mTextResId
    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    // Setter for mAnswerTrue
    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    // Constructor?
    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }
}
