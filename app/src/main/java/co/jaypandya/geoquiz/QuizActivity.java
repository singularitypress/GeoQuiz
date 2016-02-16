package co.jaypandya.geoquiz;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;

    private static final String TAG = "QuizActivity ";

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

    /*
        Then we have an int that gets assigned the id of the mQuestionBank's question
        id based on the array index.
        Then we send the id for the Question object's question and send it to mQuestionTextView
        so that it sets the text from that question to mQuestionTextView's textview.
    */
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    /**/
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        View parentLayout = findViewById(android.R.id.content);
        if(userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Snackbar.make(parentLayout, messageResId, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d(TAG, "onCreate called");

        /*
            Wiring up the TextView.
            Here we link the textview called question_text_view to mQuestionTextView.
        */
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

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
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        // Wiring up  the Next button
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // When clicking the next button, the index increments by one, and changes the textview to the new Question's textview.
                // Also, the % is there so that when you hit 5%5 which is 0, the index starts from the beginning, index 0.
                mCurrentIndex = (mCurrentIndex+1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mPrevButton = (ImageButton) findViewById(R.id.previous_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // only allow to go back if current index is greater than 0, otherwise it'd crash. Can't have a negative index.
                if (mCurrentIndex > 0) {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                    updateQuestion();
                }
            }
        });
        mQuestionTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // When clicking the next button, the index increments by one, and changes the textview to the new Question's textview.
                // Also, the % is there so that when you hit 5%5 which is 0, the index starts from the beginning, index 0.
                mCurrentIndex = (mCurrentIndex+1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        //Having the below here as well as within the nextbutton listener is so that a question appears when you first open the app
        updateQuestion();

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
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
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
