package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.ort.fineart.R;
import com.ort.fineart.Recycler_Adapter.OrderHistoryAdapter;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.ViewModel.OrderHistoryViewModel;
import com.ort.fineart.databinding.ActivityOrderHistoryBinding;

import java.util.ArrayList;

public class Order_History_Activity extends AppCompatActivity {
    ActivityOrderHistoryBinding binding;
    OrderHistoryAdapter adapter;
    OrderHistoryViewModel viewModel;
    SharedPreferenceManager sharedPreferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOrderHistoryBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbar);
        Drawable backArrow = getResources().getDrawable(R.drawable.back_arrow);
        getSupportActionBar().setHomeAsUpIndicator(backArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(OrderHistoryViewModel.class);
        sharedPreferenceManager=new SharedPreferenceManager(this);
        String token=sharedPreferenceManager.getUserToken();

        adapter = new OrderHistoryAdapter(this, new ArrayList<>());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(adapter);

        viewModel.getOrderHistory("Token "+token).observe(this, payloadList -> {
            // Update the adapter with new data
            adapter.setPayloadList(payloadList);
        });
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