package com.edwin.abreusoft.manualparanuevoscreyentes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.edwin.abreusoft.manualparanuevoscreyentes.Content.Books;
import com.edwin.abreusoft.manualparanuevoscreyentes.Content.Paragraphs;
import com.edwin.abreusoft.manualparanuevoscreyentes.Content.QuestionsAndAnswers;
import com.edwin.abreusoft.manualparanuevoscreyentes.Content.TextsToMemorize;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;

    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawen_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        changeFragmentText(R.string.app_name, Paragraphs.intrContent);

    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_introduction:
                changeFragmentText(R.string.introduction, Paragraphs.intrContent);
                break;
            case R.id.nav_gratitude:
                changeFragmentText(R.string.gratitude, Paragraphs.gratContent);
                break;
            case R.id.nav_first_steps:
                changeFragmentList(R.string.first_steps, QuestionsAndAnswers.questions, QuestionsAndAnswers.answers);
                break;
            case R.id.nav_bible_books:
                changeFragmentBooks(R.string.bible_books, Books.bNames, Books.bAuthors, Books.bChapters, Books.bMeanings);
                break;
            case R.id.nav_memorizing_texts:
                changeFragmentList(R.string.memorizing_texts, TextsToMemorize.textRef, TextsToMemorize.textContent);
                break;
            case R.id.nav_conclusion:
                changeFragmentText(R.string.conclusion, Paragraphs.conclContent);
                break;
            case R.id.nav_new_disciples:
                changeFragmentText(R.string.new_disciples, Paragraphs.newDiscContent);
                break;
            case R.id.nav_credits:
                Intent intent = new Intent(this, CreditsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_exit:
                finish();
        }
        
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeFragmentBooks(int titleRef, String[] bNames, String[] bAuthors, int[] bChapters, String[] bMeanings) {
        String title = getResources().getString(titleRef);

        toolbar.setTitle(title);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.container, BooksFragment.newInstance(bNames, bAuthors, bChapters, bMeanings))
                .commit();
    }

    private void changeFragmentText(int titleRef, String content) {
        String title = getResources().getString(titleRef);

        toolbar.setTitle(title);
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
