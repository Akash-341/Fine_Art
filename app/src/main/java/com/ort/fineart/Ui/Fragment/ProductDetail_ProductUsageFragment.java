package com.ort.fineart.Ui.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ort.fineart.R;
import com.ort.fineart.databinding.FragmentProductDetailProductMoreDetailsBinding;
import com.ort.fineart.databinding.FragmentProductDetailProductUsageBinding;


public class ProductDetail_ProductUsageFragment extends Fragment {
    FragmentProductDetailProductUsageBinding binding;

    String htmlText;



    public ProductDetail_ProductUsageFragment() {
        // Required empty public constructor
    }
    public ProductDetail_ProductUsageFragment(String html) {
        // Required empty public constructor
        htmlText=html;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductDetailProductUsageBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.productUsageTxt.setText(Html.fromHtml(htmlText));
        binding.productUsageTxt.setPadding(0, 0, 0, 0); // Set padding to zero
        binding.productUsageTxt.setLineSpacing(0, 1); // Set line spacing to zero

    }
}