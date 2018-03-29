package com.edwin.abreusoft.manualbiblicoparacreyentes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private final List<Item> itemsList;
    private final Context ctx;

    static class ViewHolder extends RecyclerView.ViewHolder {
        final LinearLayout itemHolder;
        final TextView titleView;
        final TextView contentView;

        ViewHolder(final View itemView) {
            super(itemView);
            itemHolder = itemView.findViewById(R.id.item_holder);
            titleView = itemView.findViewById(R.id.title_view);
            contentView = itemView.findViewById(R.id.content_view);
        }
    }

    ItemsAdapter(Context ctx, List<Item> itemsList) {
        this.ctx = ctx;
        this.itemsList = itemsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Item item = itemsList.get(position);
        final String text1 = item.getText1();
        final String text2;

        if(item.getText3() == null && item.getText4() == null) {
            text2 = item.getText2();
        } else {
            text2 = "Autor(es): " + item.getText2() + "\n"
                    + "Capítulos: " + item.getText3() + "\n"
                    + "Contenido: " + item.getText4();
        }
        holder.titleView.setText(text1);
        holder.contentView.setText(text2);

        final String[] text = {text1, text2};

        holder.itemHolder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                OptionsDialog.createOptionsDialog(ctx, text, false);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}