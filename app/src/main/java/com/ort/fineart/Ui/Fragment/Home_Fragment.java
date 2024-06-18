package com.ort.fineart.Ui.Fragment;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ort.fineart.Banner_Adapter.BottomHomeBanner_Adapter;
import com.ort.fineart.Banner_Adapter.CustomerTestimonial_Banner;
import com.ort.fineart.Banner_Adapter.TopHomeBanner_Adapter;
import com.ort.fineart.Model.Request_Model.WishList.AddRemove_WishListProduct_RequestModel;
import com.ort.fineart.Model.Response_Model.Home.HomeBestSeller_ResponseModel;
import com.ort.fineart.Model.Response_Model.Home.HomeBottomBanner_ResponseModel;
import com.ort.fineart.Model.Response_Model.Home.HomeDealOfDay_ResponseModel;
import com.ort.fineart.Model.Response_Model.Home.HomeTestimonial_ResponseModel;
import com.ort.fineart.Model.Response_Model.Home.HomeTopBanner_ResponseModel;
import com.ort.fineart.Recycler_Adapter.BestSeller_RecyclerviewAdapter;
import com.ort.fineart.Recycler_Adapter.DealsOfDay_RecyclerAdapter;
import com.ort.fineart.Ui.Activity.Authentication.Authentication_Activity;
import com.ort.fineart.Ui.Activity.MyCart_Activity;
import com.ort.fineart.Ui.Activity.My_Wishlist_Activity;
import com.ort.fineart.Ui.Activity.SpecifeProductDetails.SpecifeProductDetails_Activity;
import com.ort.fineart.Ui.Activity.View_All_Product_Activity;
import com.ort.fineart.Utils.AddRemoveProductWishlist_Interface;
import com.ort.fineart.Utils.DpToPixcel;
import com.ort.fineart.Utils.HorizontalSpaceItemDecoration;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.Utils.ShowToast;
import com.ort.fineart.ViewModel.HomeScreen_ViewModel;
import com.ort.fineart.ViewModel.WishList_ViewModel;
import com.ort.fineart.databinding.FragmentHomeBinding;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Home_Fragment extends Fragment {

    HomeScreen_ViewModel homeScreen_viewModel;

    int RecyclerViewCardMargin=16;
    FragmentHomeBinding binding;
    List<String> Horizontal_Banner_ArrayList=new ArrayList<>();
    private BestSeller_RecyclerviewAdapter bestSeller_recyclerviewAdapter;
    private DealsOfDay_RecyclerAdapter dealsOfDay_recyclerviewAdapter;
    AddRemoveProductWishlist_Interface addRemoveProductWishlist_interface;
    WishList_ViewModel wishList_viewModel;
    public Home_Fragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int spaceBetweenItemsPx = DpToPixcel.dpToPx(getContext(), RecyclerViewCardMargin);
        ViewInit();
        GetHomeScreenData();

        /**
         * Interface
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









        bestSeller_recyclerviewAdapter = new BestSeller_RecyclerviewAdapter(getContext(),addRemoveProductWishlist_interface);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.bestSellerRecyclerView.setLayoutManager(layoutManager);
        binding.bestSellerRecyclerView.setAdapter(bestSeller_recyclerviewAdapter);
        binding.bestSellerRecyclerView.addItemDecoration(new HorizontalSpaceItemDecoration(getContext(), spaceBetweenItemsPx));

        dealsOfDay_recyclerviewAdapter = new DealsOfDay_RecyclerAdapter(getContext(),addRemoveProductWishlist_interface);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.dealOfDayRecyclerView.setLayoutManager(layoutManager1);
        binding.dealOfDayRecyclerView.setAdapter(dealsOfDay_recyclerviewAdapter);
        binding.dealOfDayRecyclerView.addItemDecoration(new HorizontalSpaceItemDecoration(getContext(), spaceBetweenItemsPx));

        binding.viewAllDealsTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), View_All_Product_Activity.class);
                intent.putExtra("AppBarName","Deal of The Day");
                intent.putExtra("CategoryId","0");
                startActivity(intent);
            }
        });
        binding.viewAllSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), View_All_Product_Activity.class);
                intent.putExtra("AppBarName","Best Seller");
                intent.putExtra("CategoryId","0");
                startActivity(intent);
            }
        });




    }

    private void GetHomeScreenData() {

        /*
            Top Banner
        */
        homeScreen_viewModel.GetTopBanner_Callback();
        homeScreen_viewModel.getTopBanner().observe(getViewLifecycleOwner(), new Observer<List<HomeTopBanner_ResponseModel>>() {
            @Override
            public void onChanged(List<HomeTopBanner_ResponseModel> homeTopBanner_responseModels) {
                if (homeTopBanner_responseModels!=null&&!homeTopBanner_responseModels.isEmpty()){

                    binding.ViewPager.setAdapter(new TopHomeBanner_Adapter(getContext(), homeTopBanner_responseModels));
                    binding.tabLayout.setupWithViewPager(binding.ViewPager);
                    autoImageSlide();
                }else {


                }
            }
        });






        /*
            Deal Of The Days
        */

        homeScreen_viewModel.GetDealsOfTheDay_Callback();
        homeScreen_viewModel.get_deal_of_day().observe(getViewLifecycleOwner(), new Observer<List<HomeDealOfDay_ResponseModel>>() {
            @Override
            public void onChanged(List<HomeDealOfDay_ResponseModel> homeDealOfDay_responseModels) {
                if (homeDealOfDay_responseModels!=null&&!homeDealOfDay_responseModels.isEmpty()&&homeDealOfDay_responseModels.size()!=0){
                    List<HomeDealOfDay_ResponseModel> dealList=new ArrayList<>();
                    for (HomeDealOfDay_ResponseModel model:homeDealOfDay_responseModels){
                        if (model.getProductPrice().toString().length()!=0){
                            dealList.add(model);
                        }
                    }

                    dealsOfDay_recyclerviewAdapter.setDataList(dealList);

                }else {

                    dealsOfDay_recyclerviewAdapter.setDataList(null);

                }
            }
        });




        /*
            Best Seller
        */

        homeScreen_viewModel.GetBestSeller_Callback();
        homeScreen_viewModel.getBestSeller().observe(getViewLifecycleOwner(), new Observer<List<HomeBestSeller_ResponseModel>>() {
            @Override
            public void onChanged(List<HomeBestSeller_ResponseModel> homeBestSeller_responseModels) {
                if (homeBestSeller_responseModels!=null&&!homeBestSeller_responseModels.isEmpty()&&homeBestSeller_responseModels.size()!=0){
                    List<HomeBestSeller_ResponseModel>bestSeller=new ArrayList<>();
                    for (HomeBestSeller_ResponseModel model:homeBestSeller_responseModels){
                        if (model.getProductPrice().toString().length()!=0){
                            bestSeller.add(model);
                        }
                    }
                    bestSeller_recyclerviewAdapter.setDataList(bestSeller);

                }else {
                    bestSeller_recyclerviewAdapter.setDataList(null);
                }
            }
        });

        /*
            Testimonial Data
         */
        homeScreen_viewModel.GetTestimonial_Callback();
        homeScreen_viewModel.getTestimonial().observe(getViewLifecycleOwner(), new Observer<List<HomeTestimonial_ResponseModel>>() {
            @Override
            public void onChanged(List<HomeTestimonial_ResponseModel> homeTestimonial_responseModels) {

                if (homeTestimonial_responseModels!=null&&!homeTestimonial_responseModels.isEmpty()){
                    binding.TestimonialViewPager.setAdapter(new CustomerTestimonial_Banner(getContext(), homeTestimonial_responseModels));

                     autoSlideTestimonial(binding.TestimonialViewPager,homeTestimonial_responseModels.size());

                }else {

                }


            }
        });


        /*
            Bottom Banner
        */
        homeScreen_viewModel.GetBottomBanner_Callback();
        homeScreen_viewModel.getBottomBanner().observe(getViewLifecycleOwner(), new Observer<List<HomeBottomBanner_ResponseModel>>() {
            @Override
            public void onChanged(List<HomeBottomBanner_ResponseModel> homeBottomBanner_responseModels) {
                if (homeBottomBanner_responseModels!=null&&!homeBottomBanner_responseModels.isEmpty()){

                    binding.BottomViewPager.setAdapter(new BottomHomeBanner_Adapter(getContext(), homeBottomBanner_responseModels));

                    autoSlideTestimonial(binding.BottomViewPager,homeBottomBanner_responseModels.size());

                }else {


                }
            }
        });




    }


    private void autoImageSlide() {
        final long DELAY_MS = 3000;
        final long PERIOD_MS = 3000;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (binding.ViewPager.getCurrentItem() == Horizontal_Banner_ArrayList.size() - 1) {
                    binding.ViewPager.setCurrentItem(0);
                } else {
                    binding.ViewPager.setCurrentItem(binding.ViewPager.getCurrentItem() + 1, true);
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, DELAY_MS, PERIOD_MS);

    }
    private void autoSlideTestimonial(ViewPager viewPager,int ListSize) {
        final long DELAY_MS = 4000;
        final long PERIOD_MS = 4000;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (viewPager.getCurrentItem() == ListSize - 1) {
                    viewPager.setCurrentItem(0);
                } else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, DELAY_MS, PERIOD_MS);

    }

    private void ViewInit() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        homeScreen_viewModel = viewModelProvider.get(HomeScreen_ViewModel.class);
        wishList_viewModel= viewModelProvider.get(WishList_ViewModel.class);
    }
}