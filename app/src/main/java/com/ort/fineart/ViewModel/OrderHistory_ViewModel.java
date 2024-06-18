package com.ort.fineart.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ort.fineart.Api_Handel.RequestStatus_Interface;
import com.ort.fineart.Model.Request_Model.OrderHistory.GetOrderDetails_RequestModel;
import com.ort.fineart.Model.Request_Model.OrderHistory.GetOrderHistoryList_RequestModel;
import com.ort.fineart.Model.Request_Model.WishList.GetWishList_RequestModel;
import com.ort.fineart.Model.Response_Model.OrderHistory.OrderHistory_ResponseModel;
import com.ort.fineart.Model.Response_Model.WishList.GetWishList_ResponseModel;
import com.ort.fineart.Repository.OrderHistory_Repository;
import com.ort.fineart.Repository.WishList_Repository;

import java.util.List;

public class OrderHistory_ViewModel extends AndroidViewModel {
    MutableLiveData<List<OrderHistory_ResponseModel>> OrderHistoryList_LiveData = new MutableLiveData<>();
    MutableLiveData<OrderHistory_ResponseModel> OrderDetails_LiveData = new MutableLiveData<>();


    public OrderHistory_Repository orderHistory_repository;


    public OrderHistory_ViewModel(@NonNull Application application) {
        super(application);
        orderHistory_repository=new OrderHistory_Repository(application);


    }

    /*
    Return
     */

    public LiveData<List<OrderHistory_ResponseModel>> getOrderHistoryList() {
        return OrderHistoryList_LiveData;
    }

    public LiveData<OrderHistory_ResponseModel> getOrderDetails() {
        return OrderDetails_LiveData;
    }




    /*
    Calls
     */


    public void GetOrderHistoryList_Callback() {
        orderHistory_repository.GetOrderHistoryList_ApiCall(new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {

                GetOrderHistoryList_RequestModel model= (GetOrderHistoryList_RequestModel) data;

                if (model != null&&model.getPayload() != null && model.getPayload().size() != 0) {
                  OrderHistoryList_LiveData.postValue(model.getPayload());


                } else {
                    OrderHistoryList_LiveData.postValue(null);
                }

            }

            @Override
            public void onFailureResponse(String message) {
                OrderHistoryList_LiveData.postValue(null);
            }
        });




    }
    public void GetOrderDetails_Callback(String OderId) {
        orderHistory_repository.GetOrderDetails_ApiCall(OderId,new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {

                GetOrderDetails_RequestModel model= (GetOrderDetails_RequestModel) data;
                if (model != null&&model.getPayload() != null ) {
                    OrderDetails_LiveData.postValue(model.getPayload());


                } else {
                    OrderDetails_LiveData.postValue(null);
                }

            }

            @Override
            public void onFailureResponse(String message) {
                OrderDetails_LiveData.postValue(null);
            }
        });




    }
}
