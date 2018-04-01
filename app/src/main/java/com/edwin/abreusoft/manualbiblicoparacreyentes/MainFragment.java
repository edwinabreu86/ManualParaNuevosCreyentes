package com.edwin.abreusoft.manualbiblicoparacreyentes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edwin.abreusoft.manualbiblicoparacreyentes.TextAdapters.ItemsAdapter;
import com.edwin.abreusoft.manualbiblicoparacreyentes.TextAdapters.VersesAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private static final String TEXT1 = "text1";
    private static final String TEXT2 = "text2";
    private static final String TEXT3 = "text3";
    private static final String TEXT4 = "text4";

    private String[] text1, text2, text3, text4;

    public MainFragment() {
        // Required empty public constructor
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
        text1 = getArguments().getStringArray(TEXT1);
        text2 = getArguments().getStringArray(TEXT2);

        if (getArguments() != null) {
            text3 = getArguments().getStringArray(TEXT3);
            text4 = getArguments().getStringArray(TEXT4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Item> itemsList = new ArrayList<>();

        if(text3 != null && text4 == null) {
            for (int n = 0; n < text1.length; n++) {
                itemsList.add(new Item(text1[n], text2[n], text3[n], false));
            }
            recyclerView.setAdapter(new VersesAdapter(getContext(), itemsList));
        } else {
            for (int n = 0; n < text1.length; n++) {
                if (text3 == null && text4 == null) {
                    itemsList.add(new Item(text1[n], text2[n]));
                } else {
                    assert text3 != null;
                    itemsList.add(new Item(text1[n], text2[n], text3[n], text4[n]));
                }
                recyclerView.setAdapter(new ItemsAdapter(getContext(), itemsList));
            }
        }
        return recyclerView;
    }
}