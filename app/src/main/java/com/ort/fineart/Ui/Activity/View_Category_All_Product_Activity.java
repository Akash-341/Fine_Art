package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ort.fineart.Model.Request_Model.WishList.AddRemove_WishListProduct_RequestModel;
import com.ort.fineart.Model.Response_Model.Hub.GetHubProductListByCategory_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.Recycler_Adapter.ViewAllProduct_RecyclerAdapter;
import com.ort.fineart.Ui.Activity.Authentication.Authentication_Activity;
import com.ort.fineart.Ui.Fragment.Category_Data;
import com.ort.fineart.Ui.Fragment.Filter_View_All_Product_Fragment;
import com.ort.fineart.Utils.AddRemoveProductWishlist_Interface;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.Utils.ShowToast;
import com.ort.fineart.ViewModel.Hub_ViewModel;
import com.ort.fineart.ViewModel.WishList_ViewModel;
import com.ort.fineart.databinding.ActivityViewAllProductBinding;
import com.ort.fineart.databinding.ActivityViewCategoryAllProductBinding;

import java.util.ArrayList;
import java.util.List;

public class View_Category_All_Product_Activity extends AppCompatActivity {
    ActivityViewCategoryAllProductBinding binding;
    ViewAllProduct_RecyclerAdapter viewAllProduct_recyclerAdapter;
    String CategoryId;
    String SubCategoryId = "";
    Hub_ViewModel hub_viewModel;
    WishList_ViewModel wishList_viewModel;
    Filter_View_All_Product_Fragment.SelectedSubCategory_Interface selectedSubCategory_interface;
    AddRemoveProductWishlist_Interface addRemoveProductWishlist_interface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewCategoryAllProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        binding.appbarNameTxt.setText(intent.getStringExtra("AppBarName"));
        CategoryId = intent.getStringExtra("CategoryId");
        ViewInit();


        /**
         * Interface Implementation
         */
        addRemoveProductWishlist_interface = new AddRemoveProductWishlist_Interface() {
            @Override
            public void add_Or_remove_productWish(Integer varientId) {
                SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());

                String userToken = sharedPreferenceManager.getUserToken();

                if (userToken != null && !userToken.isEmpty()) {
                    wishList_viewModel.AddToWishList_Callback(varientId);
                    wishList_viewModel.addToWishList().observe(View_Category_All_Product_Activity.this, new Observer<AddRemove_WishListProduct_RequestModel>() {
                        @Override
                        public void onChanged(AddRemove_WishListProduct_RequestModel addRemove_wishListProduct_requestModel) {

                            if (addRemove_wishListProduct_requestModel != null) {

                                ShowToast.ShowMsgToast(addRemove_wishListProduct_requestModel.getMessge(), getApplicationContext());

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

        selectedSubCategory_interface = new Filter_View_All_Product_Fragment.SelectedSubCategory_Interface() {
            @Override
            public void selected_subCategory(String Category, String Subcategory) {
                /**
                 * Checking Filter Status
                 */
                SharedPreferences prefs = getApplicationContext().getSharedPreferences("HubSubCategoryFilter", Context.MODE_PRIVATE);
                if (prefs.getString("SubCategoryId", "").equals("NotFound")) {
                    binding.filterIcon.setImageDrawable(getDrawable(R.drawable.filter_icon));
                } else {
                    binding.filterIcon.setImageDrawable(getDrawable(R.drawable.filter_active_icon));
                }

                CategoryId = Category;
                SubCategoryId = Subcategory;
                Log.e("CatergoryIdView", "Category:" + CategoryId + ",Subcategory:" + Subcategory);
                GetHubList(CategoryId, SubCategoryId);
            }
        };

        /**
         * End
         */
        binding.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        binding.filterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filter_View_All_Product_Fragment fitter_sheet = new Filter_View_All_Product_Fragment(CategoryId, selectedSubCategory_interface);
                fitter_sheet.show(getSupportFragmentManager(), fitter_sheet.getTag());

            }
        });


        viewAllProduct_recyclerAdapter = new ViewAllProduct_RecyclerAdapter(getApplicationContext(),addRemoveProductWishlist_interface);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        binding.viewCategoryAllRecyclerview.setLayoutManager(layoutManager);
        binding.viewCategoryAllRecyclerview.setAdapter(viewAllProduct_recyclerAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();

        GetHubList(CategoryId, SubCategoryId);
        SetHubList();


    }


    private void SetHubList() {

        hub_viewModel.getHubProduct_List().observe(View_Category_All_Product_Activity.this, new Observer<List<GetHubProductListByCategory_ResponseModel>>() {
            @Override
            public void onChanged(List<GetHubProductListByCategory_ResponseModel> getHubProductListByCategory_responseModels) {
                if (getHubProductListByCategory_responseModels != null && getHubProductListByCategory_responseModels.size() != 0) {
                    List<GetHubProductListByCategory_ResponseModel> getHubProductListByCategory = new ArrayList<>();
                    for (GetHubProductListByCategory_ResponseModel model : getHubProductListByCategory_responseModels) {
                        if (model.getProductPrice().toString().length() != 0) {
                            getHubProductListByCategory.add(model);
                        }
                    }

                    if (getHubProductListByCategory.size() != 0) {
                        viewAllProduct_recyclerAdapter.setDataList(getHubProductListByCategory);

                        binding.loadingLottie.setVisibility(View.GONE);
                        binding.notFoundImg.setVisibility(View.GONE);
                        binding.viewCategoryAllRecyclerview.setVisibility(View.VISIBLE);
                    } else {
                        binding.loadingLottie.setVisibility(View.GONE);
                        binding.notFoundImg.setVisibility(View.VISIBLE);
                        binding.viewCategoryAllRecyclerview.setVisibility(View.GONE);
                        viewAllProduct_recyclerAdapter.setDataList(null);
                    }


                } else {
                    binding.loadingLottie.setVisibility(View.GONE);
                    binding.notFoundImg.setVisibility(View.VISIBLE);
                    binding.viewCategoryAllRecyclerview.setVisibility(View.GONE);
                    viewAllProduct_recyclerAdapter.setDataList(null);
                }
            }
        });

    }

    private void GetHubList(String CategoryId, String SubCategory) {
        hub_viewModel.GetHubListByCategory_Callback(CategoryId, SubCategory);
        binding.loadingLottie.setVisibility(View.VISIBLE);


    }

    private void ViewInit() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        hub_viewModel = viewModelProvider.get(Hub_ViewModel.class);
        wishList_viewModel=viewModelProvider.get(WishList_ViewModel.class);

    }

}