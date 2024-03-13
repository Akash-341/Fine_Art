package com.ort.fineart.Ui.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ort.fineart.Model.Response_Model.Deal_Of_The_day.Payload;
import com.ort.fineart.Model.Response_Model.Product_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.Recycler_Adapter.DotdAdapter;
import com.ort.fineart.ViewModel.DotdViewModel;
import com.ort.fineart.databinding.FragmentDotdViewAllBinding;

import java.util.List;


public class Dotd_ViewAll extends Fragment {
    FragmentDotdViewAllBinding binding;
    private List<Payload> productList;
    private DotdAdapter productAdapter;
    private DotdViewModel dotdViewModel;
    private LiveData<List<Payload>> productLiveData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dotdViewModel = new ViewModelProvider(this).get(DotdViewModel.class);
        productLiveData = dotdViewModel.getProductList();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentDotdViewAllBinding.inflate(inflater, container, false);
        productAdapter = new DotdAdapter(productList, getActivity().getApplicationContext(), requireActivity());
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(productAdapter);

        dotdViewModel.getProductList().observe(getViewLifecycleOwner(), productList -> {
            productAdapter.setProductList(productList);
            productAdapter.notifyDataSetChanged();
        });

        return binding.getRoot();
    }
}