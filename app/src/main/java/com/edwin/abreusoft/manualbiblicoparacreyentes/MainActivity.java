package com.edwin.abreusoft.manualbiblicoparacreyentes;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.LayoutParams;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String CHECKED = "checked";
    private Toolbar toolbar;
    private ViewPager viewPager;
    private DrawerLayout drawer;
    private SharedPreferences preferences;
    private LocalBroadcastManager broadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.view_pager);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawen_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        showList(R.string.general_questions, 0);

        preferences = getPreferences(MODE_PRIVATE);
        broadcastManager = LocalBroadcastManager.getInstance(this);
    }

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.filterEquals(new Intent(OptionsDialog.COPY_FILTER))) {
                Snackbar.make(viewPager, "Texto copiado", Snackbar.LENGTH_SHORT).show();
            } else if (intent.filterEquals(new Intent(OptionsDialog.VERSES_FILTER))) {
                Snackbar.make(viewPager, "Versículo " + intent.getStringExtra(OptionsDialog.VERSES_EXTRA) + " agregado a favoritos", Snackbar.LENGTH_SHORT).show();
            }
        }
    };

        @Override
    protected void onStart() {
        super.onStart();
        if (!preferences.contains(CHECKED)) {
            showIntroDialog();
        }
    }

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

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            showExitDialog();
        }
    }

    private void showExitDialog() {
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i == DialogInterface.BUTTON_POSITIVE) {
                    finish();
                } else {
                    dialogInterface.dismiss();
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseas salir de la aplicación?").setCancelable(true)
                .setPositiveButton("Sí", listener)
                .setNegativeButton("No", listener)
                .create().show();
    }


    private void showIntroDialog() {
        final Dialog dialog = OptionsDialog.createDialog(this, R.layout.intro_dialog);
        dialog.show();

        Button closeIntro = dialog.findViewById(R.id.close_intro);
        closeIntro.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ApplySharedPref")
            @Override
            public void onClick(View view) {
                CheckBox noIntro = dialog.findViewById(R.id.no_show_check);
                if (noIntro.isChecked()) {
                    preferences.edit().putBoolean(CHECKED, true).commit();
                }
                dialog.dismiss();
            }
        });
    }

    private void showCreditsDialog() {
        final Dialog dialog = OptionsDialog.createDialog(this, R.layout.credits_dialog);
        dialog.show();

        final TextView creditsText1 = dialog.findViewById(R.id.credits_text1);
        final TextView creditsText2 = dialog.findViewById(R.id.credits_text2);
        Button closeCredits = dialog.findViewById(R.id.close_credits);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.credits_text1) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(creditsText1.getText().toString())));
                } else if(view.getId() == R.id.credits_text2) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(creditsText2.getText().toString())));
                } else {
                    dialog.dismiss();
                }
            }
        };
        creditsText1.setOnClickListener(listener);
        creditsText2.setOnClickListener(listener);
        closeCredits.setOnClickListener(listener);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setCheckable(true);
        item.setChecked(true);
        int id = item.getItemId();

        if(id == R.id.nav_general_questions) {
            showList(R.string.general_questions, 0);
        } else if(id == R.id.nav_recommend) {
            startActivity(new Intent(this, RecommedActivity.class));
            item.setChecked(false);
        } else if (id == R.id.nav_ot_books) {
            showList(R.string.bible_books, R.string.old_testament);
        } else if (id == R.id.nav_nt_books) {
            showList(R.string.bible_books, R.string.new_testament);
        } else if (id == R.id.nav_ot_verses) {
            showList(R.string.verses_for_you, R.string.old_testament);
        } else if (id == R.id.nav_nt_verses) {
            showList(R.string.verses_for_you, R.string.new_testament);
        } else if (id == R.id.nav_fav_verses) {
            startActivity(new Intent(this, FavActivity.class));
            item.setChecked(false);
        } else {
            finish();
        }

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                drawer.closeDrawer(GravityCompat.START);
            }
        });
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.second_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_app_rating) {
            startActivity(rateIntent());
        } else if(id == R.id.menu_sugest) {
            startActivity(Intent.createChooser(createMailIntent(), "Enviar sugerencia"));
        } else if(id == R.id.menu_credits) {
            showCreditsDialog();
        }
        return false;
    }

    private Intent rateIntent() {
        Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getApplicationContext().getPackageName()));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if(Build.VERSION.SDK_INT >= 21) {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        } else {
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        rateIntent.addFlags(flags);
        return rateIntent;
    }

    private Intent createMailIntent() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "core2duo2602@gmail.com", null));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Sugerencias para Manual Bíblico");
        return intent;
    }

    private void showList(final int titleId, final int subtitleId) {
        final String title = getString(titleId);
        final String subtitle = (subtitleId == 0) ? "" : getString(subtitleId);

        int flags = (titleId == R.string.general_questions) ?
                0 : AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS;
        ((LayoutParams) toolbar.getLayoutParams()).setScrollFlags(flags);
        int visibility = (titleId == R.string.general_questions) ?
                View.GONE : View.VISIBLE;

        final TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setVisibility(visibility);

        Handler h = new Handler();
        h.post(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle(title);
                toolbar.setSubtitle(subtitle);
                viewPager.setAdapter(new SectionsAdapter(getSupportFragmentManager(), titleId, subtitleId));
                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }
}