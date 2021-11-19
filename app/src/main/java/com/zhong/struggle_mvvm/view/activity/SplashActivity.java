package com.zhong.struggle_mvvm.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, FrameworkActivity1.class));
            finish();
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}