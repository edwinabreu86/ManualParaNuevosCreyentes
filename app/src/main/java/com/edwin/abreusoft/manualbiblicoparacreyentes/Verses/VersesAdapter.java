package com.edwin.abreusoft.manualbiblicoparacreyentes.Verses;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edwin.abreusoft.manualbiblicoparacreyentes.R;

import java.util.List;

public class VersesAdapter extends RecyclerView.Adapter<VersesAdapter.ViewHolder> {

    private final List<Verse> versesList;

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView bookView;
        final TextView verseView;
        final TextView contentView;

        ViewHolder(View itemView) {
            super(itemView);
            bookView = itemView.findViewById(R.id.book_view);
            verseView = itemView.findViewById(R.id.verse_view);
            contentView = itemView.findViewById(R.id.content_view);
        }
    }

    public VersesAdapter(List<Verse> versesList) {
        this.versesList = versesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View verseView = inflater.inflate(R.layout.verse_view, parent, false);
        return new ViewHolder(verseView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Verse verse = versesList.get(position);
        holder.bookView.setText(verse.getBook());
        holder.verseView.setText(verse.getVerse());
        holder.contentView.setText(verse.getContent());
    }

    @Override
    public int getItemCount() {
        return versesList.size();
    }
}

