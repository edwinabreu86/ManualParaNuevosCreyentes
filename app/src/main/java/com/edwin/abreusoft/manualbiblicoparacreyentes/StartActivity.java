package com.edwin.abreusoft.manualbiblicoparacreyentes;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);

        Handler h = new Handler();
        h.postDelayed(new Start(), 1500);
    }

    private class Start implements Runnable {
        @Override
        public void run() {
            Intent i = new Intent(getApplication(), MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}
