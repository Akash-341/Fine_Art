package com.ort.fineart.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.ort.fineart.Model.Response_Model.Best_Seller.Payload;
import com.ort.fineart.Repository.BestSellerRepository;
import com.ort.fineart.Repository.DotdRepository;

import java.util.List;

public class BestSellerViewModel extends ViewModel {
    private LiveData<List<Payload>> bestsellerListLiveData;
    private BestSellerRepository bestSellerRepository;

    public BestSellerViewModel() {
       bestSellerRepository = new BestSellerRepository();
        bestsellerListLiveData = bestSellerRepository.getProducts();
    }

    public LiveData<List<Payload>> getProductList() {
        return bestsellerListLiveData;
    }

}
