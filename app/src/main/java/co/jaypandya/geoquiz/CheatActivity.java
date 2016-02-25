package co.jaypandya.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    // This string value is the key in a key/value pair that gets sent to iIntent.putExtra in order
    // communicate data between activities.
    private static final String EXTRA_ANSWER_IS_TRUE = "co.jaypandya.geoquiz.answer_is_true";
    public static Intent newIntent(Context packageContext, boolean answerIsTrue){
        Intent iIntent = new Intent(packageContext, CheatActivity.class);
        iIntent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return iIntent;
    }

    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Note that Activity.getIntent() always returns the Intent that started he activity. In this case, QuizActivity.
        // This was what I sent when calling startActivity(Intent)
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);

        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mAnswerIsTrue)
                    mAnswerTextView.setText(R.string.true_button);
                else
                    mAnswerTextView.setText(R.string.false_button);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This didn't do anything in the Main Activity,\n why would you think it does anything here?", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
