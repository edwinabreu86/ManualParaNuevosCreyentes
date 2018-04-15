package com.edwin.abreusoft.manualbiblicoparacreyentes.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edwin.abreusoft.manualbiblicoparacreyentes.Objects.Question;
import com.edwin.abreusoft.manualbiblicoparacreyentes.R;
import com.edwin.abreusoft.manualbiblicoparacreyentes.TextAdapters.QuestionAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuestionFragment extends Fragment {

    private static final String QUESTION = "question";
    private static final String ANSWER = "answer";

    private String[] question;
    private String[] answer;

    public QuestionFragment() {
        // Required empty public constructor
    }

    public static QuestionFragment newInstance(String[] question, String[] answer) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putStringArray(QUESTION, question);
        args.putStringArray(ANSWER, answer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        question = getArguments().getStringArray(QUESTION);
        answer = getArguments().getStringArray(ANSWER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Question> questionList = new ArrayList<>();

        for (int n = 0; n < question.length; n++) {
            questionList.add(new Question(question[n], answer[n]));
            recyclerView.setAdapter(new QuestionAdapter(this.getActivity(), questionList));
        }
        return recyclerView;
    }
}