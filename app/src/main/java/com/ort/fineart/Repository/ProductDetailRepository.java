package com.ort.fineart.Repository;


import androidx.lifecycle.MutableLiveData;


import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.Model.Response_Model.ProductDetailsData.Product_Details_Model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailRepository {
    private MutableLiveData<Product_Details_Model> productDetailLiveData;

    public ProductDetailRepository(){
        productDetailLiveData=new MutableLiveData<>();
    }

    public MutableLiveData<Product_Details_Model> getProductDetailLiveData(String varientid) {
        Call<Product_Details_Model> call= RetrofitClient.getInstance().getApiService().getProductData(varientid);
        call.enqueue(new Callback<Product_Details_Model>() {
            @Override
            public void onResponse(Call<Product_Details_Model> call, Response<Product_Details_Model> response) {
                if (response.isSuccessful()){
                    Product_Details_Model product_details_model=response.body();
                    productDetailLiveData.setValue(product_details_model);
                }
            }

            @Override
            public void onFailure(Call<Product_Details_Model> call, Throwable t) {
                productDetailLiveData.setValue(null);

            }
        });

        return productDetailLiveData;
    }
}
