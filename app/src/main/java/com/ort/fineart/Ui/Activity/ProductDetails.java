package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
import com.ort.fineart.Model.Response_Model.AddRemoveWishlist.Add_wishlist;
import com.ort.fineart.Model.Response_Model.Cart.Add_Cart;
import com.ort.fineart.Model.Response_Model.PersonalDetailsData;
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
    private String selectedColorName;
    private boolean isUpdateUIPerformed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
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
        binding.quickBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetails.this, Order_CheckOut_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right_fade, R.anim.slide_out_left_fade);
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
        binding.itemDetailPrice.setText("₹" + productDetailsModel.getPayload().getProductDiscountPrice());
        binding.itemDetailOgPrice.setText("₹" + productDetailsModel.getPayload().getProductPrice());
        binding.discount.setText(productDetailsModel.getPayload().getProductDiscount() + productDetailsModel.getPayload().getProductDiscountMode() + "Off");
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
        binding.wishlistLogo.setOnClickListener(view -> {
            AddtoWishlist(productDetailsModel.getPayload().getProductVarientId());
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

            radioButton.setButtonTintList(ColorStateList.valueOf(Color.parseColor(color)));
            radioButton.setBackgroundTintMode(PorterDuff.Mode.DARKEN);
            radioButton.getSolidColor();

            binding.colorRadioGroup.addView(radioButton);
        }
        binding.colorRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            RadioButton selectedRadioButton = findViewById(checkedId);

            int selectedPosition = binding.colorRadioGroup.indexOfChild(selectedRadioButton);
            if (selectedPosition >= 0 && selectedPosition < colorsNameList.size()) {
                int color = Color.parseColor(colorsList.get(selectedPosition));
                selectedRadioButton.setButtonTintList(ColorStateList.valueOf(color));
                selectedColorName = colorsNameList.get(selectedPosition);

                Log.e("radiobuttoncolor", "Selected Color Name: " + selectedColorName);
                if (productDetailsModel.getPayload().getProductId().equals("63") && selectedColorName.equals("Red")) {
                    fetchVarientData(selectedColorName, "4 MM", productDetailsModel.getPayload().getProductId());
                }
                if (productDetailsModel.getPayload().getProductId().equals("63") && selectedColorName.equals("Golden")) {
                    fetchVarientData(selectedColorName, "Regular Free", productDetailsModel.getPayload().getProductId());
                } else {
                    fetchVarientData(selectedColorName, productDetailsModel.getPayload().getProductSize(), productDetailsModel.getPayload().getProductId());
                }
                selectedRadioButton.setChecked(true);
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

        binding.addToCart.setOnClickListener(view -> {
            AddtoCart(productDetailsModel.getPayload().getProductVarientId());
        });


    }

    private void fetchVarientData(String color, String size, String id) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<Product_Details_Model> call = apiService.getVarientData(color, size, id);

        call.enqueue(new Callback<Product_Details_Model>() {
            @Override
            public void onResponse(Call<Product_Details_Model> call, Response<Product_Details_Model> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Product_Details_Model productDetailsModel = response.body();
                    updateUI(productDetailsModel);
                }
                applySelectedColor();
            }

            @Override
            public void onFailure(Call<Product_Details_Model> call, Throwable t) {
                // Handle API call failure
            }
        });
    }

    private void applySelectedColor() {
        if (selectedColorName != null) {
            RadioButton selectedRadioButton = findRadioButtonByColor(selectedColorName);
            if (selectedRadioButton != null) {
                selectedRadioButton.setChecked(true);
            }
        }
    }

    private RadioButton findRadioButtonByColor(String colorName) {
        for (int i = 0; i < binding.colorRadioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) binding.colorRadioGroup.getChildAt(i);
            if (radioButton != null && radioButton.getText().toString().equals(colorName)) {
                return radioButton;
            }
        }
        return null;
    }

    private void AddtoWishlist(String varientid) {
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(this);
        String token = "Token " + sharedPreferenceManager.getUserToken();
        Log.e("AddtoWishlist: ", token);
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<Add_wishlist> call = apiService.addtoWishlist(token, varientid);
        call.enqueue(new Callback<Add_wishlist>() {
            @Override
            public void onResponse(Call<Add_wishlist> call, Response<Add_wishlist> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Add_wishlist add_wishlist = response.body();
                    Toast.makeText(ProductDetails.this, add_wishlist.getMessge(), Toast.LENGTH_SHORT).show();
                    if (add_wishlist.getPayload().getIsactive()) {
                        binding.wishlistLogo.setImageResource(R.drawable.ic_baseline_favorite_24);
                    } else {
                        binding.wishlistLogo.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    }

                }
            }

            @Override
            public void onFailure(Call<Add_wishlist> call, Throwable t) {

            }
        });
    }

    private void AddtoCart(String varientid) {
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(this);
        String token = "Token " + sharedPreferenceManager.getUserToken();
        String qty = binding.qty.getText().toString().trim();
        Log.e("AddtoCart: ", qty);
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<Add_Cart> call = apiService.addtocartList(token, varientid, qty);
        call.enqueue(new Callback<Add_Cart>() {
            @Override
            public void onResponse(Call<Add_Cart> call, Response<Add_Cart> response) {
                if (response.isSuccessful() && response.body() != null ) {
                    Add_Cart add_cart = response.body();
                    Log.e("AddtoCart: ", "Response status: " + response.code());
                    if (add_cart.getStatus().equals("200")) {
                        Toast.makeText(ProductDetails.this, add_cart.getMessge(), Toast.LENGTH_SHORT).show();
                    } else {
                        // Handle other status codes if needed
                        Log.e("AddtoCart: ", "Failed to add to cart. Status code: " + add_cart.getStatus());
                    }
                }

            }

            @Override
            public void onFailure(Call<Add_Cart> call, Throwable t) {
                Toast.makeText(ProductDetails.this, "Product Already Exist In Cart", Toast.LENGTH_SHORT).show();

            }
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