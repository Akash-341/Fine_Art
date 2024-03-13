package com.ort.fineart.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ort.fineart.Model.Response_Model.CategoryModel;
import com.ort.fineart.Repository.CategoryRepository;

import java.util.List;

public class CategoriesViewModel extends ViewModel {
    private CategoryRepository categoryRepository;
    private LiveData<List<CategoryModel>> categoriesLiveData;

    public CategoriesViewModel() {
        categoryRepository =new CategoryRepository();
        categoriesLiveData = categoryRepository.getCategoriesList();
    }

    public LiveData<List<CategoryModel>> getCategoriesList() {
        return categoriesLiveData;
    }
}
