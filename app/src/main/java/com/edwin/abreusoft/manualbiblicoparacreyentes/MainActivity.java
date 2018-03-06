package com.edwin.abreusoft.manualbiblicoparacreyentes;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

import com.edwin.abreusoft.manualbiblicoparacreyentes.SectionsAdapter.SectionsNTBooksAdapter;
import com.edwin.abreusoft.manualbiblicoparacreyentes.SectionsAdapter.SectionsOTBooksAdapter;
import com.edwin.abreusoft.manualbiblicoparacreyentes.SectionsAdapter.SectionsItemsAdapter;
import com.edwin.abreusoft.manualbiblicoparacreyentes.SectionsAdapter.SectionsNTVersesAdapter;
import com.edwin.abreusoft.manualbiblicoparacreyentes.SectionsAdapter.SectionsOTVersesAdapter;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String CHECKED = "checked";
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DrawerLayout drawer;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        showList(R.string.general_questions, 0, new SectionsItemsAdapter(getSupportFragmentManager()));
    }


    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs= getPreferences(MODE_PRIVATE);

        if (!prefs.contains(CHECKED)) {
            showIntro();
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

    private void showIntro() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(LayoutInflater.from(this).inflate(R.layout.intro_dialog, null),
                new ViewGroup.LayoutParams(540, ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.show();

        final CheckBox noIntro = dialog.findViewById(R.id.no_show_check);

        Button closeDialog = dialog.findViewById(R.id.close_dialog);
        closeDialog.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ApplySharedPref")
            @Override
            public void onClick(View view) {
                SharedPreferences prefs= getPreferences(MODE_PRIVATE);
                if (noIntro.isChecked()) {
                    prefs.edit().putBoolean(CHECKED, true).commit();
                }
                dialog.dismiss();
            }
        });
    }

    private void showCredits() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(LayoutInflater.from(this).inflate(R.layout.credits_dialog, null),
                new ViewGroup.LayoutParams(540, ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.show();

        Button closeDialog = dialog.findViewById(R.id.close_dialog);
        closeDialog.setOnClickListener(new View.OnClickListener() {
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
        int id = item.getItemId();

        FragmentManager fm = getSupportFragmentManager();

        if(id == R.id.nav_general_questions) {
            showList(R.string.general_questions, 0, new SectionsItemsAdapter(fm));
        } else if (id == R.id.nav_ot_books) {
            showList(R.string.bible_books, R.string.old_testament, new SectionsOTBooksAdapter(fm));
        } else if (id == R.id.nav_nt_books) {
            showList(R.string.bible_books, R.string.new_testament, new SectionsNTBooksAdapter(fm));
        } else if (id == R.id.nav_ot_verses) {
            showList(R.string.verses_for_you, R.string.old_testament, new SectionsOTVersesAdapter(fm));
        } else if (id == R.id.nav_nt_verses) {
            showList(R.string.verses_for_you, R.string.new_testament, new SectionsNTVersesAdapter(fm));
        } else if (id == R.id.menu_app_rating) {
            rateApp();
        } else if(id == R.id.menu_credits) {
            showCredits();
        } else {
            finish();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void rateApp() {
        Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);

        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
        }
    }

    private void showList(int titleId, int subtitleId, PagerAdapter adapter) {
        toolbar.setTitle(getString(titleId));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        if(titleId == R.string.general_questions) {
            tabLayout.setVisibility(View.GONE);
            toolbar.setSubtitle("");
        } else {
            tabLayout.setVisibility(View.VISIBLE);
            toolbar.setSubtitle(getString(subtitleId));
        }
    }
}
