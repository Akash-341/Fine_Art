package com.ort.fineart.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ort.fineart.Api_Handel.ApiService;
import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.Model.Response_Model.Deal_Of_The_day.DOTD_ResponseModel;
import com.ort.fineart.Model.Response_Model.Deal_Of_The_day.Payload;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DotdRepository {

    private ApiService productApi;
    private MutableLiveData<List<Payload>> productListLiveData= new MutableLiveData<>();

    public DotdRepository() {

    }

    public LiveData<List<Payload>> getProducts() {
        Call<DOTD_ResponseModel> call = RetrofitClient.getInstance().getApiService().getProductList();
        call.enqueue(new Callback<DOTD_ResponseModel>() {
            @Override
            public void onResponse(Call<DOTD_ResponseModel> call, Response<DOTD_ResponseModel> response) {
                Log.e("onResponse: ", response.body().getMessge());
                if (response.isSuccessful() && response.body() != null) {
                    List<Payload> payloadList = response.body().getPayload();
                    productListLiveData.setValue(payloadList);
                }
            }

            @Override
            public void onFailure(Call<DOTD_ResponseModel> call, Throwable t) {
                // Handle failure
                productListLiveData.setValue(null);
                Log.e("onFailure: ", t.getLocalizedMessage());
                Log.e("onFailure: ", t.getCause().getLocalizedMessage());
            }
        });
        return productListLiveData;
    }

}
