package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ort.fineart.Model.Request_Model.WishList.AddRemove_WishListProduct_RequestModel;
import com.ort.fineart.Model.Response_Model.Home.HomeBestSeller_ResponseModel;
import com.ort.fineart.Model.Response_Model.Home.HomeDealOfDay_ResponseModel;
import com.ort.fineart.Model.Response_Model.Hub.GetHubProductListByCategory_ResponseModel;
import com.ort.fineart.Model.Response_Model.ViewAll_Deal_and_BestSeller_Product;
import com.ort.fineart.R;
import com.ort.fineart.Recycler_Adapter.BestSeller_RecyclerviewAdapter;
import com.ort.fineart.Recycler_Adapter.ViewAllDeal_and_BestSeller_RecyclerAdapter;
import com.ort.fineart.Recycler_Adapter.ViewAllProduct_RecyclerAdapter;
import com.ort.fineart.Ui.Activity.Authentication.Authentication_Activity;
import com.ort.fineart.Ui.Fragment.Category_Data;
import com.ort.fineart.Ui.Fragment.Filter_View_All_Product_Fragment;
import com.ort.fineart.Utils.AddRemoveProductWishlist_Interface;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.Utils.ShowToast;
import com.ort.fineart.ViewModel.HomeScreen_ViewModel;
import com.ort.fineart.ViewModel.Hub_ViewModel;
import com.ort.fineart.ViewModel.WishList_ViewModel;
import com.ort.fineart.databinding.ActivitySpecifeProductDetailsBinding;
import com.ort.fineart.databinding.ActivityViewAllProductBinding;

import java.util.ArrayList;
import java.util.List;

public class View_All_Product_Activity extends AppCompatActivity {
    ActivityViewAllProductBinding binding;
    HomeScreen_ViewModel homeScreen_viewModel;
    WishList_ViewModel wishList_viewModel;
    Hub_ViewModel hub_viewModel;
    AddRemoveProductWishlist_Interface addRemoveProductWishlist_interface;


    ViewAllDeal_and_BestSeller_RecyclerAdapter viewAllProduct_recyclerAdapter;
    String DealName;
    String CategoryId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewAllProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewInit();
        Intent intent = getIntent();
        DealName=intent.getStringExtra("AppBarName");
        CategoryId=intent.getStringExtra("CategoryId");

        Log.e("SingleProductCategoryId",CategoryId);

