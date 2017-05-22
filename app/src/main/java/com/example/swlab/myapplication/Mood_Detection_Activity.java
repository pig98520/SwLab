package com.example.swlab.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;


public class Mood_Detection_Activity extends AppCompatActivity{

    private QuestionBank mQuestionLibrary = new QuestionBank();
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;

    private String mAnswer;
    private int mQuestionNumber = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_detection);
        mQuestionView = (TextView) findViewById(R.id.question);
        mButtonChoice1 = (Button) findViewById(R.id.choice1);
        mButtonChoice2 = (Button) findViewById(R.id.choice2);
        mButtonChoice3 = (Button) findViewById(R.id.choice3);
        mButtonChoice4 = (Button) findViewById(R.id.choice4);
        updateQuestion();
    }

        private void updateQuestion(){

            if (mQuestionNumber<mQuestionLibrary.getLength() ){

                mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
                mButtonChoice1.setText(mQuestionLibrary.getChoice(mQuestionNumber, 1));
                mButtonChoice2.setText(mQuestionLibrary.getChoice(mQuestionNumber, 2));
                mButtonChoice3.setText(mQuestionLibrary.getChoice(mQuestionNumber, 3));
                mButtonChoice4.setText(mQuestionLibrary.getChoice(mQuestionNumber, 4));
                mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
                mQuestionNumber++;
            }



    }
}
