package com.ort.fineart.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ort.fineart.Model.Response_Model.PersonalDetailsData;
import com.ort.fineart.Model.Response_Model.ProductDetailsData.Product_Details_Model;
import com.ort.fineart.Repository.PersonalDetailRepository;
import com.ort.fineart.Repository.ProductDetailRepository;
import com.ort.fineart.Utils.SharedPreferenceManager;

public class ProductDetailVIewModel extends ViewModel {
    private ProductDetailRepository productDetailRepository;
    private LiveData<Product_Details_Model> productdetailLiveData;

    public ProductDetailVIewModel(SharedPreferenceManager sharedPreferenceManager){
        productDetailRepository=new ProductDetailRepository();
        String email=sharedPreferenceManager.getUserEmail();
        productdetailLiveData=productDetailRepository.getProductDetailLiveData(email);
    }

    public LiveData<Product_Details_Model> getProductLiveData() {
        return productdetailLiveData;
    }
}
