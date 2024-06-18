package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ort.fineart.Model.Response_Model.Checkout.CouponList_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.Recycler_Adapter.CouponList_RecyclerAdapter;
import com.ort.fineart.Recycler_Adapter.MyCart_RecyclerAdapter;
import com.ort.fineart.Utils.DpToPixcel;
import com.ort.fineart.Utils.VerticalSpaceItemDecoration;
import com.ort.fineart.ViewModel.Checkout_ViewModel;
import com.ort.fineart.ViewModel.MyCart_ViewModel;
import com.ort.fineart.databinding.ActivityMyCartBinding;
import com.ort.fineart.databinding.ActivityViewAllCouponsBinding;

import java.util.List;

public class View_All_Coupons_Activity extends AppCompatActivity {
    int RecyclerViewCardMargin=16;
    ActivityViewAllCouponsBinding binding;
    Checkout_ViewModel checkout_viewModel;
    CouponList_RecyclerAdapter couponList_recyclerAdapter;
    CouponList_RecyclerAdapter.ApplyCoupon_Interface applyCoupon_interface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewAllCouponsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int spaceBetweenItemsPx = DpToPixcel.dpToPx(getApplicationContext(), RecyclerViewCardMargin);


        ViewInit();


        binding.notFoundImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CouponList_ResponseModel couponList_responseModel1=new CouponList_ResponseModel(21,"Suraj11", "Testing Discount Coupon", "%", 900, "12", "14/10/2024", 100, true, true, "Coupons") ;






                Intent resultIntent = new Intent();
                resultIntent.putExtra("CouponData", couponList_responseModel1); // Put your result data here
                setResult(RESULT_OK, resultIntent);
                finish(); // Finish this activity to return to the calling activity
            }
        });


        /**
         * Interface Impl
         */

        applyCoupon_interface=new CouponList_RecyclerAdapter.ApplyCoupon_Interface() {
            @Override
            public void applyCoupon(CouponList_ResponseModel couponList_responseModel) {







                Intent resultIntent = new Intent();
                resultIntent.putExtra("CouponData", couponList_responseModel); // Put your result data here
                setResult(RESULT_OK, resultIntent);
                finish(); // Finish this activity to return to the calling activity
            }
        };








        couponList_recyclerAdapter = new CouponList_RecyclerAdapter(getApplicationContext(),applyCoupon_interface);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.couponListRecycler.setLayoutManager(layoutManager);
        binding.couponListRecycler.setAdapter(couponList_recyclerAdapter);
        binding.couponListRecycler.addItemDecoration(new VerticalSpaceItemDecoration(getApplicationContext(), spaceBetweenItemsPx));

        getCouponList();
        setCouponList();




        binding.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                setResult(RESULT_CANCELED, resultIntent);
                finish();

            }
        });
    }




    public void getCouponList(){
        checkout_viewModel.GetCouponList_Callback();
        binding.loadingLottie.setVisibility(View.VISIBLE);
    }



    public void setCouponList(){



        checkout_viewModel.getCouponList().observe(this, new Observer<List<CouponList_ResponseModel>>() {
            @Override
            public void onChanged(List<CouponList_ResponseModel> couponList_responseModels) {

                if (couponList_responseModels!=null){
                    if (couponList_responseModels.size()!=0){
                        binding.loadingLottie.setVisibility(View.GONE);
                        binding.notFoundImg.setVisibility(View.GONE);
                        binding.couponListRecycler.setVisibility(View.VISIBLE);

                        couponList_recyclerAdapter.setDataList(couponList_responseModels);
                    }else {
                        binding.loadingLottie.setVisibility(View.GONE);
                        binding.notFoundImg.setVisibility(View.VISIBLE);
                        binding.couponListRecycler.setVisibility(View.GONE);

                        couponList_recyclerAdapter.setDataList(null);
                    }

                }else {
                    couponList_recyclerAdapter.setDataList(null);
                    binding.loadingLottie.setVisibility(View.GONE);
                    binding.notFoundImg.setVisibility(View.VISIBLE);
                    binding.couponListRecycler.setVisibility(View.GONE);
                }
            }
        });




    }
    private void ViewInit() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        checkout_viewModel = viewModelProvider.get(Checkout_ViewModel.class);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent resultIntent = new Intent();
        setResult(RESULT_CANCELED, resultIntent);
        finish();
    }
}