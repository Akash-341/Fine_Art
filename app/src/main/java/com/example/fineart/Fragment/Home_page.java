package com.example.fineart.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.fineart.Adapter.DotdAdapter;
import com.example.fineart.Adapter.TestimonialAdapter;
import com.example.fineart.Model.Product;
import com.example.fineart.Model.TestimonialModel;
import com.example.fineart.R;
import com.example.fineart.ViewModel.DotdViewModel;
import com.example.fineart.databinding.FragmentHomePageBinding;

import java.util.ArrayList;
import java.util.List;


public class Home_page extends Fragment {

    FragmentHomePageBinding binding;
    private List<Product> productList;
    private DotdAdapter productAdapter;
    private TestimonialAdapter testimonialAdapter;
    private DotdViewModel dotdViewModel;
    private List<TestimonialModel> testimonialModelList;

    private int currentVisiblePosition = 0;
    private boolean isAutoScrolling = false;
    private static final long AUTO_SCROLL_DELAY = 3000; // 3 seconds
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dotdViewModel = new ViewModelProvider(this).get(DotdViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentHomePageBinding.inflate(inflater, container, false);
//        flipperImages();
//        productList = initializeProductList();
        testimonialModelList=initializeTestimonialList();
        productAdapter = new DotdAdapter(productList,getActivity().getApplicationContext(),requireActivity());
        testimonialAdapter=new TestimonialAdapter(testimonialModelList);

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
        startAutoScroll();

        dotdViewModel.getProductList().observe(getViewLifecycleOwner(), productList -> {
            productAdapter.setProductList(productList);
            productAdapter.notifyDataSetChanged();
        });
        dotdViewModel.setProductList(initializeProductList());
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
    private List<TestimonialModel> initializeTestimonialList() {

        List<TestimonialModel> productList = new ArrayList<>();
        productList.add(new TestimonialModel("I believe on Fine Art..not only for the product but also owners behavior...","Anonymous", "designer","http://www.thefinearthub.com/uploads/uploads/CustomerTestimonials/Gerberas_488937.png"));
        productList.add(new TestimonialModel("I believe on Fine Art..not only for the product but also owners behavior...","Anonymous", "designer","http://www.thefinearthub.com/uploads/uploads/CustomerTestimonials/4.png"));


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

    private void stopAutoScroll() {
        isAutoScrolling = false;
    }
}