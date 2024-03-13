package com.ort.fineart.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ort.fineart.Api_Handel.ApiService;
import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.Model.Response_Model.Best_Seller.Best_Seller_Model;
import com.ort.fineart.Model.Response_Model.Best_Seller.Payload;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BestSellerRepository {


    private ApiService productApi;
    private MutableLiveData<List<Payload>> productListLiveData = new MutableLiveData<>();

    public BestSellerRepository() {

    }

    public LiveData<List<Payload>> getProducts() {
        Call<Best_Seller_Model> call = RetrofitClient.getInstance().getApiService().getbestSellerList();
        call.enqueue(new Callback<Best_Seller_Model>() {
            @Override
            public void onResponse(Call<Best_Seller_Model> call, Response<Best_Seller_Model> response) {
                Log.e("onResponse: ", response.body().getMessge());
                if (response.isSuccessful() && response.body() != null) {
                    List<Payload> payloadList = response.body().getPayload();
                    productListLiveData.setValue(payloadList);
                }
            }

            @Override
            public void onFailure(Call<Best_Seller_Model> call, Throwable t) {
                // Handle failure
                productListLiveData.setValue(null);
                Log.e("onFailure: ", t.getLocalizedMessage());
                Log.e("onFailure: ", t.getCause().getLocalizedMessage());
            }
        });
        return productListLiveData;
    }

}
