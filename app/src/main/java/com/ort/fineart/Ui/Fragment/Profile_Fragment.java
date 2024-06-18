package com.ort.fineart.Ui.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ort.fineart.R;
import com.ort.fineart.Ui.Activity.Change_Password_Activity;
import com.ort.fineart.Ui.Activity.Home_Activity;
import com.ort.fineart.Ui.Activity.Manage_Addreses_Activity;
import com.ort.fineart.Ui.Activity.MyCart_Activity;
import com.ort.fineart.Ui.Activity.My_Wishlist_Activity;
import com.ort.fineart.Ui.Activity.Order_History_Activity;
import com.ort.fineart.Ui.Activity.Personal_Details_Activity;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.databinding.FragmentHubBinding;
import com.ort.fineart.databinding.FragmentProfileBinding;


public class Profile_Fragment extends Fragment {

    FragmentProfileBinding binding;



    public Profile_Fragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentProfileBinding.inflate(inflater, container, false);




        SharedPreferenceManager sharedPreferenceManager=new SharedPreferenceManager(getContext());
        String UserName=sharedPreferenceManager.getUserFirstName()+" "+sharedPreferenceManager.getUserLastName();
        String UserEmail=sharedPreferenceManager.getUserEmail();

        binding.userNameTxt.setText(UserName);
        binding.userEmailTxt.setText(UserEmail);





        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferenceManager.clearSharedPreferences(getContext());
                Intent intent=new Intent(getContext(), Home_Activity.class);
                startActivity(intent);


            }
        });

        binding.personalDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Personal_Details_Activity.class);
                startActivity(intent);
            }
        });

        binding.orderhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Order_History_Activity.class);
                startActivity(intent);
            }
        });


        binding.manageAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Manage_Addreses_Activity.class);
                startActivity(intent);
            }
        });
        binding.changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Change_Password_Activity.class);
                startActivity(intent);
            }
        });



        binding.cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), MyCart_Activity.class);
                startActivity(intent);
            }
        });
        binding.favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), My_Wishlist_Activity.class);
                startActivity(intent);
            }
        });









        return binding.getRoot();
    }
}