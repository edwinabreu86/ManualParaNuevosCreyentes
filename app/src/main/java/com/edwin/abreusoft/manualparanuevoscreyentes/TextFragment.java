package com.edwin.abreusoft.manualparanuevoscreyentes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TextFragment extends Fragment {

    private static final String CONTENT = "content";

    private String content;

    public static TextFragment newInstance(String content) {
        Bundle b = new Bundle();
        b.putString(CONTENT, content);

        TextFragment tFrag = new TextFragment();
        tFrag.setArguments(b);
        return (tFrag);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            content = savedInstanceState.getString(CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_text, container, false);
        onCreate(getArguments());

        TextView contentView = (TextView) rootView.findViewById(R.id.content_view);
        contentView.setText(content);

        return rootView;
    }
}