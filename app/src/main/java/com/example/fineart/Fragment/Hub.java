package com.example.fineart.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.example.fineart.Adapter.BottomSheetAdapter;
import com.example.fineart.Adapter.DotdAdapter;
import com.example.fineart.Model.Product;
import com.example.fineart.R;
import com.example.fineart.databinding.FragmentHubBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Hub extends Fragment {


    FragmentHubBinding binding;
    private List<Product> productList;
    private DotdAdapter productAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding=FragmentHubBinding.inflate(inflater, container, false);
        productList = initializeProductList();
        productAdapter = new DotdAdapter(productList,getActivity().getApplicationContext(),requireActivity());
        productAdapter.setOnWishlistClickListener(new DotdAdapter.OnWishlistClickListener() {
            @Override
            public void onWishlistClick(int position) {
                toggleWishlist(position);
            }
        });

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(productAdapter);
        return binding.getRoot();

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Show Bottom Sheet Dialog when the Fragment is created
        showBottomSheetDialog();
    }
    private void showBottomSheetDialog() {
        if (getActivity() != null) {
            final Dialog bottomSheetDialog = new Dialog(getActivity());
            bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            bottomSheetDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            bottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
            View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_layout, null);

            RecyclerView recyclerView = bottomSheetView.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2)); // 2 columns, you can change it
            List<String> namesList = Arrays.asList("Handmade Jewellery", "Traditional Jewellery" , "Raw Material"); // Replace with your data
            BottomSheetAdapter namesAdapter = new BottomSheetAdapter(namesList);
            namesAdapter.setOnItemClickListener((v, selectedName) -> {
                // Update your TextView with the selected name
                bottomSheetDialog.dismiss();  // Close the Bottom Sheet Dialog
            });

            recyclerView.setAdapter(namesAdapter);

            bottomSheetDialog.setContentView(bottomSheetView);

            bottomSheetDialog.show();
        }
    }
    private List<Product> initializeProductList() {
        // Populate the list with your product data
        // For simplicity, using placeholder data here
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Product 1", false));
        productList.add(new Product("Product 2", true));
        productList.add(new Product("Product 3", false));
        productList.add(new Product("Product 4", false));
        productList.add(new Product("Product 5", false));
        productList.add(new Product("Product 6", true));
        productList.add(new Product("Product 7", false));
        productList.add(new Product("Product 8", true));
        // ... add more products

        return productList;
    }
    private void toggleWishlist(int position) {
        Product product = productList.get(position);
        product.setWishlist(!product.isWishlist());
        productAdapter.notifyItemChanged(position);
    }
}