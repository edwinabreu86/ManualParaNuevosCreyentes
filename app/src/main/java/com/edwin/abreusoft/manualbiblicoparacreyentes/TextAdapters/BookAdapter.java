package com.edwin.abreusoft.manualbiblicoparacreyentes.TextAdapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edwin.abreusoft.manualbiblicoparacreyentes.Objects.Book;
import com.edwin.abreusoft.manualbiblicoparacreyentes.OptionsDialog;
import com.edwin.abreusoft.manualbiblicoparacreyentes.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private final Activity activity;
    private final List<Book> bookList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        final LinearLayout itemHolder;
        final TextView bookView;
        final TextView authorView;
        final TextView chaptersView;
        final TextView contentView;

        ViewHolder(final View itemView) {
            super(itemView);
            itemHolder = itemView.findViewById(R.id.item_holder);
            bookView = itemView.findViewById(R.id.book_view);
            authorView = itemView.findViewById(R.id.author_view);
            chaptersView = itemView.findViewById(R.id.chapters_view);
            contentView = itemView.findViewById(R.id.bcontent_view);
        }
    }

    public BookAdapter(Activity activity, List<Book> bookList) {
        this.activity = activity;
        this.bookList = bookList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_layout, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Book nBook = bookList.get(position);
        final String book = nBook.getBook();
        final String author = nBook.getAuthor();
        final String chapters = nBook.getChapters();
        final String content = nBook.getContent();

        holder.bookView.setText(book);
        holder.authorView.setText(author);
        holder.chaptersView.setText(chapters);
        holder.contentView.setText(content);

        final String reading = "Autor(es): " + author +
                "\nCapítulos: " + chapters +
                "\nContenido: " + content;
        final String[] text = {book, reading};

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
        return bookList.size();
    }
}
