package com.example.fineart.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.fineart.R;
import com.example.fineart.databinding.ActivitySplshScreenBinding;

public class SplshScreen extends AppCompatActivity {
    ActivitySplshScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplshScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int splashScreenDuration = 2000; // 2 seconds
        new Handler().postDelayed(() -> {
            // Start your main activity after the splash screen duration
            Intent intent = new Intent(SplshScreen.this, LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.scale_up, 0);

            finish();
        }, splashScreenDuration);
    }
}