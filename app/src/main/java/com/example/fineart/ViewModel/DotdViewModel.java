package com.example.fineart.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fineart.Model.Product;

import java.util.List;

public class DotdViewModel extends ViewModel {
    private MutableLiveData<List<Product>> productListLiveData = new MutableLiveData<>();

    public void setProductList(List<Product> productList) {
        productListLiveData.setValue(productList);
    }

    public LiveData<List<Product>> getProductList() {
        return productListLiveData;
    }
    public void toggleWishlist(int position) {
        List<Product> productList = productListLiveData.getValue();
        if (productList != null && position >= 0 && position < productList.size()) {
            Product product = productList.get(position);
            product.setWishlist(!product.isWishlist());
            productListLiveData.setValue(productList);
        }
    }
}
