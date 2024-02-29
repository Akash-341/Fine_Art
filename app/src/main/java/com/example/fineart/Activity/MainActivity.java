package com.example.fineart.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fineart.Fragment.Home_page;
import com.example.fineart.Fragment.Login;
import com.example.fineart.R;
import com.example.fineart.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (savedInstanceState == null) {
            // Add the fragment to the container
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, new Home_page())
                    .commit();
        }
    }
}