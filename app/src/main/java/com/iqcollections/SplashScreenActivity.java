package com.iqcollections;
/*
    Code Attribution 1:
    Source: YouTube
    Source URL link: https://www.youtube.com/watch?v=1dnM0-D5CDo&t=1s
    Title Page/Video: How to create Splash Screen in Android Studio | Java
    Author name/tag/channel: Android Mate
    Author channel/profile url link: https://www.youtube.com/channel/UC3w_uEn4IhvT14rfP5nTl0A
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //startActivity(new Intent(this, LoginActivity.class));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                finish();
            }
        }, 2000);
    }

}