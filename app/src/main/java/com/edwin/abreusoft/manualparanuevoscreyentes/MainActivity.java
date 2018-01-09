package com.edwin.abreusoft.manualparanuevoscreyentes;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.edwin.abreusoft.manualparanuevoscreyentes.Content.Books;
import com.edwin.abreusoft.manualparanuevoscreyentes.Content.Paragraphs;
import com.edwin.abreusoft.manualparanuevoscreyentes.Content.QuestionsAndAnswers;
import com.edwin.abreusoft.manualparanuevoscreyentes.Content.TextsToMemorize;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String CHECKED = "checked";
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private SharedPreferences prefs;

    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawen_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        changeFragmentList(R.string.first_steps, QuestionsAndAnswers.QUESTIONS, QuestionsAndAnswers.ANSWERS);

        prefs = getPreferences(Context.MODE_PRIVATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!prefs.contains(CHECKED)) {
            createIntroDialog();
        }
    }

    private void createIntroDialog() {
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.intro_dialog_layout, null);
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view, new ViewGroup.LayoutParams(540, ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.setCancelable(true);

        TextView introText = dialog.findViewById(R.id.intro_text);
        introText.setText(Paragraphs.INTR_CONTENT);

        final CheckBox noShowCheck = dialog.findViewById(R.id.no_show_check);
        Button acceptButton = dialog.findViewById(R.id.accept_button);

        dialog.show();

        acceptButton.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_app_rating:
                rateApp(getApplicationContext());
                return true;
            case R.id.menu_credits:
                Intent intent = new Intent(this, CreditsActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_exit:
                finish();
                return true;
        }

        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_gratitude:
                changeFragmentText(R.string.gratitude, Paragraphs.GRAT_CONTENT);
                break;
            case R.id.nav_first_steps:
                changeFragmentList(R.string.first_steps, QuestionsAndAnswers.QUESTIONS, QuestionsAndAnswers.ANSWERS);
                break;
            case R.id.nav_bible_books:
                changeFragmentBooks(R.string.bible_books, Books.B_NAMES, Books.B_AUTHORS, Books.B_CHAPTERS, Books.B_MEANINGS);
                break;
            case R.id.nav_memorizing_texts:
                changeFragmentList(R.string.memorizing_texts, TextsToMemorize.textRef, TextsToMemorize.textContent);
                break;
            case R.id.nav_conclusion:
                changeFragmentText(R.string.conclusion, Paragraphs.CONCL_CONTENT);
                break;
            case R.id.nav_new_disciples:
                changeFragmentText(R.string.new_disciples, Paragraphs.NEW_DISC_CONTENT);
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

    private void changeFragmentBooks(int titleRef, String[] text1, String[] text2, int[] text3, String[] text4) {
        toolbar.setTitle(getResources().getString(R.string.bible_books));
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.container,
                        ItemsFragment.newBook(Books.B_NAMES, Books.B_AUTHORS, Books.B_CHAPTERS, Books.B_MEANINGS))
                .commit();
    }

    private void changeFragmentText(int titleRef, String content) {
        toolbar.setTitle(getResources().getString(titleRef));
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.container, TextFragment.newInstance(content))
                .commit();
    }

    private void changeFragmentList(int titleRef, String[] text1, String[] text2) {
        String title = getResources().getString(titleRef);

        toolbar.setTitle(title);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.container, ItemsFragment.newInstance(text1, text2))
                .commit();
    }
}

