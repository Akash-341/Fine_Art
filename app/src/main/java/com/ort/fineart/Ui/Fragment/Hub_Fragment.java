package com.ort.fineart.Ui.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ort.fineart.Model.Request_Model.WishList.AddRemove_WishListProduct_RequestModel;
import com.ort.fineart.Model.Response_Model.Hub.GetHubProductListByCategory_ResponseModel;
import com.ort.fineart.Model.Response_Model.Hub.Hub_CategoryList_ResponseModel;
import com.ort.fineart.Recycler_Adapter.HubCategory_RecyclerAdapter;
import com.ort.fineart.Recycler_Adapter.Hub_RecyclerAdapter;
import com.ort.fineart.Ui.Activity.Authentication.Authentication_Activity;
import com.ort.fineart.Ui.Activity.MyCart_Activity;
import com.ort.fineart.Ui.Activity.My_Wishlist_Activity;
import com.ort.fineart.Ui.Activity.View_Category_All_Product_Activity;
import com.ort.fineart.Utils.AddRemoveProductWishlist_Interface;
import com.ort.fineart.Utils.DpToPixcel;
import com.ort.fineart.Utils.HorizontalSpaceItemDecoration;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.Utils.ShowToast;
import com.ort.fineart.ViewModel.CategoriesViewModel;
import com.ort.fineart.ViewModel.Hub_ViewModel;
import com.ort.fineart.ViewModel.WishList_ViewModel;
import com.ort.fineart.databinding.FragmentHubBinding;

import java.util.ArrayList;
import java.util.List;


public class Hub_Fragment extends Fragment {


    FragmentHubBinding binding;

    private CategoriesViewModel categoryViewModel;

    Hub_RecyclerAdapter hub_recyclerAdapter;
    HubCategory_RecyclerAdapter hubCategory_recyclerAdapter;
    HubCategory_RecyclerAdapter.CategorySelection_Interface categorySelection_interface;
    AddRemoveProductWishlist_Interface addRemoveProductWishlist_interface;




    int RecyclerViewCardMargin=12;
    String AppbarName;
    String Category_Id="";




    Hub_ViewModel hub_viewModel;
    WishList_ViewModel wishList_viewModel;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding=FragmentHubBinding.inflate(inflater, container, false);



        return binding.getRoot();

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int spaceBetweenItemsPx = DpToPixcel.dpToPx(getContext(), RecyclerViewCardMargin);
        ViewInit();

        /**
         * Interface Implementation
         */
        addRemoveProductWishlist_interface=new AddRemoveProductWishlist_Interface() {
            @Override
            public void add_Or_remove_productWish(Integer varientId) {
                SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(getContext());

                String userToken = sharedPreferenceManager.getUserToken();

                if (userToken != null && !userToken.isEmpty()) {
                    wishList_viewModel.AddToWishList_Callback(varientId);
                    wishList_viewModel.addToWishList().observe(getViewLifecycleOwner(), new Observer<AddRemove_WishListProduct_RequestModel>() {
                        @Override
                        public void onChanged(AddRemove_WishListProduct_RequestModel addRemove_wishListProduct_requestModel) {

                            if (addRemove_wishListProduct_requestModel!=null){

                                ShowToast.ShowMsgToast(addRemove_wishListProduct_requestModel.getMessge(),getContext());

                            }
                            wishList_viewModel.addToWishList().removeObserver(this);
                        }
                    });
                } else {
                    Intent intent = new Intent(getContext(), Authentication_Activity.class);
                    startActivity(intent);

                }
            }
        };

        /**
         * End
         */


        GetCategoryList();
        SetHubList();
        ClearSubFilter();



        categorySelection_interface=new HubCategory_RecyclerAdapter.CategorySelection_Interface() {
            @Override
            public void CategorySelection(String SelectionCat, Integer CategoryId) {
                binding.catergoryTypeTxt.setText(SelectionCat);
                AppbarName=SelectionCat;
                Category_Id= String.valueOf(CategoryId);
                GetHubList(String.valueOf(CategoryId));
            }
        };






        hub_recyclerAdapter = new Hub_RecyclerAdapter(getContext(),addRemoveProductWishlist_interface);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        binding.hubRecyclerView.setLayoutManager(layoutManager);
        binding.hubRecyclerView.setAdapter(hub_recyclerAdapter);

        hubCategory_recyclerAdapter = new HubCategory_RecyclerAdapter(getContext(),categorySelection_interface);
        binding.hubCatergoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        //binding.hubCatergoryRecyclerView.setLayoutManager(layoutManager);
        binding.hubCatergoryRecyclerView.setAdapter(hubCategory_recyclerAdapter);
        binding.hubCatergoryRecyclerView.addItemDecoration(new HorizontalSpaceItemDecoration(getContext(), spaceBetweenItemsPx));

