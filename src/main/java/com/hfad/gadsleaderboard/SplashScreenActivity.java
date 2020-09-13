package com.hfad.gadsleaderboard;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.hfad.gadsleaderboard.R;

public class SplashScreenActivity extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       handler = new Handler ();
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
               startActivity(i);
               finish();

           }
       }, 5*1000);
    }
}