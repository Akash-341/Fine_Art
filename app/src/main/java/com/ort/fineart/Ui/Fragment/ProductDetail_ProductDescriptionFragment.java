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
import com.ort.fineart.databinding.FragmentHubBinding;
import com.ort.fineart.databinding.FragmentProductDetailProductDescriptionBinding;


public class ProductDetail_ProductDescriptionFragment extends Fragment {


    FragmentProductDetailProductDescriptionBinding binding;

    String htmlText;

    public ProductDetail_ProductDescriptionFragment() {
        // Required empty public constructor
    }

    public ProductDetail_ProductDescriptionFragment(String html) {
        // Required empty public constructor
        htmlText = html;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductDetailProductDescriptionBinding.inflate(inflater, container, false);


        return binding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.descriptionTxt.setText(Html.fromHtml(htmlText));
        binding.descriptionTxt.setPadding(0, 0, 0, 0); // Set padding to zero
        binding.descriptionTxt.setLineSpacing(0, 1); // Set line spacing to zero

    }
}