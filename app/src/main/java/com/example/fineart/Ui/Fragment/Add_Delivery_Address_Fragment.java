package com.example.fineart.Ui.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fineart.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class Add_Delivery_Address_Fragment extends BottomSheetDialogFragment {


    public Add_Delivery_Address_Fragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add__delivery__address_, container, false);
    }
}