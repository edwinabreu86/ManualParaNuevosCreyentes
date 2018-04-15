package com.edwin.abreusoft.manualbiblicoparacreyentes.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edwin.abreusoft.manualbiblicoparacreyentes.R;
import com.edwin.abreusoft.manualbiblicoparacreyentes.TextAdapters.VerseAdapter;
import com.edwin.abreusoft.manualbiblicoparacreyentes.Objects.Verse;

import java.util.ArrayList;
import java.util.List;

public class VerseFragment extends Fragment {

    private static final String BOOK = "book";
    private static final String VERSE = "verse";
    private static final String CONTENT = "content";

    private String[] book, verse, content;

    public VerseFragment() {
        // Required empty public constructor
    }

    public static VerseFragment newInstance(String[] book, String[] verse, String[] content) {
        VerseFragment fragment = new VerseFragment();
        Bundle args = new Bundle();
        args.putStringArray(BOOK, book);
        args.putStringArray(VERSE, verse);
        args.putStringArray(CONTENT, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        book = getArguments().getStringArray(BOOK);
        verse = getArguments().getStringArray(VERSE);
        content = getArguments().getStringArray(CONTENT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Verse> verseList = new ArrayList<>();
        for (int n = 0; n < book.length; n++) {
            verseList.add(new Verse(book[n], verse[n], content[n], false));
            recyclerView.setAdapter(new VerseAdapter(this.getActivity(), verseList));
        }
        return recyclerView;
    }
}
