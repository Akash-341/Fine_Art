package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ort.fineart.R;
import com.ort.fineart.Ui.Activity.Authentication.Authentication_Activity;
import com.ort.fineart.Ui.Fragment.Home_Fragment;
import com.ort.fineart.Ui.Fragment.Hub_Fragment;
import com.ort.fineart.Ui.Fragment.Profile_Fragment;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.databinding.ActivityHomeBinding;

public class Home_Activity extends AppCompatActivity {

    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        OpenFragment(new Home_Fragment());


        binding.homeFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenFragment(new Home_Fragment());
                int tintColor_Red = ContextCompat.getColor(Home_Activity.this, R.color.app_color);
                int tintColor_White = ContextCompat.getColor(Home_Activity.this, R.color.white);

                // Apply tint color to the FloatingActionButton
                binding.homeFab.getDrawable().setColorFilter(tintColor_Red, PorterDuff.Mode.SRC_IN);
                binding.hubIcon.getDrawable().setColorFilter(tintColor_White, PorterDuff.Mode.SRC_IN);
                binding.profileIcon.getDrawable().setColorFilter(tintColor_White, PorterDuff.Mode.SRC_IN);



            }
        });

        binding.hubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenFragment(new Hub_Fragment());
                int tintColor_Red = ContextCompat.getColor(Home_Activity.this, R.color.app_color);
                int tintColor_White = ContextCompat.getColor(Home_Activity.this, R.color.white);

                // Apply tint color to the FloatingActionButton
                binding.homeFab.getDrawable().setColorFilter(tintColor_White, PorterDuff.Mode.SRC_IN);
                binding.hubIcon.getDrawable().setColorFilter(tintColor_Red, PorterDuff.Mode.SRC_IN);
                binding.profileIcon.getDrawable().setColorFilter(tintColor_White, PorterDuff.Mode.SRC_IN);

            }
        });








        binding.profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tintColor_Red = ContextCompat.getColor(Home_Activity.this, R.color.app_color);
                int tintColor_White = ContextCompat.getColor(Home_Activity.this, R.color.white);


                SharedPreferenceManager sharedPreferenceManager=new SharedPreferenceManager(getApplicationContext());

                String userToken = sharedPreferenceManager.getUserToken();

                if (userToken != null && !userToken.isEmpty()) {
                    // The user token is neither null nor empty
                    OpenFragment(new Profile_Fragment());
                    binding.homeFab.getDrawable().setColorFilter(tintColor_White, PorterDuff.Mode.SRC_IN);
                    binding.hubIcon.getDrawable().setColorFilter(tintColor_White, PorterDuff.Mode.SRC_IN);
                    binding.profileIcon.getDrawable().setColorFilter(tintColor_Red, PorterDuff.Mode.SRC_IN);

                } else {
                    Intent intent=new Intent(getApplicationContext(),Authentication_Activity.class);
                    startActivity(intent);

                }

            }
        });






    }


    public void OpenFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment);
       // transaction.addToBackStack(fragment.getTag()); // Optional: Adds the transaction to the back stack
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            Log.e("HomeFragmentBack",getSupportFragmentManager().getBackStackEntryCount()+":Replace");
            // If there's only one fragment in the back stack, close the activity
            finish();
        } else {
            Log.e("HomeFragmentBack",":One Replace");

            // If there are multiple fragments in the back stack, proceed with the default back behavior
            super.onBackPressed();
        }
    }
}