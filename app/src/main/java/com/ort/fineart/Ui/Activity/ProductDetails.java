package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.ort.fineart.R;
import com.ort.fineart.databinding.ActivityProductDetailsBinding;

public class ProductDetails extends AppCompatActivity {
    ActivityProductDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbar);
        Drawable backArrow = getResources().getDrawable(R.drawable.back_arrow);
        getSupportActionBar().setHomeAsUpIndicator(backArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        binding.layoutAction1.setOnClickListener(view -> {
            binding.descriptionLayout.setVisibility(View.VISIBLE);
            binding.usageLayout.setVisibility(View.GONE);
            binding.moredetailsLayout.setVisibility(View.GONE);
        });
        binding.layoutAction2.setOnClickListener(view -> {
            binding.usageLayout.setVisibility(View.VISIBLE);
            binding.descriptionLayout.setVisibility(View.GONE);
            binding.moredetailsLayout.setVisibility(View.GONE);
        });
        binding.layoutAction3.setOnClickListener(view -> {
            binding.moredetailsLayout.setVisibility(View.VISIBLE);
            binding.usageLayout.setVisibility(View.GONE);
            binding.descriptionLayout.setVisibility(View.GONE);
        });
        binding.increment.setOnClickListener(view -> {
            int quantity = Integer.parseInt(binding.qty.getText().toString());
            quantity++;
            binding.qty.setText(String.valueOf(quantity));
        });
        binding.decrement.setOnClickListener(view -> {
            int quantity = Integer.parseInt(binding.qty.getText().toString());
            if (quantity > 1) {
                quantity--;
                binding.qty.setText(String.valueOf(quantity));
            }
        });

        setContentView(binding.getRoot());

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}