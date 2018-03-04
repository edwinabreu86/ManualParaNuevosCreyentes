package com.edwin.abreusoft.manualbiblicoparacreyentes.Verses;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edwin.abreusoft.manualbiblicoparacreyentes.Items.ItemsAdapter;
import com.edwin.abreusoft.manualbiblicoparacreyentes.R;

import java.util.List;

public class VersesAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private final List<Verse> versesList;

    public VersesAdapter(List<Verse> versesList) {
        this.versesList = versesList;
    }

    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View verse = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItemsAdapter.ViewHolder(verse);
    }

    @Override
    public void onBindViewHolder(ItemsAdapter.ViewHolder holder, int position) {
        Verse verse = versesList.get(position);
        holder.text1.setText(String.format("%s %s", verse.getText1(), verse.getText2()));
        holder.text2.setText(verse.getText3());
    }

    @Override
    public int getItemCount() {
        return versesList.size();
    }
}