        binding.viewAllTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), View_Category_All_Product_Activity.class);
                intent.putExtra("AppBarName",AppbarName);
                intent.putExtra("CategoryId",Category_Id);
                startActivity(intent);
            }
        });





        binding.cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceManager sharedPreferenceManager=new SharedPreferenceManager(getContext());

                String userToken = sharedPreferenceManager.getUserToken();

                if (userToken != null && !userToken.isEmpty()) {
                    Intent intent=new Intent(getContext(), MyCart_Activity.class);
                    startActivity(intent);
                } else {
                    Intent intent=new Intent(getContext(), Authentication_Activity.class);
                    startActivity(intent);

                }

            }
        });
        binding.favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferenceManager sharedPreferenceManager=new SharedPreferenceManager(getContext());

                String userToken = sharedPreferenceManager.getUserToken();

                if (userToken != null && !userToken.isEmpty()) {
                    Intent intent=new Intent(getContext(), My_Wishlist_Activity.class);
                    startActivity(intent);
                } else {
                    Intent intent=new Intent(getContext(), Authentication_Activity.class);
                    startActivity(intent);

                }

            }
        });







    }

    private void ClearSubFilter() {
        SharedPreferences.Editor editor = getContext().getSharedPreferences("HubSubCategoryFilter", Context.MODE_PRIVATE).edit();
        editor.putString("CategoryId", "NotFound");
        editor.putString("SubCategoryId", "NotFound");
        editor.putString("SubCategoryName", "NotFound");
        editor.apply();
    }

    private void GetCategoryList() {

        hub_viewModel.GetHubCategory_Callback();
        hub_viewModel.getHubCategory().observe(getViewLifecycleOwner(), new Observer<List<Hub_CategoryList_ResponseModel>>() {
            @Override
            public void onChanged(List<Hub_CategoryList_ResponseModel> hub_categoryList_responseModels) {

                if (hub_categoryList_responseModels!=null&&hub_categoryList_responseModels.size()!=0){
                    hubCategory_recyclerAdapter.setCategoryList(hub_categoryList_responseModels);
                      binding.catergoryTypeTxt.setText(hub_categoryList_responseModels.get(0).getCategoryName());
                    AppbarName=hub_categoryList_responseModels.get(0).getCategoryName();
                    Category_Id= String.valueOf(hub_categoryList_responseModels.get(0).getId());

                    GetHubList(String.valueOf(hub_categoryList_responseModels.get(0).getId()));


                }else {


                }


            }
        });



    }
    private void SetHubList() {

        hub_viewModel.getHubProduct_List().observe(getViewLifecycleOwner(), new Observer<List<GetHubProductListByCategory_ResponseModel>>() {
            @Override
            public void onChanged(List<GetHubProductListByCategory_ResponseModel> getHubProductListByCategory_responseModels) {
                if (getHubProductListByCategory_responseModels!=null&&getHubProductListByCategory_responseModels.size()!=0){
                    List<GetHubProductListByCategory_ResponseModel> getHubProductListByCategory=new ArrayList<>();
                    for (GetHubProductListByCategory_ResponseModel model:getHubProductListByCategory_responseModels){
                        if (model.getProductPrice().toString().length()!=0){
                            getHubProductListByCategory.add(model);
                        }
                    }

                    if (getHubProductListByCategory.size()!=0){
                        binding.loadingLottie.setVisibility(View.GONE);
                        binding.notFoundImg.setVisibility(View.GONE);
                        binding.hubRecyclerView.setVisibility(View.VISIBLE);
                        hub_recyclerAdapter.setDataList(getHubProductListByCategory);
                    }else {
                        binding.loadingLottie.setVisibility(View.GONE);
                        binding.notFoundImg.setVisibility(View.VISIBLE);
                        binding.hubRecyclerView.setVisibility(View.GONE);
                        hub_recyclerAdapter.setDataList(null);
                    }



                }else {
                    binding.loadingLottie.setVisibility(View.GONE);
                    binding.notFoundImg.setVisibility(View.VISIBLE);
                    binding.hubRecyclerView.setVisibility(View.GONE);
                    hub_recyclerAdapter.setDataList(null);
                }
            }
        });


    }
    private void GetHubList(String CategoryId) {

        hub_viewModel.GetHubListByCategory_Callback(CategoryId,"");
        binding.loadingLottie.setVisibility(View.VISIBLE);




    }
    private void ViewInit() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        hub_viewModel = viewModelProvider.get(Hub_ViewModel.class);
        wishList_viewModel=viewModelProvider.get(WishList_ViewModel.class);
    }

}