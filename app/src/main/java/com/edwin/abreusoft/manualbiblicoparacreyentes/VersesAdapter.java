package com.edwin.abreusoft.manualbiblicoparacreyentes;

import android.content.Context;
import android.view.View;

import java.util.List;

public class VersesAdapter extends ItemsAdapter {

    private final Context ctx;
    private final List<Item> versesList;

    VersesAdapter(Context ctx, List<Item> versesList) {
        super(ctx, versesList);
        this.ctx = ctx;
        this.versesList = versesList;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Item verse = versesList.get(position);
        final String[] text = {verse.getText1(), verse.getText2(), verse.getText3()};

        holder.titleView.setText(String.format("%s %s", text[0], text[1]));
        holder.contentView.setText(text[2]);

        holder.itemHolder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(!verse.isFavorite()) {
                    OptionsDialog.createOptionsDialog(ctx, text, false);
                } else {
                    OptionsDialog.createOptionsDialog(ctx, text, true);
                }
                return false;
            }
        });
    }
}