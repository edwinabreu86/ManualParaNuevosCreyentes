package com.edwin.abreusoft.manualbiblicoparacreyentes;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private final List<Item> itemsList;
    private Context ctx;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener,
            MenuItem.OnMenuItemClickListener {
        final TextView textView1;
        final TextView textView2;
        // final View layer;

        ViewHolder(final View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.text1);
            textView2 = itemView.findViewById(R.id.text2);
            // layer = itemView.findViewById(R.id.layer);

            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    // layer.setVisibility(View.VISIBLE);
                    return false;
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            MenuItem menuCopy = contextMenu.add(Menu.NONE, R.id.menu_copy, Menu.NONE, "Copiar");
            MenuItem menuShare = contextMenu.add(Menu.NONE, R.id.menu_share, Menu.NONE, "Compartir");
            // MenuItem menuBookmark = contextMenu.add(Menu.NONE, R.id.menu_bookmark, Menu.NONE, "Etiquetar");

            menuCopy.setOnMenuItemClickListener(this);
            menuShare.setOnMenuItemClickListener(this);
            // menuBookmark.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String main = textView1.getText().toString();
            String content = textView2.getText().toString();
            String text = main + "\n" + content;

            switch (menuItem.getItemId()) {
                case R.id.menu_copy:
                    ClipboardManager clipboard = (ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE);
                    if (clipboard != null) {
                        clipboard.setPrimaryClip(ClipData.newPlainText("label", text));
                    }
                    // layer.setVisibility(View.GONE);

                    Snackbar.make(itemView, "Texto copiado", Snackbar.LENGTH_SHORT).show();
                    return true;
                case R.id.menu_share:
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.putExtra(Intent.EXTRA_TEXT, text);
                    i.setType("text/plain");
                    ctx.startActivity(i);

                    // layer.setVisibility(View.GONE);
                    return true;
                    /*
                case R.id.menu_bookmark:
                    textView1.setTextColor(ctx.getResources().getColor(android.R.color.white));
                    textView2.setBackgroundColor(ctx.getResources().getColor(android.R.color.background_light));
                    return true;
                    */
            }
            return false;
        }
    }

    ItemsAdapter(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ctx = parent.getContext();

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Item item = itemsList.get(position);

        if(item.getText3() == null && item.getText4() == null) {
            holder.textView1.setText(item.getText1());
            holder.textView2.setText(item.getText2());
        } else if(item.getText3() != null && item.getText4() == null) {
            holder.textView1.setText(String.format("%s %s", item.getText1(), item.getText2()));
            holder.textView2.setText(item.getText3());
        } else {
            holder.textView1.setText(item.getText1());
            holder.textView2.setText(String.format("Autor(es): %s\nCapítulos: %s\nDescripción: \n%s",
                    item.getText2(), item.getText3(), item.getText4()));
        }
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}
