package com.edwin.abreusoft.manualparanuevoscreyentes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class BooksFragment extends Fragment {

    private static final String B_NAME = "bName";
    private static final String B_AUTHORS = "bAuthors";
    private static final String B_CHAPTERS = "bCHAPTERS";
    private static final String B_MEANING = "bMEANING";

    private String[] name;
    private String[] authors;
    private int[] chapters;
    private String[] meaning;

    public BooksFragment() {
        // Required empty public constructor
    }

    public static BooksFragment newInstance(String[] name, String[] authors, int[] chapters, String[] meaning) {
        Bundle b = new Bundle();
        b.putStringArray(B_NAME, name);
        b.putStringArray(B_AUTHORS, authors);
        b.putIntArray(B_CHAPTERS, chapters);
        b.putStringArray(B_MEANING, meaning);

        BooksFragment bFrag = new BooksFragment();
        bFrag.setArguments(b);
        return (bFrag);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null) {
            name = savedInstanceState.getStringArray(B_NAME);
            authors = savedInstanceState.getStringArray(B_AUTHORS);
            chapters = savedInstanceState.getIntArray(B_CHAPTERS);
            meaning = savedInstanceState.getStringArray(B_MEANING);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);

        onCreate(getArguments());

        ArrayList<BookItem> booksList = new ArrayList<>();
        for(int i = 0; i < name.length; i++) {
            booksList.add(new BookItem(name[i], authors[i], chapters[i], meaning[i]));
        }

        BooksAdapter adapter = new BooksAdapter(getActivity(), booksList);

        ListView listView = view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        return view;
    }

}
