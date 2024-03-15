package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.ort.fineart.R;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.databinding.ActivityProfileBinding;

public class Profile_Activity extends AppCompatActivity {
    ActivityProfileBinding binding;
    SharedPreferenceManager sharedPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        sharedPreferenceManager = new SharedPreferenceManager(this);

        String userId = sharedPreferenceManager.getUserId();
        String userEmail = sharedPreferenceManager.getUserEmail();
        String userFirstName = sharedPreferenceManager.getUserFirstName();
        String userLastName = sharedPreferenceManager.getUserLastName();

        binding.username.setText(userFirstName + " " + userLastName);
        binding.useremail.setText(userEmail);

        Drawable backArrow = getResources().getDrawable(R.drawable.back_arrow);
        getSupportActionBar().setHomeAsUpIndicator(backArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        binding.personaldeatils.setOnClickListener(view -> {
            Intent intent = new Intent(Profile_Activity.this, Personal_Details_Activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.scale_up, 0);
        });
        binding.orderhistory.setOnClickListener(view -> {
            Intent intent = new Intent(Profile_Activity.this, Order_History_Activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.scale_up, 0);
        });
        binding.manageAddress.setOnClickListener(view -> {
            Intent intent = new Intent(Profile_Activity.this, Manage_Addreses_Activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.scale_up, 0);
        });
        binding.changePass.setOnClickListener(view -> {
            Intent intent = new Intent(Profile_Activity.this,Change_Password_Activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.scale_up, 0);
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