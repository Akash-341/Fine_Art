package com.ort.fineart.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ort.fineart.Model.Response_Model.Product_ResponseModel;
import com.ort.fineart.Repository.ProductRepository;

import java.util.List;

public class DotdViewModel extends ViewModel {
    private LiveData<List<Product_ResponseModel>> productListLiveData;
    private ProductRepository productRepository;

    public DotdViewModel() {
        productRepository = new ProductRepository();
        productListLiveData = productRepository.getProducts();
    }

    public LiveData<List<Product_ResponseModel>> getProductList() {
        return productListLiveData;
    }

}
