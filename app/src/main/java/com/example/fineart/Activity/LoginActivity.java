package com.example.fineart.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fineart.Fragment.Login;
import com.example.fineart.R;
import com.example.fineart.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            // Add the fragment to the container
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.conatainer, new Login())
                    .commit();
        }
    }
}