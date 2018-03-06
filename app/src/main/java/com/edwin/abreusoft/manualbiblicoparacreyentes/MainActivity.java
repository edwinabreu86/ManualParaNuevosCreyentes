package com.edwin.abreusoft.manualbiblicoparacreyentes;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.Fragment;
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
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
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

import com.edwin.abreusoft.manualbiblicoparacreyentes.Texts.BooksText;
import com.edwin.abreusoft.manualbiblicoparacreyentes.Texts.ItemsText;
import com.edwin.abreusoft.manualbiblicoparacreyentes.Texts.VersesText;

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

        showList(R.string.general_questions, 0);
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

    protected Dialog showCustomDialog(int layoutId) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(LayoutInflater.from(this).inflate(layoutId, null),
                new ViewGroup.LayoutParams(480, ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.show();
        return dialog;
    }

    private void showIntro() {
        final Dialog dialog = showCustomDialog(R.layout.intro_dialog);
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
        final Dialog dialog = showCustomDialog(R.layout.credits_dialog);

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

        if(id == R.id.nav_general_questions) {
            showList(R.string.general_questions, 0);
        } else if (id == R.id.nav_ot_books) {
            showList(R.string.bible_books, R.string.old_testament);
        } else if (id == R.id.nav_nt_books) {
            showList(R.string.bible_books, R.string.new_testament);
        } else if (id == R.id.nav_ot_verses) {
            showList(R.string.verses_for_you, R.string.old_testament);
        } else if (id == R.id.nav_nt_verses) {
            showList(R.string.verses_for_you, R.string.new_testament);
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

    private void showList(int titleId, int subtitleId) {
        toolbar.setTitle(getString(titleId));

        if(titleId == R.string.general_questions) {
            tabLayout.setVisibility(View.GONE);
            toolbar.setSubtitle("");
        } else {
            tabLayout.setVisibility(View.VISIBLE);
            toolbar.setSubtitle(getString(subtitleId));
        }

        viewPager.setAdapter(new SectionsAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    private class SectionsAdapter extends FragmentStatePagerAdapter {

        private final String[] otLabels = {"Pentateuco", "Históricos", "Poéticos", "Proféticos mayores", "Proféticos menores"};
        private final String[] ntLabels = {"Evangelios", "Históricos", "Cartas paulinas", "Cartas generales", "Proféticos"};

        private String title = toolbar.getTitle().toString();
        private String subtitle = toolbar.getSubtitle().toString();
        private String[] text1;
        private String[] text2;
        private String[] text3;
        private String[] text4;

        SectionsAdapter(FragmentManager fm) {
            super(fm);
        }

        private String[] getColumn(String[][] items, int index) {
            String[] column = new String[items.length];

            for (int i = 0; i < column.length; i++) {
                column[i] = items[i][index];
            }
            return column;
        }
        
        @Override
        public Fragment getItem(int position) {

            if (title.equals(getString(R.string.general_questions))) {
                text1 = getColumn(ItemsText.ITEMS, 0);
                text2 = getColumn(ItemsText.ITEMS, 1);

                return MainFragment.newInstance(text1, text2);

            } else if (title.equals(getString(R.string.bible_books))
                    && subtitle.equals(getString(R.string.old_testament))) {

                switch (position) {
                    case 0:
                        text1 = getColumn(BooksText.OT_MOSES, 0);
                        text2 = getColumn(BooksText.OT_MOSES, 1);
                        text3 = getColumn(BooksText.OT_MOSES, 2);
                        text4 = getColumn(BooksText.OT_MOSES, 3);
                        break;
                    case 1:
                        text1 = getColumn(BooksText.OT_HISTORICAL, 0);
                        text2 = getColumn(BooksText.OT_HISTORICAL, 1);
                        text3 = getColumn(BooksText.OT_HISTORICAL, 2);
                        text4 = getColumn(BooksText.OT_HISTORICAL, 3);
                        break;
                    case 2:
                        text1 = getColumn(BooksText.OT_POETRY, 0);
                        text2 = getColumn(BooksText.OT_POETRY, 1);
                        text3 = getColumn(BooksText.OT_POETRY, 2);
                        text4 = getColumn(BooksText.OT_POETRY, 3);
                        break;
                    case 3:
                        text1 = getColumn(BooksText.OT_MAJ_PROPHETS, 0);
                        text2 = getColumn(BooksText.OT_MAJ_PROPHETS, 1);
                        text3 = getColumn(BooksText.OT_MAJ_PROPHETS, 2);
                        text4 = getColumn(BooksText.OT_MAJ_PROPHETS, 3);
                        break;
                    case 4:
                        text1 = getColumn(BooksText.OT_MIN_PROPHETS, 0);
                        text2 = getColumn(BooksText.OT_MIN_PROPHETS, 1);
                        text3 = getColumn(BooksText.OT_MIN_PROPHETS, 2);
                        text4 = getColumn(BooksText.OT_MIN_PROPHETS, 3);
                        break;
                }
                return MainFragment.newInstance(text1, text2, text3, text4);

            } else if (title.equals(getString(R.string.bible_books))
                    && subtitle.equals(getString(R.string.new_testament))) {

                switch (position) {
                    case 0:
                        text1 = getColumn(BooksText.NT_GOSPELS, 0);
                        text2 = getColumn(BooksText.NT_GOSPELS, 1);
                        text3 = getColumn(BooksText.NT_GOSPELS, 2);
                        text4 = getColumn(BooksText.NT_GOSPELS, 3);
                        break;
                    case 1:
                        text1 = getColumn(BooksText.NT_HISTORICAL, 0);
                        text2 = getColumn(BooksText.NT_HISTORICAL, 1);
                        text3 = getColumn(BooksText.NT_HISTORICAL, 2);
                        text4 = getColumn(BooksText.NT_HISTORICAL, 3);
                        break;
                    case 2:
                        text1 = getColumn(BooksText.NT_PAULINE, 0);
                        text2 = getColumn(BooksText.NT_PAULINE, 1);
                        text3 = getColumn(BooksText.NT_PAULINE, 2);
                        text4 = getColumn(BooksText.NT_PAULINE, 3);
                        break;
                    case 3:
                        text1 = getColumn(BooksText.NT_GENERAL, 0);
                        text2 = getColumn(BooksText.NT_GENERAL, 1);
                        text3 = getColumn(BooksText.NT_GENERAL, 2);
                        text4 = getColumn(BooksText.NT_GENERAL, 3);
                        break;
                    case 4:
                        text1 = getColumn(BooksText.NT_PROPHECY, 0);
                        text2 = getColumn(BooksText.NT_PROPHECY, 1);
                        text3 = getColumn(BooksText.NT_PROPHECY, 2);
                        text4 = getColumn(BooksText.NT_PROPHECY, 3);
                        break;
                }
                return MainFragment.newInstance(text1, text2, text3, text4);

            } else if (title.equals(getString(R.string.verses_for_you))
                    && subtitle.equals(getString(R.string.old_testament))) {

                switch (position) {
                    case 0:
                        text1 = getColumn(VersesText.OT_MOSES, 0);
                        text2 = getColumn(VersesText.OT_MOSES, 1);
                        text3 = getColumn(VersesText.OT_MOSES, 2);
                        break;
                    case 1:
                        text1 = getColumn(VersesText.OT_HISTORICAL, 0);
                        text2 = getColumn(VersesText.OT_HISTORICAL, 1);
                        text3 = getColumn(VersesText.OT_HISTORICAL, 2);
                        break;
                    case 2:
                        text1 = getColumn(VersesText.OT_POETRY, 0);
                        text2 = getColumn(VersesText.OT_POETRY, 1);
                        text3 = getColumn(VersesText.OT_POETRY, 2);
                        break;
                    case 3:
                        text1 = getColumn(VersesText.OT_MAJ_PROPHETS, 0);
                        text2 = getColumn(VersesText.OT_MAJ_PROPHETS, 1);
                        text3 = getColumn(VersesText.OT_MAJ_PROPHETS, 2);
                        break;
                    case 4:
                        text1 = getColumn(VersesText.OT_MIN_PROPHETS, 0);
                        text2 = getColumn(VersesText.OT_MIN_PROPHETS, 1);
                        text3 = getColumn(VersesText.OT_MIN_PROPHETS, 2);
                        break;
                }
                return MainFragment.newInstance(text1, text2, text3);

            } else if (title.equals(getString(R.string.verses_for_you))
                    && subtitle.equals(getString(R.string.new_testament))) {

                switch (position) {
                    case 0:
                        text1 = getColumn(VersesText.NT_GOSPELS, 0);
                        text2 = getColumn(VersesText.NT_GOSPELS, 1);
                        text3 = getColumn(VersesText.NT_GOSPELS, 2);
                        break;
                    case 1:
                        text1 = getColumn(VersesText.NT_HISTORICAL, 0);
                        text2 = getColumn(VersesText.NT_HISTORICAL, 1);
                        text3 = getColumn(VersesText.NT_HISTORICAL, 2);
                        break;
                    case 2:
                        text1 = getColumn(VersesText.NT_PAULINE, 0);
                        text2 = getColumn(VersesText.NT_PAULINE, 1);
                        text3 = getColumn(VersesText.NT_PAULINE, 2);
                        break;
                    case 3:
                        text1 = getColumn(VersesText.NT_GENERAL, 0);
                        text2 = getColumn(VersesText.NT_GENERAL, 1);
                        text3 = getColumn(VersesText.NT_GENERAL, 2);
                        break;
                    case 4:
                        text1 = getColumn(VersesText.NT_PROPHECY, 0);
                        text2 = getColumn(VersesText.NT_PROPHECY, 1);
                        text3 = getColumn(VersesText.NT_PROPHECY, 2);
                        break;
                }
                return MainFragment.newInstance(text1, text2, text3);
            }
            return null;
        }

        @Override
        public int getCount() {
            if(title.equals(getString(R.string.general_questions))) {
                return 1;
            } else {
                return 5;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(subtitle.equals(getString(R.string.old_testament))) {
                return otLabels[position];
            } else if(title.equals(getString(R.string.new_testament))) {
                return ntLabels[position];
            } else {
                return null;
            }
        }
    }
}
