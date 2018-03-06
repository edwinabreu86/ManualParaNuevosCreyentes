package com.edwin.abreusoft.manualbiblicoparacreyentes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private static final String TEXT1 = "text1";
    private static final String TEXT2 = "text2";
    private static final String TEXT3 = "text3";
    private static final String TEXT4 = "text4";

    private String[] text1;
    private String[] text2;
    private String[] text3;
    private String[] text4;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String[] text1, String[] text2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putStringArray(TEXT1, text1);
        args.putStringArray(TEXT2, text2);
        fragment.setArguments(args);
        return fragment;
    }

    public static MainFragment newInstance(String[] text1, String[] text2, String[] text3) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putStringArray(TEXT1, text1);
        args.putStringArray(TEXT2, text2);
        args.putStringArray(TEXT3, text3);
        fragment.setArguments(args);
        return fragment;
    }
    
    public static MainFragment newInstance(String[] text1, String[] text2, String[] text3, String[] text4) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putStringArray(TEXT1, text1);
        args.putStringArray(TEXT2, text2);
        args.putStringArray(TEXT3, text3);
        args.putStringArray(TEXT4, text4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            text1 = getArguments().getStringArray(TEXT1);
            text2 = getArguments().getStringArray(TEXT2);
            text3 = getArguments().getStringArray(TEXT3);
            text4 = getArguments().getStringArray(TEXT4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_main, container, false);

        List<Item> itemsList = new ArrayList<>();
        List<Item> versesList = new ArrayList<>();
        List<Item> booksList = new ArrayList<>();
        
        if(text3 == null && text4 == null) {
            for(int n = 0; n < text1.length; n++) {
                itemsList.add(new Item(text1[n], text2[n]));
                ItemsAdapter itemAdapter = new ItemsAdapter(itemsList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(itemAdapter);
            }
        } else if(text3 != null && text4 == null) {
            for(int n = 0; n < text1.length; n++) {
                versesList.add(new Item(text1[n], text2[n], text3[n]));
                ItemsAdapter versesAdapter = new ItemsAdapter(versesList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(versesAdapter);
            }
        } else {
            for(int n = 0; n < text1.length; n++) {
                assert text3 != null;
                booksList.add(new Item(text1[n], text2[n], text3[n], text4[n]));
                ItemsAdapter booksAdapter = new ItemsAdapter(booksList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(booksAdapter);
            }
        }
        
        return recyclerView;
    }
}

