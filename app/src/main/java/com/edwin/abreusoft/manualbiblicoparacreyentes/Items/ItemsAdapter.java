package com.edwin.abreusoft.manualbiblicoparacreyentes.Items;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edwin.abreusoft.manualbiblicoparacreyentes.R;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private final List<Item> itemsList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView text1;
        public final TextView text2;

        public ViewHolder(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);
        }
    }

    public ItemsAdapter(List<Item> itemsList) {
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
        holder.text1.setText(item.getText1());
        holder.text2.setText(item.getText2());
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}
