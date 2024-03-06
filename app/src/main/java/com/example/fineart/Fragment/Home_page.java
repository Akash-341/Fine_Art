package com.example.fineart.Fragment;

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
import com.example.fineart.Adapter.DotdAdapter;
import com.example.fineart.Adapter.TestimonialAdapter;
import com.example.fineart.All_URL;
import com.example.fineart.Interface.ApiService;
import com.example.fineart.Interface.BottomBannerApiService;
import com.example.fineart.Interface.TopBannerApiService;
import com.example.fineart.Model.BottomBannerData;
import com.example.fineart.Model.Product;
import com.example.fineart.Model.TestimonialModel;
import com.example.fineart.Model.TopBannerModel;
import com.example.fineart.R;
import com.example.fineart.RetrofitClient;
import com.example.fineart.ViewModel.DotdViewModel;
import com.example.fineart.ViewModel.TestimonialViewModel;
import com.example.fineart.databinding.FragmentHomePageBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Home_page extends Fragment {

    FragmentHomePageBinding binding;
    private List<Product> productList;
    private DotdAdapter productAdapter;
    private TestimonialAdapter testimonialAdapter;
    private DotdViewModel dotdViewModel;
    private List<TestimonialModel> testimonialModelList;
    private TestimonialViewModel testimonialViewModel;
    private LiveData<List<TestimonialModel>> testimonialListLiveData;

    private int currentVisiblePosition = 0;
    private boolean isAutoScrolling = false;
    private static final long AUTO_SCROLL_DELAY = 3000; // 3 seconds

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dotdViewModel = new ViewModelProvider(this).get(DotdViewModel.class);
        testimonialViewModel = new ViewModelProvider(this).get(TestimonialViewModel.class);
        testimonialListLiveData = testimonialViewModel.getTestimonialListLiveData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater, container, false);
//        flipperImages();
//        productList = initializeProductList();

        testimonialAdapter = new TestimonialAdapter(testimonialModelList);
        productAdapter = new DotdAdapter(productList, getActivity().getApplicationContext(), requireActivity());
        testimonialAdapter = new TestimonialAdapter(testimonialModelList);

        productAdapter.setOnWishlistClickListener(new DotdAdapter.OnWishlistClickListener() {
            @Override
            public void onWishlistClick(int position) {
                toggleWishlist(position);
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

        testimonialListLiveData.observe(getViewLifecycleOwner(), new Observer<List<TestimonialModel>>() {
            @Override
            public void onChanged(List<TestimonialModel> testimonialModels) {
                // Update the UI with the new data
                testimonialAdapter.setTestimonialList(testimonialModels);
                testimonialAdapter.notifyDataSetChanged();
            }
        });
        startAutoScroll();

        dotdViewModel.getProductList().observe(getViewLifecycleOwner(), productList -> {
            productAdapter.setProductList(productList);
            productAdapter.notifyDataSetChanged();
        });
        dotdViewModel.setProductList(initializeProductList());

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

        dotdViewModel.toggleWishlist(position);
    }

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
        BottomBannerApiService apiService = RetrofitClient.getRetrofitInstance().create(BottomBannerApiService.class);
        Call<List<BottomBannerData>> call = apiService.getBottomBaner();

        call.enqueue(new Callback<List<BottomBannerData>>() {
            @Override
            public void onResponse(Call<List<BottomBannerData>> call, Response<List<BottomBannerData>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Update UI with bottom banner data
                    List<BottomBannerData> bottomBannerList = response.body();
                    updateBottomBannerUI(bottomBannerList);
                }
            }

            @Override
            public void onFailure(Call<List<BottomBannerData>> call, Throwable t) {
                // Handle API call failure
            }
        });
    }
    private void fetchTopBannerData() {
        // Use Retrofit to make API call and update UI
        TopBannerApiService apiService = RetrofitClient.getRetrofitInstance().create(TopBannerApiService.class);
        Call<List<TopBannerModel>> call = apiService.getTopBanner();

        call.enqueue(new Callback<List<TopBannerModel>>() {
            @Override
            public void onResponse(Call<List<TopBannerModel>> call, Response<List<TopBannerModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Update UI with bottom banner data
                    List<TopBannerModel> topBannerList = response.body();
                    updateTopBannerUI(topBannerList);
                }
            }

            @Override
            public void onFailure(Call<List<TopBannerModel>> call, Throwable t) {
                // Handle API call failure
            }
        });
    }
    private void updateBottomBannerUI(List<BottomBannerData> bottomBannerList) {
        if (bottomBannerList != null && bottomBannerList.size() > 0) {
            BottomBannerData bottomBanner = bottomBannerList.get(0);


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
    private void updateTopBannerUI(List<TopBannerModel> bottomBannerList) {
        if (bottomBannerList != null && bottomBannerList.size() > 0) {
            TopBannerModel topBanner = bottomBannerList.get(0);


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