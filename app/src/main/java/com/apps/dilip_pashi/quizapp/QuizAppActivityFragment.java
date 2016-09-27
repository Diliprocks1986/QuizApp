package com.apps.dilip_pashi.quizapp;

/**
 * Created by Dilip_pashi on 25-09-2016.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

public class QuizAppActivityFragment extends Fragment {

    ScrollView scrollView;

    private RadioGroup mRadioGroupQ1;
    private boolean isRadioGroupQ1Scored;
    private RadioGroup mRadioGroupQ2;
    private boolean isRadioGroupQ2Scored;
    private RadioGroup mRadioGroupQ5;
    private boolean isRadioGroupQ5Scored;

    private EditText mEditTextQ3;
    private boolean isEditTextQ3Scored;
    private EditText mEditTextQ4;
    private boolean isEditTextQ4Scored;

    private CheckBox mCheckBoxQ6Opt1;
    private CheckBox mCheckBoxQ6Opt2;
    private CheckBox mCheckBoxQ6Opt3;
    private CheckBox mCheckBoxQ6Opt4;
    private boolean isCheckBoxScored;

    private Button displayButton,resetButton;

    Context mContext;
    int score;
    private static final String LOG_TAG = QuizAppActivityFragment.class.getSimpleName();

    public QuizAppActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quiz_app, container, false);

        initViews(rootView);
        initListeners();
        scrollView.setFocusableInTouchMode(true);
        scrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);

        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edittextQuestions();
                checkboxQuestions();
                Toast.makeText(mContext, "Score " + score, Toast.LENGTH_SHORT).show();
                Log.d(LOG_TAG, "Score " + score);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioGroupQ1.clearCheck();
                mRadioGroupQ2.clearCheck();
                mRadioGroupQ5.clearCheck();
                mCheckBoxQ6Opt1.setChecked(false);
                mCheckBoxQ6Opt2.setChecked(false);
                mCheckBoxQ6Opt3.setChecked(false);
                mCheckBoxQ6Opt4.setChecked(false);
                mEditTextQ3.setText("");
                mEditTextQ4.setText("");
                score = 0;
                Toast.makeText(mContext, "Score Reset to " + score, Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }

    private void initViews(View view) {
        score = 0;
        mContext = view.getContext();
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);


        mRadioGroupQ1 = (RadioGroup) view.findViewById(R.id.radiogrp_q1);
        mRadioGroupQ2 = (RadioGroup) view.findViewById(R.id.radiogrp_q2);
        mRadioGroupQ5 = (RadioGroup) view.findViewById(R.id.radiogrp_q5);

        mEditTextQ3 = (EditText) view.findViewById(R.id.q3_edittext);
        mEditTextQ4 = (EditText) view.findViewById(R.id.q4_edittext);

        mCheckBoxQ6Opt1 = (CheckBox) view.findViewById(R.id.chkbox_q6_opt1);
        mCheckBoxQ6Opt2 = (CheckBox) view.findViewById(R.id.chkbox_q6_opt2);
        mCheckBoxQ6Opt3 = (CheckBox) view.findViewById(R.id.chkbox_q6_opt3);
        mCheckBoxQ6Opt4 = (CheckBox) view.findViewById(R.id.chkbox_q6_opt4);

        displayButton = (Button) view.findViewById(R.id.display_button);
        resetButton = (Button) view.findViewById(R.id.reset_button);

        isRadioGroupQ1Scored = false;
        isRadioGroupQ2Scored = false;
        isRadioGroupQ5Scored = false;

        isEditTextQ3Scored = false;
        isEditTextQ4Scored = false;

        isCheckBoxScored = false;
    }

    private void initListeners() {
        mRadioGroupQ1.setOnCheckedChangeListener(mRadioGroupListener);
        mRadioGroupQ2.setOnCheckedChangeListener(mRadioGroupListener);
        mRadioGroupQ5.setOnCheckedChangeListener(mRadioGroupListener);
    }


    private void edittextQuestions() {
        String edittextQ3 = mEditTextQ3.getText().toString().toLowerCase();
//        Log.d(LOG_TAG, "Question 3 " + edittextQ3);
        String edittextQ4 = mEditTextQ4.getText().toString().toLowerCase();;
//        Log.d(LOG_TAG, "Question 4 " + edittextQ4);


        if (!isEditTextQ3Scored) {
            if (edittextQ3 != null && !edittextQ3.equals("")) {
                String answer = getResources().getString(R.string.Layout).toLowerCase();;
                if (edittextQ3.equals(answer)) {
                    score++;
                    isEditTextQ3Scored = true;
                    Log.d(LOG_TAG, "EditText Q3 Score " + score);
                }
            }
        }

        if (!isEditTextQ4Scored) {
            if (edittextQ4 != null && !edittextQ4.equals("")) {
                String answer = getResources().getString(R.string.Automatically).toLowerCase();;
                if (edittextQ4.equals(answer)) {
                    score++;
                    isEditTextQ4Scored = true;
                    Log.d(LOG_TAG, "EditText Q4 Score " + score);
                }
            }
        }

    }

    private RadioGroup.OnCheckedChangeListener mRadioGroupListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                case R.id.radiobtn_q1_opt3:
                    if (!isRadioGroupQ1Scored) {
                        score++;
                        isRadioGroupQ1Scored = true;
                        Log.d(LOG_TAG, "RadioButton Q1 Score " + score);
                    } else {
                        Log.d(LOG_TAG, "isRadioGroupQ1Scored is " + isRadioGroupQ1Scored);
                    }
                    break;
                case R.id.radiobtn_q2_opt3:
                    if (!isRadioGroupQ2Scored) {
                        score++;
                        isRadioGroupQ2Scored = true;
                        Log.d(LOG_TAG, "RadioButton Q2 Score " + score);
                    }
                    break;
                case R.id.radiobtn_q5_opt2:
                    if (!isRadioGroupQ5Scored) {
                        score++;
                        isRadioGroupQ5Scored = true;
                        Log.d(LOG_TAG, "RadioButton Q5 Score " + score);
                    }
                    break;
            }
        }
    };

    /*
     * This question #6
     */
    private void checkboxQuestions() {
        if ((mCheckBoxQ6Opt1.isChecked() &&
                mCheckBoxQ6Opt2.isChecked() &&
                mCheckBoxQ6Opt3.isChecked() &&
                mCheckBoxQ6Opt4.isChecked() &&
                !isCheckBoxScored)) {
            score++;
            isCheckBoxScored = true;
            Log.d(LOG_TAG, "CheckBox Q6 Score " + score);
        }
    }
}