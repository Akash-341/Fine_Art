package com.ort.fineart.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ort.fineart.Model.Response_Model.OrderHistory.Payload;
import com.ort.fineart.Repository.OrderHistoryRepository;

import java.util.List;

public class OrderHistoryViewModel extends ViewModel {
    private OrderHistoryRepository orderHistoryRepository;

    public OrderHistoryViewModel() {
        orderHistoryRepository = new OrderHistoryRepository();
    }

    public LiveData<List<Payload>> getOrderHistory(String token) {
        return orderHistoryRepository.getOrderHistory(token);
    }
}
