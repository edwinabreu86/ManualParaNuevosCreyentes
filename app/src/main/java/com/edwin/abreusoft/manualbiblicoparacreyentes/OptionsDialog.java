package com.edwin.abreusoft.manualbiblicoparacreyentes;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.edwin.abreusoft.manualbiblicoparacreyentes.Objects.Verse;

public class OptionsDialog{

    static final String COPY_FILTER = "com.abreusoft.COPY_INTENT";
    public static final String VERSES_FILTER = "com.abreusoft.VERSE_INTENT";
    static final String VERSES_EXTRA = "verses_extra";

    static Dialog createDialog(Activity activity, int layoutId) {
        activity.getResources();
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(LayoutInflater.from(activity).inflate(layoutId, null),
                new ViewGroup.LayoutParams(width * 4/5, ViewGroup.LayoutParams.WRAP_CONTENT));
        return dialog;
    }

    public static void createOptionsDialog(final Activity activity, final String[] text) {
        final Dialog dialog = createDialog(activity, R.layout.options_dialog);
        dialog.show();

        final TextView titleView = dialog.findViewById(R.id.opt_title);
        final TextView contentView = dialog.findViewById(R.id.opt_content);
        ImageView imgCopy = dialog.findViewById(R.id.img_copy);
        ImageView imgShare = dialog.findViewById(R.id.img_share);

        titleView.setText(text[0]);
        contentView.setText(text[1]);

        final LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(activity.getApplicationContext());

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.img_copy:
                        ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                        if (clipboard != null) {
                            clipboard.setPrimaryClip(ClipData.newPlainText("label", text[0] + "\n" + text[1]));
                        }
                        broadcastManager.sendBroadcast(new Intent(COPY_FILTER));
                        break;
                    case R.id.img_share:
                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, text[0]);
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, text[0] + "\n" + text[1]);
                        activity.startActivity(Intent.createChooser(sharingIntent, "Compartir con: "));
                        break;
                }
                dialog.dismiss();
            }
        };

        imgCopy.setOnClickListener(listener);
        imgShare.setOnClickListener(listener);
    }

    public static void createOptionsDialog(final Activity activity, final String[] text, final boolean favorite) {
        final Dialog dialog = createDialog(activity, R.layout.options_dialog);
        dialog.show();

        final TextView titleView = dialog.findViewById(R.id.opt_title);
        final TextView contentView = dialog.findViewById(R.id.opt_content);
        final ImageView imgCopy = dialog.findViewById(R.id.img_copy);
        ImageView imgShare = dialog.findViewById(R.id.img_share);
        ImageView imgFav = dialog.findViewById(R.id.img_fav);

        imgFav.setVisibility(View.VISIBLE);

        int resId = (favorite) ? R.drawable.ic_remove : R.drawable.ic_favorite;
        imgFav.setImageResource(resId);

        titleView.setText(String.format("%s %s", text[0], text[1]));
        contentView.setText(text[2]);

        final String title = titleView.getText().toString();
        final String content = contentView.getText().toString();
        final VersesDbHelper dbHelper = new VersesDbHelper(activity);

        final LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(activity.getApplicationContext());

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.img_copy:
                        ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                        if (clipboard != null) {
                            clipboard.setPrimaryClip(ClipData.newPlainText("label", title + "\n" + content));
                        }
                        broadcastManager.sendBroadcast(new Intent(COPY_FILTER));
                        break;
                    case R.id.img_share:
                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, title);
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, title + "\n" + content);
                        activity.startActivity(Intent.createChooser(sharingIntent, "Compartir con: "));
                        break;
                    case R.id.img_fav:
                        if(!favorite) {
                            dbHelper.addVerse(new Verse(text[0], text[1], text[2], true));
                        } else {
                            dbHelper.removeVerse(new Verse(text[0], text[1], text[2], false));
                        }
                        broadcastManager.sendBroadcast(new Intent(VERSES_FILTER).putExtra(VERSES_EXTRA, title));
                        break;
                }
                dialog.dismiss();
            }
        };

        imgCopy.setOnClickListener(listener);
        imgShare.setOnClickListener(listener);
        imgFav.setOnClickListener(listener);
    }
}