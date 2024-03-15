package com.ort.fineart.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ort.fineart.Model.Response_Model.ProductByCategory.Payload;
import com.ort.fineart.Repository.HubCategoryRepository;

import java.util.List;

public class HubCategoryViewModel extends ViewModel {

    private HubCategoryRepository hubCategoryRepository;

    public HubCategoryViewModel(){
        hubCategoryRepository=new HubCategoryRepository();

    }

    public LiveData<List<Payload>> getLiveData(String id) {
        return hubCategoryRepository.getProducts(id);
    }
}
