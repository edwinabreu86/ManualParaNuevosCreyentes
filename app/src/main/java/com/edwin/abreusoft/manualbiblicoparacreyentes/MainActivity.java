package com.edwin.abreusoft.manualbiblicoparacreyentes;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.edwin.abreusoft.manualbiblicoparacreyentes.Books.SectionsNTBooksAdapter;
import com.edwin.abreusoft.manualbiblicoparacreyentes.Books.SectionsOTBooksAdapter;
import com.edwin.abreusoft.manualbiblicoparacreyentes.Items.SectionsItemsAdapter;
import com.edwin.abreusoft.manualbiblicoparacreyentes.Verses.SectionsNTVersesAdapter;
import com.edwin.abreusoft.manualbiblicoparacreyentes.Verses.SectionsOTVersesAdapter;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String CHECKED = "checked";
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private DrawerLayout drawer;
    private SharedPreferences prefs;

    private Dialog dialog;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // getWindow().setStatusBarColor(Color.TRANSPARENT);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawen_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        prefs = getPreferences(Context.MODE_PRIVATE);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        viewPager = findViewById(R.id.view_pager);

        tabLayout = findViewById(R.id.tab_layout);

        showQuestions();
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (!prefs.contains(CHECKED)) {
            showIntroDialog();
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

    @SuppressLint("InflateParams")
    private void showIntroDialog() {
        dialog.setContentView(getLayoutInflater().inflate(R.layout.intro_dialog, null),
                new ViewGroup.LayoutParams(540, ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.show();

        TextView introText = dialog.findViewById(R.id.intro_text);

        String INTR_CONTENT = "Aquí encontrarás datos esenciales de la Biblia " +
                "antes conocerla por dentro, tales como: \n * los nombres de sus 66 libros, \n " +
                "* cuántos capítulos tienen, \n * el contenido general de ellos, \n " +
                "* las sub-divisiones de la biblia \n * versos esenciales para tu edificación.";

        introText.setText(INTR_CONTENT);

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

    @SuppressLint("InflateParams")
    private void showCreditsDialog() {
        dialog.setContentView(getLayoutInflater().inflate(R.layout.credits_dialog, null),
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
        int id = item.getItemId();

        String[] otLabels = getResources().getStringArray(R.array.ot_sections);
        String[] ntLabels = getResources().getStringArray(R.array.nt_sections);

        if(id == R.id.nav_general_questions) {
            showQuestions();
        } else if (id == R.id.nav_ot_books) {
            showList(R.string.bible_books, R.string.old_testament,
                    new SectionsOTBooksAdapter(getSupportFragmentManager()), otLabels);
        } else if (id == R.id.nav_nt_books) {
            showList(R.string.bible_books, R.string.new_testament,
                    new SectionsNTBooksAdapter(getSupportFragmentManager()), ntLabels);
        } else if (id == R.id.nav_ot_verses) {
            showList(R.string.verses_for_you, R.string.old_testament,
                    new SectionsOTVersesAdapter(getSupportFragmentManager()), otLabels);
        } else if (id == R.id.nav_nt_verses) {
            showList(R.string.verses_for_you, R.string.new_testament,
                    new SectionsNTVersesAdapter(getSupportFragmentManager()), ntLabels);
        } else if (id == R.id.menu_app_rating) {
            rateApp();
        } else if(id == R.id.menu_credits) {
            showCreditsDialog();
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
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
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

    private void showQuestions() {
        toolbar.setTitle(getString(R.string.general_questions));
        tabLayout.setVisibility(View.GONE);

        viewPager.setAdapter(new SectionsItemsAdapter(getSupportFragmentManager()));
    }

    private void showList(int titleId, int subtitleId, PagerAdapter adapter, String[] labels) {
        toolbar.setTitle(getString(titleId));
        toolbar.setSubtitle(getString(subtitleId));
        tabLayout.setVisibility(View.VISIBLE);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        for (int n = 0; n < labels.length; n++) {
            tabLayout.getTabAt(n).setText(labels[n]);
        }
    }
}
