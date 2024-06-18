package com.ort.fineart.Ui.Activity.Authentication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.ort.fineart.databinding.ActivityAuthenticationBinding;

public class Authentication_Activity extends AppCompatActivity {
    ActivityAuthenticationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        binding = ActivityAuthenticationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Registration_Activity.class);
                startActivity(intent);
            }
        });

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Login_Activity.class);
                startActivity(intent);
            }
        });
    }





}