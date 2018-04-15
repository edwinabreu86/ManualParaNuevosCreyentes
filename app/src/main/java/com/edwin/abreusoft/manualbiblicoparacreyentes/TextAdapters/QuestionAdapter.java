package com.edwin.abreusoft.manualbiblicoparacreyentes.TextAdapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edwin.abreusoft.manualbiblicoparacreyentes.Objects.Question;
import com.edwin.abreusoft.manualbiblicoparacreyentes.OptionsDialog;
import com.edwin.abreusoft.manualbiblicoparacreyentes.R;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private final Activity activity;
    private final List<Question> itemsList;

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

    public QuestionAdapter(Activity activity, List<Question> itemsList) {
        this.activity = activity;
        this.itemsList = itemsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_layout, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Question item = itemsList.get(position);
        final String question = item.getQuestion();
        final String answer = item.getAnswer();

        holder.titleView.setText(question);
        holder.contentView.setText(answer);

        final String[] text = {question, answer};

        holder.itemHolder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                OptionsDialog.createOptionsDialog(activity, text);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}