        /**
         * Interface Implementation
         */
        addRemoveProductWishlist_interface=new AddRemoveProductWishlist_Interface() {
            @Override
            public void add_Or_remove_productWish(Integer varientId) {
                SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());

                String userToken = sharedPreferenceManager.getUserToken();

                if (userToken != null && !userToken.isEmpty()) {
                    wishList_viewModel.AddToWishList_Callback(varientId);
                    wishList_viewModel.addToWishList().observe(View_All_Product_Activity.this, new Observer<AddRemove_WishListProduct_RequestModel>() {
                        @Override
                        public void onChanged(AddRemove_WishListProduct_RequestModel addRemove_wishListProduct_requestModel) {

                            if (addRemove_wishListProduct_requestModel!=null){

                                ShowToast.ShowMsgToast(addRemove_wishListProduct_requestModel.getMessge(),View_All_Product_Activity.this);

                            }
                            wishList_viewModel.addToWishList().removeObserver(this);
                        }
                    });
                } else {
                    Intent intent = new Intent(getApplicationContext(), Authentication_Activity.class);
                    startActivity(intent);

                }
            }
        };

        /**
         * End
         */


        binding.appbarNameTxt.setText(intent.getStringExtra("AppBarName"));




        binding.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        binding.filterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filter_View_All_Product_Fragment fitter_sheet = new Filter_View_All_Product_Fragment();
                fitter_sheet.show(getSupportFragmentManager(), fitter_sheet.getTag());

            }
        });


        ArrayList<Category_Data> dataList = new ArrayList<>();
        dataList.add(new Category_Data("Fabric Jewellery", 101));
        dataList.add(new Category_Data("Flower Jewellery", 102));
        dataList.add(new Category_Data("Beaded Jewellery", 103));
        dataList.add(new Category_Data("Terracotta Jewellery", 104));
        dataList.add(new Category_Data("Painting or clay Jewellery", 105));
        dataList.add(new Category_Data("Kundan Jewellery", 106));
        dataList.add(new Category_Data("Traditional Earings", 107));

        dataList.add(new Category_Data("Western Earings", 108));
        dataList.add(new Category_Data("Fabric Bangles", 109));
        dataList.add(new Category_Data("Silk Thread Bangles", 110));
        dataList.add(new Category_Data("Ravi", 111));


        viewAllProduct_recyclerAdapter = new ViewAllDeal_and_BestSeller_RecyclerAdapter(getApplicationContext(),addRemoveProductWishlist_interface);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        binding.viewAllRecyclerview.setLayoutManager(layoutManager);
        binding.viewAllRecyclerview.setAdapter(viewAllProduct_recyclerAdapter);


    }





    private void ViewInit() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        homeScreen_viewModel = viewModelProvider.get(HomeScreen_ViewModel.class);
        hub_viewModel=viewModelProvider.get(Hub_ViewModel.class);
    }



    public void GetData(String Type){

        if (Type.equals("Deal of The Day")){
            /**
             * Deal of the day
             */
            homeScreen_viewModel.GetDealsOfTheDay_Callback();
            homeScreen_viewModel.get_deal_of_day().observe(View_All_Product_Activity.this, new Observer<List<HomeDealOfDay_ResponseModel>>() {
                @Override
                public void onChanged(List<HomeDealOfDay_ResponseModel> homeDealOfDay_responseModels) {
                    if (homeDealOfDay_responseModels!=null&&!homeDealOfDay_responseModels.isEmpty()&&homeDealOfDay_responseModels.size()!=0){

                        List<ViewAll_Deal_and_BestSeller_Product>viewAll_deal_and_bestSeller=new ArrayList<>();
                        for (HomeDealOfDay_ResponseModel model:homeDealOfDay_responseModels){
                           if (model.getProductPrice().toString().length()!=0){
                               viewAll_deal_and_bestSeller.add(new ViewAll_Deal_and_BestSeller_Product(
                                       model.getId(),
                                       model.getProductCategory(),
                                       model.getProductSubCategory(),
                                       model.getProductName(),
                                       model.getProductNumber(),
                                       model.getHSNCODE(),
                                       model.getProductDescription(),
                                       model.getProductShortDescription(),
                                       model.getProductUsage(),
                                       model.getProductMoreDetails(),
                                       model.isIsactive(),
                                       model.isDelivery_Charges(),
                                       model.isBest_seller(),
                                       model.isDeal_of_the_day(),
                                       model.getAverageRating(),
                                       model.isStatus(),
                                       model.getProductImage(),
                                       model.getProductSize(),
                                       model.getProductWeight(),
                                       model.getProductColor(),
                                       model.getProductDiscount(),
                                       model.getProductDiscountMode(),
                                       model.getProductQuantity(),
                                       model.getProductPrice(),
                                       model.getProductDiscountPrice(),
                                       model.getProduct_VarientId()

                               ));
                           }

                        }


                        viewAllProduct_recyclerAdapter.setDataList(viewAll_deal_and_bestSeller);

                    }else {

                        viewAllProduct_recyclerAdapter.setDataList(null);

                    }

                }
            });



        } else if (Type.equals("All Product")) {
            hub_viewModel.GetHubListByCategory_Callback(CategoryId,"");


            hub_viewModel.getHubProduct_List().observe(View_All_Product_Activity.this, new Observer<List<GetHubProductListByCategory_ResponseModel>>() {
                @Override
                public void onChanged(List<GetHubProductListByCategory_ResponseModel> getHubProductListByCategory_responseModels) {
                    if (getHubProductListByCategory_responseModels!=null&&getHubProductListByCategory_responseModels.size()!=0) {

                        List<ViewAll_Deal_and_BestSeller_Product>viewAll_deal_and_bestSeller=new ArrayList<>();
                        for (GetHubProductListByCategory_ResponseModel model:getHubProductListByCategory_responseModels){
                            if (model.getProductPrice().toString().length()!=0){
                                viewAll_deal_and_bestSeller.add(new ViewAll_Deal_and_BestSeller_Product(
                                        model.getId(),
                                        model.getProductCategory(),
                                        model.getProductSubCategory(),
                                        model.getProductName(),
                                        model.getProductNumber(),
                                        model.getHSNCODE(),
                                        model.getProductDescription(),
                                        model.getProductShortDescription(),
                                        model.getProductUsage(),
                                        model.getProductMoreDetails(),
                                        model.isIsactive(),
                                        model.isDelivery_Charges(),
                                        model.isBest_seller(),
                                        model.isDeal_of_the_day(),
                                        model.getAverageRating(),
                                        model.isStatus(),
                                        model.getProductImage(),
                                        model.getProductSize().toString(),
                                        model.getProductWeight(),
                                        model.getProductColor(),
                                        model.getProductDiscount(),
                                        model.getProductDiscountMode(),
                                        model.getProductQuantity(),
                                        model.getProductPrice(),
                                        model.getProductDiscountPrice(),
                                        model.getProduct_VarientId()

                                ));
                            }

                        }

                        viewAllProduct_recyclerAdapter.setDataList(viewAll_deal_and_bestSeller);

                    }else {

                        viewAllProduct_recyclerAdapter.setDataList(null);

                    }
                }
            });








        } else {
            /**
             * Best Seller
             */
            homeScreen_viewModel.GetBestSeller_Callback();
            homeScreen_viewModel.getBestSeller().observe(View_All_Product_Activity.this, new Observer<List<HomeBestSeller_ResponseModel>>() {
                @Override
                public void onChanged(List<HomeBestSeller_ResponseModel> homeBestSeller_responseModels) {
                    if (homeBestSeller_responseModels!=null&&!homeBestSeller_responseModels.isEmpty()&&homeBestSeller_responseModels.size()!=0){


                        List<ViewAll_Deal_and_BestSeller_Product>viewAll_deal_and_bestSeller=new ArrayList<>();
                        for (HomeBestSeller_ResponseModel model:homeBestSeller_responseModels){

                            if (model.getProductPrice().toString().length()!=0){
                                viewAll_deal_and_bestSeller.add(new ViewAll_Deal_and_BestSeller_Product(
                                        model.getId(),
                                        model.getProductCategory(),
                                        model.getProductSubCategory(),
                                        model.getProductName(),
                                        model.getProductNumber(),
                                        model.getHSNCODE(),
                                        model.getProductDescription(),
                                        model.getProductShortDescription(),
                                        model.getProductUsage(),
                                        model.getProductMoreDetails(),
                                        model.isIsactive(),
                                        model.isDelivery_Charges(),
                                        model.isBest_seller(),
                                        model.isDeal_of_the_day(),
                                        model.getAverageRating(),
                                        model.isStatus(),
                                        model.getProductImage(),
                                        model.getProductSize(),
                                        model.getProductWeight(),
                                        model.getProductColor(),
                                        model.getProductDiscount(),
                                        model.getProductDiscountMode(),
                                        model.getProductQuantity(),
                                        model.getProductPrice(),
                                        model.getProductDiscountPrice(),
                                        model.getProduct_VarientId()

                                ));
                            }


                        }


                        viewAllProduct_recyclerAdapter.setDataList(viewAll_deal_and_bestSeller);

                    }else {
                        viewAllProduct_recyclerAdapter.setDataList(null);
                    }

                }
            });


        }


    }












    @Override
    protected void onResume() {
        super.onResume();
       GetData(DealName);
    }
}