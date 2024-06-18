package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.ort.fineart.databinding.ActivitySplshScreenBinding;

public class SplashScreen_Activity extends AppCompatActivity {
    ActivitySplshScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        binding=ActivitySplshScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int splashScreenDuration = 2000; // 2 seconds
        new Handler().postDelayed(() -> {
            // Start your main activity after the splash screen duration
            Intent intent = new Intent(SplashScreen_Activity.this,Home_Activity.class);
            startActivity(intent);
            //overridePendingTransition(R.anim.scale_up, 0);

            finish();
        }, splashScreenDuration);
    }
}