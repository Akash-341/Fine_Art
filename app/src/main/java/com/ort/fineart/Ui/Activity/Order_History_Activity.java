package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ort.fineart.R;
import com.ort.fineart.databinding.ActivityOrderHistoryBinding;

public class Order_History_Activity extends AppCompatActivity {
    ActivityOrderHistoryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOrderHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}