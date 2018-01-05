package com.edwin.abreusoft.manualparanuevoscreyentes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<TextItem> {

    public ItemAdapter(@NonNull Context context, @NonNull List<TextItem> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextItem item = getItem(position);
        ViewHolder holder = new ViewHolder();

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        holder.itemView1 = (TextView) convertView.findViewById(R.id.item_question);
        holder.itemView2 = (TextView) convertView.findViewById(R.id.item_answer);

        assert item != null;
        holder.itemView1.setText(item.getText1());
        holder.itemView2.setText(item.getText2());

        return convertView;
    }

    private static class ViewHolder {
        TextView itemView1;
        TextView itemView2;
    }
}
