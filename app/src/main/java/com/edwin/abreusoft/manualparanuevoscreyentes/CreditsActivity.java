package com.edwin.abreusoft.manualparanuevoscreyentes;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class CreditsActivity extends AppCompatActivity implements View.OnClickListener {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        Toolbar creditsToolbar = findViewById(R.id.credits_toolbar);
        setSupportActionBar(creditsToolbar);

        creditsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView iconUrl1 = findViewById(R.id.icon_url1);
        TextView iconUrl2 = findViewById(R.id.icon_url2);
        iconUrl1.setOnClickListener(this);
        iconUrl2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_url1:
                openUrlInBrowser(getResources().getString(R.string.url1));
                break;
            case R.id.icon_url2:
                openUrlInBrowser(getResources().getString(R.string.url2));
                break;
            case R.id.image_url:
                openUrlInBrowser(getResources().getString(R.string.url3));
        }
    }

    private void openUrlInBrowser(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}
