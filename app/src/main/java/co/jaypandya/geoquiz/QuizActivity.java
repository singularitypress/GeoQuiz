package co.jaypandya.geoquiz;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

/*
    Controller objects tie the view and model objects together.
    They contain “application logic.” Controllers are designed to respond to various events
    triggered by view objects and to manage the flow of data to and from model objects
    and the view layer.

    In Android, a controller is typically a subclass of Activity, Fragment, or Service.
    (You will learn about fragments in Chapter 7 and services in Chapter 26.)

    GeoQuiz’s controller layer, at present, consists solely of QuizActivity.
*/

public class QuizActivity extends AppCompatActivity {

    /*
    Essentially, I'm going to declare two private buttons below this comment,
    within the scope of QuizActivity, but outside the methods so it ought to be
    accessible by the QuizActivity methods.
    */
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    // Question class has an int mTextResId and mAnswerTrue, so lets create an array of those to hold our questions and answers.
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };
    // Index of the above array should probably start at 0.
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
            Wiring up the TextView.
            Here we link the textview called question_text_view to mQuestionTextView.
            Then we have an int that gets assigned the id of the mQuestionBank's question
            id based on the array index.
            Then we send the id for the Question object's question and send it to mQuestionTextView
            so that it sets the text from that question to mQuestionTextView's textview.
        */
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

        /*
            Just below this comment block, I'm going to make the two private Buttons above
            reference the buttons created in content_quiz by invoking findViewById and passing in
            the button's ids. This is how I "link" in a sense the buttons in Java to the buttons
            made in the xml. Once you do that, the methods will know that the mTrueButton/mFalseButtons
            are referring to the buttons we see on screen, it makes interactivity with those buttons
            possible, like setting listeners and onClick methods as demonstrated below.
        */

        // "Link" the mTrueButton to the true_button in the xml
        mTrueButton = (Button) findViewById(R.id.true_button);
        /*
            Give the mTrueButton some interactivity with an onClick listener.
            The listener is implemented as an anonymous inner class. You're passing in
            an anonymous View.OnClickListener into the setOnClickListener method as a method argument.
            View.OnClickListener contains a function onClick inside it.
         */
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // I want to display an alert so, we'll make one with Snackbar.make().show
                // We're going to pass in the view we're dealing with, v, the string to show,
                // and the length it needs to be on screen.
                // Toasts use context instead of view, probably because they're not reliant
                // on the view unlike Snackbars.
                Snackbar.make(v, R.string.correct_toast, Snackbar.LENGTH_LONG).show();
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Snackbar.make(v, R.string.incorrect_toast, Snackbar.LENGTH_LONG).show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "When you click a FAB, this message comes up, you can't explain that.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
