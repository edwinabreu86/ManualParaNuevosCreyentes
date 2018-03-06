package com.edwin.abreusoft.manualbiblicoparacreyentes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private final List<Item> itemsList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView text1;
        final TextView text2;

        ViewHolder(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);
        }
    }

    ItemsAdapter(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = itemsList.get(position);
        String text1 = item.getText1();
        String text2 = item.getText2();
        String text3 = item.getText3();
        String text4 = item.getText4();

        if(text3 == null && text4 == null) {
            holder.text1.setText(text1);
            holder.text2.setText(text2);
        } else if(text3 != null && text4 == null) {
            holder.text1.setText(String.format("%s %s", text1, text2));
            holder.text2.setText(item.getText3());
        } else {
            holder.text1.setText(text2);
            holder.text2.setText(String.format("Autor(es): %s\nCapítulos: %s\nDescripción: \n%s",
                    text2, text3, text4));
        }
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}
