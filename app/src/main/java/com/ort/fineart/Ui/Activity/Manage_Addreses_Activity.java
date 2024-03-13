package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ort.fineart.R;
import com.ort.fineart.databinding.ActivityManageAddresesBinding;

public class Manage_Addreses_Activity extends AppCompatActivity {
    ActivityManageAddresesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityManageAddresesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}