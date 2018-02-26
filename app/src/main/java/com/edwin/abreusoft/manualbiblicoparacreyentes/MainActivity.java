package com.edwin.abreusoft.manualbiblicoparacreyentes;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.edwin.abreusoft.manualbiblicoparacreyentes.Books.Book;
import com.edwin.abreusoft.manualbiblicoparacreyentes.Books.BooksAdapter;
import com.edwin.abreusoft.manualbiblicoparacreyentes.Books.BooksText;
import com.edwin.abreusoft.manualbiblicoparacreyentes.Items.Item;
import com.edwin.abreusoft.manualbiblicoparacreyentes.Items.ItemsAdapter;
import com.edwin.abreusoft.manualbiblicoparacreyentes.Items.ItemsText;
import com.edwin.abreusoft.manualbiblicoparacreyentes.Verses.Verse;
import com.edwin.abreusoft.manualbiblicoparacreyentes.Verses.VersesAdapter;
import com.edwin.abreusoft.manualbiblicoparacreyentes.Verses.VersesText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String CHECKED = "checked";
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private SharedPreferences prefs;

    private Dialog dialog;
    private TextView textView;
    private RecyclerView recyclerView;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindow().setStatusBarColor(Color.TRANSPARENT);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawen_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        prefs = getPreferences(Context.MODE_PRIVATE);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        textView = findViewById(R.id.text_view);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        showItemList(getString(R.string.general_questions), ItemsText.ITEMS);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (!prefs.contains(CHECKED)) {
            showIntroDialog(R.layout.intro_dialog);
        }
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            showExitDialog();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_app_rating:
                rateApp(getApplicationContext());
                return true;
            case R.id.menu_about_author:
                startActivity(new Intent(this, AuthorActivity.class));
                return true;
            case R.id.menu_credits:
                showCreditsDialog(R.layout.credits_dialog);
                return true;
            case R.id.menu_exit:
                showExitDialog();
                return true;
        }

        return false;
    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseas salir de la aplicación?")
                .setCancelable(true)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create()
                .show();
    }

    private void showIntroDialog(int layoutRes) {
        dialog.setContentView(getLayoutInflater().inflate(layoutRes, null),
                new ViewGroup.LayoutParams(540, ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.show();

        TextView introText = dialog.findViewById(R.id.intro_text);
        introText.setText(Paragraphs.INTR_CONTENT);

        final CheckBox noShowCheck = dialog.findViewById(R.id.no_show_check);
        Button closeButton = dialog.findViewById(R.id.close_button);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ApplySharedPref")
            @Override
            public void onClick(View view) {
                if(noShowCheck.isChecked()) {
                    prefs.edit().putBoolean(CHECKED, true).commit();
                }
                dialog.dismiss();
            }
        });

    }

    private void showCreditsDialog(int layoutRes) {
        dialog.setContentView(getLayoutInflater().inflate(layoutRes, null),
                new ViewGroup.LayoutParams(540, ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.show();

        Button closeButton = dialog.findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setCheckable(true);
        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.nav_gratitude:
                showText(R.string.gratitude, Paragraphs.GRAT_CONTENT);
                break;
            case R.id.nav_general_questions:
                showItemList(getString(R.string.general_questions), ItemsText.ITEMS);
                break;
            case R.id.nav_ot_books:
                showBooksList(getString(R.string.ot_books), BooksText.OT_BOOKS);
                break;
            case R.id.nav_nt_books:
                showBooksList(getString(R.string.nt_books), BooksText.NT_BOOKS);
                break;
            case R.id.vers_ot:
                showVersesList(getString(R.string.ot_verses), VersesText.OT_VERSES);
                break;
            case R.id.vers_nt:
                showVersesList(getString(R.string.nt_verses), VersesText.NT_VERSES);
                break;
            case R.id.nav_conclusion:
                showText(R.string.conclusion, Paragraphs.CONCL_CONTENT);
                break;
            case R.id.nav_new_disciples:
                showText(R.string.new_disciples, Paragraphs.NEW_DISC_CONTENT);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void rateApp(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    private void showText(int titleRef, String content) {
        toolbar.setTitle(getResources().getString(titleRef));

        recyclerView.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);
        textView.setText(content);
    }

    private void showItemList(String title, String[][] content) {
        toolbar.setTitle(title);
        List<Item> itemList = new ArrayList<>();

        for (String[] aContent : content) {
            Item item = new Item(aContent[0], aContent[1]);
            itemList.add(item);
        }

        textView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(new ItemsAdapter(itemList));
    }

    private void showVersesList(String title, String[][] content) {
        toolbar.setTitle(title);

        List<Verse> verseList = new ArrayList<>();

        for (String[] aContent : content) {
            Verse verse = new Verse(aContent[0], aContent[1], aContent[2]);
            verseList.add(verse);
        }

        textView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(new VersesAdapter(verseList));
    }

    private void showBooksList(String title, String[][] content) {
        toolbar.setTitle(title);

        List<Book> bookList = new ArrayList<>();

        for (String[] aContent : content) {
            Book book = new Book(aContent[0], aContent[1], aContent[2], aContent[3]);
            bookList.add(book);
        }

        textView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(new BooksAdapter(bookList));
    }
}
