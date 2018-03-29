package com.edwin.abreusoft.manualbiblicoparacreyentes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private String[] text1, text2, text3, text4;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String[] text1, String[] text2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putStringArray("text1", text1);
        args.putStringArray("text2", text2);
        fragment.setArguments(args);
        return fragment;
    }

    public static MainFragment newInstance(String[] text1, String[] text2, String[] text3) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putStringArray("text1", text1);
        args.putStringArray("text2", text2);
        args.putStringArray("text3", text3);
        fragment.setArguments(args);
        return fragment;
    }

    public static MainFragment newInstance(String[] text1, String[] text2, String[] text3, String[] text4) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putStringArray("text1", text1);
        args.putStringArray("text2", text2);
        args.putStringArray("text3", text3);
        args.putStringArray("text4", text4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            text1 = getArguments().getStringArray("text1");
            text2 = getArguments().getStringArray("text2");
            text3 = getArguments().getStringArray("text3");
            text4 = getArguments().getStringArray("text4");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_main, container, false);
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registerForContextMenu(view);
    }
}