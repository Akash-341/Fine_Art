package com.ort.fineart.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.Model.Response_Model.CategoryModel;
import com.ort.fineart.Model.Response_Model.Product_ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {


    private MutableLiveData<List<CategoryModel>> categoriesLiveData= new MutableLiveData<>();
    public LiveData<List<CategoryModel>> getCategoriesList() {

        Call<List<CategoryModel>> call = RetrofitClient.getInstance().getApiService().getCategoriesList();
        call.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                if (response.isSuccessful()) {
                    List<CategoryModel> categoriesList = response.body();
                    categoriesLiveData.setValue(categoriesList);
                } else {
                   categoriesLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                // Handle the failure
                categoriesLiveData.setValue(null);
            }
        });

        return categoriesLiveData;
    }
}
