package com.edwin.abreusoft.manualparanuevoscreyentes;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CreditsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView iconUrl1;
    private TextView iconUrl2;

    String url1;
    String url2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        iconUrl1 = (TextView) findViewById(R.id.icon_url1);
        iconUrl2 = (TextView) findViewById(R.id.icon_url2);
        iconUrl1.setOnClickListener(this);
        iconUrl2.setOnClickListener(this);

        url1 = getResources().getString(R.string.url1);
        url2 = getResources().getString(R.string.url2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_url1:
                openUrlInBrowser(url1);
                break;
            case R.id.icon_url2:
                openUrlInBrowser(url2);
                break;
        }
    }

    private void openUrlInBrowser(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
        browserIntent.setData(Uri.parse(url));
        startActivity(browserIntent);
    }
}
