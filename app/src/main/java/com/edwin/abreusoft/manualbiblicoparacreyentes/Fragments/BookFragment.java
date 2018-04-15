package com.edwin.abreusoft.manualbiblicoparacreyentes.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edwin.abreusoft.manualbiblicoparacreyentes.Objects.Book;
import com.edwin.abreusoft.manualbiblicoparacreyentes.R;
import com.edwin.abreusoft.manualbiblicoparacreyentes.TextAdapters.BookAdapter;

import java.util.ArrayList;
import java.util.List;

public class BookFragment extends Fragment {

    private static final String BOOK = "book";
    private static final String AUTHOR = "author";
    private static final String CHAPTERS = "chapters";
    private static final String CONTENT = "content";

    private String[] book, author, chapters, content;

    public BookFragment() {
        // Required empty public constructor
    }

    public static BookFragment newInstance(String[] book, String[] author, String[] chapters, String[] content) {
        BookFragment fragment = new BookFragment();
        Bundle args = new Bundle();
        args.putStringArray(BOOK, book);
        args.putStringArray(AUTHOR, author);
        args.putStringArray(CHAPTERS, chapters);
        args.putStringArray(CONTENT, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        book = getArguments().getStringArray(BOOK);
        author = getArguments().getStringArray(AUTHOR);
        chapters = getArguments().getStringArray(CHAPTERS);
        content = getArguments().getStringArray(CONTENT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Book> bookList = new ArrayList<>();

        for (int n = 0; n < book.length; n++) {
            bookList.add(new Book(book[n], author[n], chapters[n], content[n]));
            recyclerView.setAdapter(new BookAdapter(this.getActivity(), bookList));
        }

        return recyclerView;
    }

}
