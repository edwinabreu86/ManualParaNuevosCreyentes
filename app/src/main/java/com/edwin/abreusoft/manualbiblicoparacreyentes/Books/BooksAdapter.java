package com.edwin.abreusoft.manualbiblicoparacreyentes.Books;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edwin.abreusoft.manualbiblicoparacreyentes.R;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private final List<Book> booksList;

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView titleView;
        final TextView authorView;
        final TextView chaptersView;
        final TextView descriptionView;

        ViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.title_view);
            authorView = itemView.findViewById(R.id.author_view);
            chaptersView = itemView.findViewById(R.id.chapters_view);
            descriptionView = itemView.findViewById(R.id.description_view);
        }
    }

    public BooksAdapter(List<Book> booksList) {
        this.booksList = booksList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View bookView = inflater.inflate(R.layout.book_view, parent, false);

        return new ViewHolder(bookView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book = booksList.get(position);
        holder.titleView.setText(book.getTitle());
        holder.authorView.setText("Autor(es): " + book.getAuthor());
        holder.chaptersView.setText("Capítulos: " + book.getChapters());
        holder.descriptionView.setText("Descripción: " + book.getDescription());
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

}
