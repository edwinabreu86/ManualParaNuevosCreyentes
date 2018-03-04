package com.edwin.abreusoft.manualbiblicoparacreyentes.Books;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edwin.abreusoft.manualbiblicoparacreyentes.Items.ItemsAdapter;
import com.edwin.abreusoft.manualbiblicoparacreyentes.R;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private final List<Book> booksList;

    public BooksAdapter(List<Book> booksList) {
        this.booksList = booksList;
    }

    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View bookView = inflater.inflate(R.layout.item_layout, parent, false);

        return new ItemsAdapter.ViewHolder(bookView);
    }

    @Override
    public void onBindViewHolder(ItemsAdapter.ViewHolder holder, int position) {
        Book book = booksList.get(position);

        holder.text1.setText(book.getText1());
        holder.text2.setText(String.format("Autor(es): %s\nCapítulos: %s\nDescripción: \n%s",
                book.getText2(), book.getText3(), book.getText4()));
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }
}
