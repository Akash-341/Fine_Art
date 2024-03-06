package com.ort.fineart.Ui.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.ort.fineart.Api_Handel.ApiService;
import com.ort.fineart.Model.Response_Model.BottomBannerData_ResponseModel;
import com.ort.fineart.Model.Response_Model.Product_ResponseModel;
import com.ort.fineart.Model.Response_Model.Testimonial_ResponseModel;
import com.ort.fineart.Model.Response_Model.TopBannerModel_ResponseModel;
import com.ort.fineart.Recycler_Adapter.DotdAdapter;
import com.ort.fineart.Recycler_Adapter.TestimonialAdapter;
import com.ort.fineart.Api_Handel.All_URL;
import com.ort.fineart.R;
import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.ViewModel.DotdViewModel;
import com.ort.fineart.ViewModel.TestimonialViewModel;
import com.ort.fineart.databinding.FragmentHomePageBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Home_page extends Fragment {

    FragmentHomePageBinding binding;
    private List<Product_ResponseModel> productList;
    private DotdAdapter productAdapter;
    private TestimonialAdapter testimonialAdapter;
    private DotdViewModel dotdViewModel;
    private List<Testimonial_ResponseModel> testimonialModelList;
    private TestimonialViewModel testimonialViewModel;
    private LiveData<List<Testimonial_ResponseModel>> testimonialListLiveData;
    private LiveData<List<Product_ResponseModel>> productLiveData;

    private int currentVisiblePosition = 0;
    private boolean isAutoScrolling = false;
    private static final long AUTO_SCROLL_DELAY = 3000; // 3 seconds

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dotdViewModel = new ViewModelProvider(this).get(DotdViewModel.class);
        testimonialViewModel = new ViewModelProvider(this).get(TestimonialViewModel.class);
        testimonialListLiveData = testimonialViewModel.getTestimonialListLiveData();
        productLiveData = dotdViewModel.getProductList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater, container, false);
//        flipperImages();
//        productList = initializeProductList();

        productAdapter = new DotdAdapter(productList, getActivity().getApplicationContext(), requireActivity());
        testimonialAdapter = new TestimonialAdapter(testimonialModelList);

        productAdapter.setOnWishlistClickListener(new DotdAdapter.OnWishlistClickListener() {
            @Override
            public void onWishlistClick(int position) {
//                toggleWishlist(position);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerDotd.setLayoutManager(layoutManager);
        binding.recyclerDotd.setAdapter(productAdapter);
        binding.bsrecycler.setLayoutManager(layoutManager1);
        binding.bsrecycler.setAdapter(productAdapter);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.testimonialRecycler.setLayoutManager(layoutManager2);
        binding.testimonialRecycler.setAdapter(testimonialAdapter);
        dotdViewModel.getProductList().observe(getViewLifecycleOwner(), productList -> {
            productAdapter.setProductList(productList);
            productAdapter.notifyDataSetChanged();
        });
        productLiveData.observe(getViewLifecycleOwner(), new Observer<List<Product_ResponseModel>>() {
            @Override
            public void onChanged(List<Product_ResponseModel> product_responseModels) {
                productAdapter.setProductList(product_responseModels);
                productAdapter.notifyDataSetChanged();
            }
        });
        testimonialListLiveData.observe(getViewLifecycleOwner(), new Observer<List<Testimonial_ResponseModel>>() {
            @Override
            public void onChanged(List<Testimonial_ResponseModel> testimonialModels) {
                // Update the UI with the new data
                testimonialAdapter.setTestimonialList(testimonialModels);
                testimonialAdapter.notifyDataSetChanged();
            }
        });
        startAutoScroll();



        fetchBottomBannerData();
        fetchTopBannerData();
        return binding.getRoot();
    }

    /*   private void flipperImages() {

           binding.homeSlider.setFlipInterval(2000);
           binding.homeSlider.startFlipping();

           binding.homeSlider.setInAnimation(getContext(), R.anim.slide_in_left);
           binding.homeSlider.setOutAnimation(getContext(), R.anim.slide_out_right);
       }*/


    /*private void toggleWishlist(int position) {

        dotdViewModel.toggleWishlist(position);
    }*/

    private void startAutoScroll() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (isAutoScrolling) {
                    currentVisiblePosition++;
                    if (currentVisiblePosition >= testimonialAdapter.getItemCount()) {
                        currentVisiblePosition = 0;
                    }
                    binding.testimonialRecycler.smoothScrollToPosition(currentVisiblePosition);
                    handler.postDelayed(this, AUTO_SCROLL_DELAY);
                }
            }
        };

        handler.postDelayed(runnable, AUTO_SCROLL_DELAY);
        isAutoScrolling = true;

        // Optionally, you can stop auto-scrolling when the RecyclerView is touched by the user
        binding.testimonialRecycler.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                stopAutoScroll();
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });
    }

    private void fetchBottomBannerData() {
        // Use Retrofit to make API call and update UI
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<List<BottomBannerData_ResponseModel>> call = apiService.getBottomBaner();

        call.enqueue(new Callback<List<BottomBannerData_ResponseModel>>() {
            @Override
            public void onResponse(Call<List<BottomBannerData_ResponseModel>> call, Response<List<BottomBannerData_ResponseModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Update UI with bottom banner data
                    List<BottomBannerData_ResponseModel> bottomBannerList = response.body();
                    updateBottomBannerUI(bottomBannerList);
                }
            }

            @Override
            public void onFailure(Call<List<BottomBannerData_ResponseModel>> call, Throwable t) {
                // Handle API call failure
            }
        });
    }
    private void fetchTopBannerData() {
        // Use Retrofit to make API call and update UI
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<List<TopBannerModel_ResponseModel>> call = apiService.getTopBanner();

        call.enqueue(new Callback<List<TopBannerModel_ResponseModel>>() {
            @Override
            public void onResponse(Call<List<TopBannerModel_ResponseModel>> call, Response<List<TopBannerModel_ResponseModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Update UI with bottom banner data
                    List<TopBannerModel_ResponseModel> topBannerList = response.body();
                    updateTopBannerUI(topBannerList);
                }
            }

            @Override
            public void onFailure(Call<List<TopBannerModel_ResponseModel>> call, Throwable t) {
                // Handle API call failure
            }
        });
    }
    private void updateBottomBannerUI(List<BottomBannerData_ResponseModel> bottomBannerList) {
        if (bottomBannerList != null && bottomBannerList.size() > 0) {
            BottomBannerData_ResponseModel bottomBanner = bottomBannerList.get(0);


            binding.textView1.setText(bottomBanner.getBannerName());
            binding.textView2.setText(bottomBanner.getBannerHeading());
            binding.textView3.setText(bottomBanner.getBannerText());

            Glide.with(requireContext())
                    .load(All_URL.imgURL + bottomBanner.getBannerImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(binding.backgroundImage);

            Glide.with(requireContext())
                    .load(All_URL.imgURL + bottomBanner.getBannerInsideImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(binding.bottombannerimgView);
        }
    }
    private void updateTopBannerUI(List<TopBannerModel_ResponseModel> bottomBannerList) {
        if (bottomBannerList != null && bottomBannerList.size() > 0) {
            TopBannerModel_ResponseModel topBanner = bottomBannerList.get(0);


            binding.toptext.setText(topBanner.getBannerHeading());
            binding.toptext2.setText(topBanner.getBannerText());


            Glide.with(requireContext())
                    .load(All_URL.imgURL+topBanner.getBannerImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(binding.topbgimage);


        }
    }

    private void stopAutoScroll() {
        isAutoScrolling = false;
    }
}