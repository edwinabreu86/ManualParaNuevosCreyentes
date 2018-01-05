package com.edwin.abreusoft.manualparanuevoscreyentes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BooksAdapter extends ArrayAdapter<BookItem> {

    public BooksAdapter(@NonNull Context context, @NonNull List<BookItem> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        BookItem item = getItem(position);
        BooksAdapter.ViewHolder holder = new BooksAdapter.ViewHolder();

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.book_item, parent, false);
        }

        holder.bookName = (TextView) convertView.findViewById(R.id.book_name);
        holder.bookAuthors = (TextView) convertView.findViewById(R.id.book_authors);
        holder.bookChapters = (TextView) convertView.findViewById(R.id.book_chapters);
        holder.bookMeaning = (TextView) convertView.findViewById(R.id.book_meaning);

        assert item != null;
        holder.bookName.setText(item.getName());
        holder.bookAuthors.setText(item.getAuthors());
        holder.bookChapters.setText(""+ item.getChapters());
        holder.bookMeaning.setText(item.getMeaning());

        return convertView;
    }

    private static class ViewHolder {
        TextView bookName;
        TextView bookAuthors;
        TextView bookChapters;
        TextView bookMeaning;
    }
}
