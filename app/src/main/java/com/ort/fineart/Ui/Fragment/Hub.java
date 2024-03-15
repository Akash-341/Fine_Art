package com.ort.fineart.Ui.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.ort.fineart.Model.Response_Model.CategoryModel;
import com.ort.fineart.Model.Response_Model.ProductByCategory.Payload;
import com.ort.fineart.Model.Response_Model.Product_ResponseModel;
import com.ort.fineart.Recycler_Adapter.BottomSheetAdapter;
import com.ort.fineart.Recycler_Adapter.DotdAdapter;
import com.ort.fineart.Recycler_Adapter.Hub_CategoryAdapter;
import com.ort.fineart.ViewModel.CategoriesViewModel;
import com.ort.fineart.ViewModel.HubCategoryViewModel;
import com.ort.fineart.databinding.FragmentHubBinding;
import com.ort.fineart.R;

import java.util.Arrays;
import java.util.List;


public class Hub extends Fragment {


    FragmentHubBinding binding;
    private List<Payload> productList;
    private LiveData<List<Payload>> payloadLiveData;
    HubCategoryViewModel hubCategoryViewModel;
    private Hub_CategoryAdapter productAdapter;
    private CategoriesViewModel categoryViewModel;
    private LiveData<List<CategoryModel>> categoryListLiveData;
    String catid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryViewModel = new ViewModelProvider(this).get(CategoriesViewModel.class);
        categoryListLiveData = categoryViewModel.getCategoriesList();
//        hubCategoryViewModel=new ViewModelProvider(this).get(HubCategoryViewModel.class);
//        payloadLiveData=hubCategoryViewModel.getLiveData(catid);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding=FragmentHubBinding.inflate(inflater, container, false);
        categoryViewModel = new ViewModelProvider(this).get(CategoriesViewModel.class);
        categoryListLiveData = categoryViewModel.getCategoriesList();
        hubCategoryViewModel=new ViewModelProvider(this).get(HubCategoryViewModel.class);
//        payloadLiveData=hubCategoryViewModel.getLiveData(catid);
        productAdapter = new Hub_CategoryAdapter(productList,getActivity().getApplicationContext(),requireActivity());


        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(productAdapter);



        return binding.getRoot();

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categoryViewModel = new ViewModelProvider(this).get(CategoriesViewModel.class);
        categoryListLiveData = categoryViewModel.getCategoriesList();
        if (categoryListLiveData != null) {
            categoryListLiveData.observe(getViewLifecycleOwner(), categoriesList -> {
                if (categoriesList != null && !categoriesList.isEmpty()) {
                    // Show Bottom Sheet Dialog when categoriesList is updated
                    showBottomSheetDialog(categoriesList);
                }
            });
        } else {
            // Handle the case when categoryListLiveData is null
        }
        // Show Bottom Sheet Dialog when the Fragment is created
    }
    private void showBottomSheetDialog(List<CategoryModel> categoriesList) {
        if (getActivity() != null) {
            final Dialog bottomSheetDialog = new Dialog(getActivity());
            bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            bottomSheetDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            bottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
            View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_layout, null);

            RecyclerView recyclerView = bottomSheetView.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2)); // 2 columns, you can change it

            BottomSheetAdapter namesAdapter = new BottomSheetAdapter(categoriesList);
            namesAdapter.setOnItemClickListener((v, selectedName,id) -> {
                // Update your TextView with the selected nam
                Log.e( "showBottomSheetDialog: ",id );
                catid=id;
                bottomSheetDialog.dismiss();  // Close the Bottom Sheet Dialog
                hubCategoryViewModel.getLiveData(catid).observe(getViewLifecycleOwner(),productList ->{
                    productAdapter.setProductList(productList);
                    productAdapter.notifyDataSetChanged();
                });
            });

            recyclerView.setAdapter(namesAdapter);

            bottomSheetDialog.setContentView(bottomSheetView);

            bottomSheetDialog.show();
        }
    }

   /* private void toggleWishlist(int position) {
        Product product = productList.get(position);
        product.setWishlist(!product.isWishlist());
        productAdapter.notifyItemChanged(position);
    }*/
}