package com.ort.fineart.Repository;

import androidx.lifecycle.MutableLiveData;

import com.ort.fineart.Api_Handel.ApiService;
import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.Model.Response_Model.Product_ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {

    private ApiService productApi;
    private MutableLiveData<List<Product_ResponseModel>> productListLiveData;

    public ProductRepository() {
        productApi = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        productListLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Product_ResponseModel>> getProducts() {
        Call<List<Product_ResponseModel>> call = productApi.getProductList();
        call.enqueue(new Callback<List<Product_ResponseModel>>() {
            @Override
            public void onResponse(Call<List<Product_ResponseModel>> call, Response<List<Product_ResponseModel>> response) {
                if (response.isSuccessful()) {
                    productListLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Product_ResponseModel>> call, Throwable t) {
                // Handle failure
                productListLiveData.setValue(null);
            }
        });
        return productListLiveData;
    }
}
