package com.ort.fineart.Repository;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.Model.Response_Model.ProductByCategory.Payload;
import com.ort.fineart.Model.Response_Model.ProductByCategory.Product_By_Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HubCategoryRepository {
    private MutableLiveData<List<Payload>> productListLiveData = new MutableLiveData<>();

    public HubCategoryRepository() {

    }

    public LiveData<List<Payload>> getProducts(String catid) {
        Call<Product_By_Category> call = RetrofitClient.getInstance().getApiService().getCategoryProductList(catid);
        call.enqueue(new Callback<Product_By_Category>() {
            @Override
            public void onResponse(Call<Product_By_Category> call, Response<Product_By_Category> response) {
                Log.e("onResponse: ", response.body().getMessge());
                if (response.isSuccessful() && response.body() != null) {
                    List<Payload> payloadList = response.body().getPayload();
                    productListLiveData.setValue(payloadList);
                }
            }

            @Override
            public void onFailure(Call<Product_By_Category> call, Throwable t) {
                // Handle failure
                productListLiveData.setValue(null);
            }
        });
        return productListLiveData;
    }

}
