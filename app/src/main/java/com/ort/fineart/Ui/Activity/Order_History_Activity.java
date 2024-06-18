package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ort.fineart.Model.Response_Model.OrderHistory.OrderHistory_ResponseModel;
import com.ort.fineart.R;
import com.ort.fineart.Recycler_Adapter.AddressList_RecyclerviewAdapter;
import com.ort.fineart.Recycler_Adapter.OrderHistoryList_RecyclerAdapter;
import com.ort.fineart.Utils.DpToPixcel;
import com.ort.fineart.Utils.VerticalSpaceItemDecoration;
import com.ort.fineart.ViewModel.MyCart_ViewModel;
import com.ort.fineart.ViewModel.OrderHistory_ViewModel;
import com.ort.fineart.databinding.ActivityOrderHistoryBinding;

import java.util.List;

public class Order_History_Activity extends AppCompatActivity {
    ActivityOrderHistoryBinding binding;
    OrderHistory_ViewModel orderHistory_viewModel;
    OrderHistoryList_RecyclerAdapter orderHistoryList_recyclerAdapter;
    int RecyclerViewCardMargin=16;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOrderHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int spaceBetweenItemsPx = DpToPixcel.dpToPx(getApplicationContext(), RecyclerViewCardMargin);

        ViewInit();
        GetOrderHistoryList();
        SetOrderHistoryList();


        orderHistoryList_recyclerAdapter = new OrderHistoryList_RecyclerAdapter(getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.orderhistoryRecyclerview.setLayoutManager(layoutManager);
        binding.orderhistoryRecyclerview.setAdapter(orderHistoryList_recyclerAdapter);
        binding.orderhistoryRecyclerview.addItemDecoration(new VerticalSpaceItemDecoration(getApplicationContext(), spaceBetweenItemsPx));


        binding.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void ViewInit() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        orderHistory_viewModel = viewModelProvider.get(OrderHistory_ViewModel.class);

    }

    public void GetOrderHistoryList(){
        orderHistory_viewModel.GetOrderHistoryList_Callback();
        binding.loadingLottie.setVisibility(View.VISIBLE);
    }
    public void SetOrderHistoryList(){
        orderHistory_viewModel.getOrderHistoryList().observe(Order_History_Activity.this, new Observer<List<OrderHistory_ResponseModel>>() {
            @Override
            public void onChanged(List<OrderHistory_ResponseModel> orderHistory_responseModels) {
                if (orderHistory_responseModels!=null&&orderHistory_responseModels.size()!=0){
                    binding.loadingLottie.setVisibility(View.GONE);
                    binding.notFoundImg.setVisibility(View.GONE);
                    binding.orderhistoryRecyclerview.setVisibility(View.VISIBLE);

                    orderHistoryList_recyclerAdapter.setDataList(orderHistory_responseModels);
                }else {
                    binding.loadingLottie.setVisibility(View.GONE);
                    binding.notFoundImg.setVisibility(View.VISIBLE);
                    binding.orderhistoryRecyclerview.setVisibility(View.GONE);
                    orderHistoryList_recyclerAdapter.setDataList(null);
                }

            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}