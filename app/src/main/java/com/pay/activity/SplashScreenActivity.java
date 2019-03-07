package com.pay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pay.R;


public class SplashScreenActivity extends AppCompatActivity {
    private int SPLASH_TIME_OUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        try {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent mainActivity = new Intent(SplashScreenActivity.this, LoginSignUpActivity.class);
                    startActivity(mainActivity);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
