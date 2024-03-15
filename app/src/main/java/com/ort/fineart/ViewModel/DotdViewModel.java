package com.ort.fineart.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ort.fineart.Model.Response_Model.Deal_Of_The_day.Payload;
import com.ort.fineart.Repository.DotdRepository;

import java.util.List;

public class DotdViewModel extends ViewModel {
    private LiveData<List<Payload>> productListLiveData;
    private DotdRepository productRepository;

    public DotdViewModel() {
        productRepository = new DotdRepository();
        productListLiveData = productRepository.getProducts();
    }

    public LiveData<List<Payload>> getProductList() {
        return productListLiveData;
    }

}
