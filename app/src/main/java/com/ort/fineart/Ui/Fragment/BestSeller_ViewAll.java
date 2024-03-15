package com.ort.fineart.Ui.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ort.fineart.Model.Response_Model.Best_Seller.Payload;
import com.ort.fineart.R;
import com.ort.fineart.Recycler_Adapter.BestSellerAdapter;
import com.ort.fineart.ViewModel.BestSellerViewModel;
import com.ort.fineart.databinding.FragmentBestSellerViewAllBinding;

import java.util.List;


public class BestSeller_ViewAll extends Fragment {

    FragmentBestSellerViewAllBinding binding;
    private List<Payload> payloadList;
    BestSellerAdapter adapter;
    BestSellerViewModel viewModel;
    private LiveData<List<Payload>> listLiveData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel=new ViewModelProvider(this).get(BestSellerViewModel.class);
        listLiveData=viewModel.getProductList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentBestSellerViewAllBinding.inflate(inflater,container,false);

        adapter=new BestSellerAdapter(payloadList,getContext(),requireActivity());
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(adapter);

        viewModel.getProductList().observe(getViewLifecycleOwner(), productList->{
            adapter.setProductList(productList);
            adapter.notifyDataSetChanged();
        });

        return binding.getRoot();
    }
}