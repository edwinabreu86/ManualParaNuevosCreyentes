package com.edwin.abreusoft.manualparanuevoscreyentes;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class ItemsFragment extends Fragment {

    private static final String TEXT1 = "text1";
    private static final String TEXT2 = "text2";
    private static final String TEXT3 = "text3";
    private static final String TEXT4 = "text4";

    private String[] text1;
    private String[] text2;
    private int[] text3;
    private String[] text4;

    public ItemsFragment() {
        // Required empty public constructor
    }

    public static ItemsFragment newInstance(String[] text1, String[] text2) {
        Bundle b = new Bundle();
        b.putStringArray(TEXT1, text1);
        b.putStringArray(TEXT2, text2);

        ItemsFragment iFrag = new ItemsFragment();
        iFrag.setArguments(b);
        return iFrag;
    }

    public static ItemsFragment newBook(String[] text1, String[] text2, int[] text3, String[] text4) {
        Bundle b = new Bundle();
        b.putStringArray(TEXT1, text1);
        b.putStringArray(TEXT2, text2);
        b.putIntArray(TEXT3, text3);
        b.putStringArray(TEXT4, text4);

        ItemsFragment bFrag = new ItemsFragment();
        bFrag.setArguments(b);
        return bFrag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null) {
            text1 = savedInstanceState.getStringArray(TEXT1);
            text2 = savedInstanceState.getStringArray(TEXT2);
            text3 = savedInstanceState.getIntArray(TEXT3);
            text4 = savedInstanceState.getStringArray(TEXT4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);

        String name = getString(R.string.name) + "\b";
        String authors = getString(R.string.authors) + "\b";
        String chapters = getString(R.string.chapters) + "\b";
        String meaning = getString(R.string.meaning) + "\b";

        onCreate(getArguments());

        ArrayList<TextItem> itemsList = new ArrayList<>();
        for(int i = 0; i < text1.length; i++) {
            if(text3 != null && text4 != null) {
                itemsList.add(new TextItem(name + text1[i],
                        authors + text2[i] + "\n"
                        + chapters + text3[i] + "\n"
                        + meaning + text4[i]));
            } else {
                itemsList.add(new TextItem(text1[i], text2[i]));
            }
        }

        ItemAdapter adapter = new ItemAdapter(getActivity(), itemsList);
        ListView listView = view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        return view;
    }
}
