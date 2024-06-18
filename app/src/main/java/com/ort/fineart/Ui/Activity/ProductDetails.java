package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.ort.fineart.Api_Handel.All_URL;
import com.ort.fineart.Api_Handel.ApiService;
import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.Model.Response_Model.ProductDetailsData.Product_Details_Model;
import com.ort.fineart.R;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.databinding.ActivityProductDetailsBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetails extends AppCompatActivity {
    ActivityProductDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbar);
        setContentView(binding.getRoot());
        Drawable backArrow = getResources().getDrawable(R.drawable.back_arrow);
        getSupportActionBar().setHomeAsUpIndicator(backArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("productId")) {
            String productId = intent.getStringExtra("productId");
            fetchPeofileData(productId);

        } else {

        }
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



    }
    private void fetchPeofileData(String varientID) {
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(this);
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<Product_Details_Model> call = apiService.getProductData(varientID);

        call.enqueue(new Callback<Product_Details_Model>() {
            @Override
            public void onResponse(Call<Product_Details_Model> call, Response<Product_Details_Model> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Product_Details_Model productDetailsModel = response.body();
                    updateUI(productDetailsModel);
                }
            }

            @Override
            public void onFailure(Call<Product_Details_Model> call, Throwable t) {
                // Handle API call failure
            }
        });
    }
    private void updateUI(Product_Details_Model productDetailsModel) {
        binding.itemDetailName.setText(productDetailsModel.getPayload().getProductName());
        binding.itemDetailPrice.setText("₹"+productDetailsModel.getPayload().getProductDiscountPrice());
        binding.itemDetailOgPrice.setText("₹"+productDetailsModel.getPayload().getProductPrice());
        binding.discount.setText(productDetailsModel.getPayload().getProductDiscount()+productDetailsModel.getPayload().getProductDiscountMode()+"Off");
        int maxQuantity = productDetailsModel.getPayload().getProductQuantity();

        binding.increment.setOnClickListener(view -> {
            int quantity = Integer.parseInt(binding.qty.getText().toString());
            if (quantity < maxQuantity) {
                quantity++;
                binding.qty.setText(String.valueOf(quantity));
            } else {
                // Handle reaching the maximum quantity (e.g., show a message)
                Toast.makeText(this, "Maximum quantity reached", Toast.LENGTH_SHORT).show();
            }
        });

        binding.decrement.setOnClickListener(view -> {
            int quantity = Integer.parseInt(binding.qty.getText().toString());
            if (quantity > 1) {
                quantity--;
                binding.qty.setText(String.valueOf(quantity));
            } else {
                // Handle reaching the minimum quantity (e.g., show a message)
                Toast.makeText(this, "Minimum quantity reached", Toast.LENGTH_SHORT).show();
            }
        });

        List<String> colorsList = productDetailsModel.getPayload().getColorslist();
        List<String> colorsNameList = productDetailsModel.getPayload().getColorsNamelist();

        binding.colorRadioGroup.removeAllViews();
        for (String color : colorsList) {
            RadioButton radioButton = new RadioButton(this);

            // Set the background color of the RadioButton
            radioButton.setButtonTintList(ColorStateList.valueOf(Color.parseColor(color)));

            // You can set other properties of the RadioButton as needed
            // radioButton.setText("Color"); // Set text if needed
            // radioButton.setTextColor(Color.WHITE); // Set text color if needed

            // Add the RadioButton to the RadioGroup
            binding.colorRadioGroup.addView(radioButton);
        }
        binding.colorRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            RadioButton selectedRadioButton = findViewById(checkedId);

            int selectedPosition = binding.colorRadioGroup.indexOfChild(selectedRadioButton);
            if (selectedPosition >= 0 && selectedPosition < colorsNameList.size()) {
                String selectedColorName = colorsNameList.get(selectedPosition);

                // Update the TextView with the selected color name
                Log.e("radiobuttoncolor", "Selected Color Name: " + selectedColorName);
            } else {
                Log.e("radiobuttoncolor", "Invalid position or ColorsNamelist");
            }

        });
        String imageUrl = All_URL.imgURL + productDetailsModel.getPayload().getProductImage();

        Glide.with(getApplicationContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .transform(new CenterCrop())
                .into(binding.image1);
        CharSequence desc = Html.fromHtml(productDetailsModel.getPayload().getProductDescription());
        binding.tvdesc.setText(desc);
        CharSequence usage = Html.fromHtml(productDetailsModel.getPayload().getProductUsage());
        binding.tvusage.setText(usage);
        CharSequence moredetails = Html.fromHtml(productDetailsModel.getPayload().getProductMoreDetails());
        binding.tvmoredetails.setText(moredetails);
    }
    private String getColorFromRadioButton(RadioButton radioButton) {
        Drawable background = radioButton.getBackground();

        if (background instanceof RippleDrawable) {
            // If it's a RippleDrawable, try to get the color from the mask
            RippleDrawable rippleDrawable = (RippleDrawable) background;
            Drawable maskDrawable = rippleDrawable.findDrawableByLayerId(android.R.id.mask);

            if (maskDrawable instanceof ColorDrawable) {
                int color = ((ColorDrawable) maskDrawable).getColor();
                return String.format("#%06X", (0xFFFFFF & color));
            }
        } else if (background instanceof ColorDrawable) {
            // If it's a ColorDrawable, directly get the color
            int color = ((ColorDrawable) background).getColor();
            return String.format("#%06X", (0xFFFFFF & color));
        }

        // Default to an empty string if the color extraction fails
        return "";
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