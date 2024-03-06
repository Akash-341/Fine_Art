package com.example.fineart.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class DotdViewModel extends ViewModel {
    private MutableLiveData<List<Product>> productListLiveData = new MutableLiveData<>();

    public void setProductList(List<Product> productList) {
        productListLiveData.setValue(productList);
    }

    public LiveData<List<Product>> getProductList() {
        return productListLiveData;
    }

}
