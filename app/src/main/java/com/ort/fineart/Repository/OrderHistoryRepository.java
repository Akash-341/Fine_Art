package com.ort.fineart.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.Model.Response_Model.Best_Seller.Best_Seller_Model;
import com.ort.fineart.Model.Response_Model.OrderHistory.OrderHistoryModel;
import com.ort.fineart.Model.Response_Model.OrderHistory.Payload;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistoryRepository {
    private MutableLiveData<List<Payload>> orderHistoryLiveData = new MutableLiveData<>();

    public OrderHistoryRepository(){

    }
    public LiveData<List<Payload>> getOrderHistory(String token) {
        Call<OrderHistoryModel> call = RetrofitClient.getInstance().getApiService().getOrderList(token);
        call.enqueue(new Callback<OrderHistoryModel>() {
            @Override
            public void onResponse(Call<OrderHistoryModel> call, Response<OrderHistoryModel> response) {
                if (response.isSuccessful()) {
                    OrderHistoryModel orderHistoryModel = response.body();
                    if (orderHistoryModel != null) {
                        List<Payload> payloadList = orderHistoryModel.getPayload();
                        orderHistoryLiveData.setValue(payloadList);
                    }
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<OrderHistoryModel> call, Throwable t) {
                // Handle failure
                orderHistoryLiveData.setValue(null);
                Log.e("onFailure: ", t.getLocalizedMessage());
                Log.e("onFailure: ", t.getCause().getLocalizedMessage());
            }
        });
        return orderHistoryLiveData;
    }
}
