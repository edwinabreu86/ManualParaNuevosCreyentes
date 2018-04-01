package com.edwin.abreusoft.manualbiblicoparacreyentes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.edwin.abreusoft.manualbiblicoparacreyentes.TextAdapters.VersesAdapter;

public class FavActivity extends AppCompatActivity {

    private String verseRef;
    private RecyclerView recyclerView;

    private LocalBroadcastManager broadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.recycler_view);

        broadcastManager = LocalBroadcastManager.getInstance(this);
        refillRecyclerView(verseRef);
    }

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.filterEquals(new Intent(OptionsDialog.COPY_FILTER))) {
                Snackbar.make(recyclerView, "Texto copiado", Snackbar.LENGTH_SHORT).show();
            } else if (intent.filterEquals(new Intent(OptionsDialog.VERSES_FILTER))) {
                verseRef = intent.getStringExtra(OptionsDialog.VERSES_EXTRA);
                refillRecyclerView(verseRef);
            }
        }
    };


    @Override
    protected void onPause() {
        super.onPause();
        broadcastManager.unregisterReceiver(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        broadcastManager.registerReceiver(receiver, new IntentFilter(OptionsDialog.COPY_FILTER));
        broadcastManager.registerReceiver(receiver, new IntentFilter(OptionsDialog.VERSES_FILTER));
    }

    private void refillRecyclerView(String verseRef) {
        VersesDbHelper dbHelper = new VersesDbHelper(this);
        VersesAdapter adapter = new VersesAdapter(this, dbHelper.getVersesList());
        adapter.notifyDataSetChanged();

        TextView emptyView = findViewById(R.id.empty_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        if(verseRef != null) {
            Snackbar.make(recyclerView, "Versículo "+ verseRef + " removido de favoritos", Snackbar.LENGTH_SHORT).show();
        }

        int visible = (!dbHelper.getVersesList().isEmpty()) ? View.GONE : View.VISIBLE;
        emptyView.setVisibility(visible);
    }
}
