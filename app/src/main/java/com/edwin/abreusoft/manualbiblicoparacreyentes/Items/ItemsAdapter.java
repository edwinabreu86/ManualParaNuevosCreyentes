package com.edwin.abreusoft.manualbiblicoparacreyentes.Items;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edwin.abreusoft.manualbiblicoparacreyentes.R;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private List<Item> itemsList;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView questionView, answerView;

        ViewHolder(View itemView) {
            super(itemView);
            questionView = itemView.findViewById(R.id.question_view);
            answerView = itemView.findViewById(R.id.answer_view);
        }
    }

    public ItemsAdapter(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_view, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = itemsList.get(position);
        holder.questionView.setText(item.getQuestion());
        holder.answerView.setText(item.getAnswer());
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}
