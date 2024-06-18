package com.ort.fineart.Repository;

import android.content.Context;
import android.util.Log;

import com.ort.fineart.Api_Handel.RequestStatus_Interface;
import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.Model.Request_Model.MyCart.MyCartList_RequestModel;
import com.ort.fineart.Model.Request_Model.OrderHistory.GetOrderDetails_RequestModel;
import com.ort.fineart.Model.Request_Model.OrderHistory.GetOrderHistoryList_RequestModel;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.Utils.ShowToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistory_Repository {

    Context context;


    public OrderHistory_Repository(Context context) {
        this.context = context;
    }

    public void GetOrderHistoryList_ApiCall(RequestStatus_Interface requestStatus_interface) {
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        String token = sharedPreferenceManager.getUserToken();

        Call<GetOrderHistoryList_RequestModel> call = RetrofitClient.getInstance().getApiService().getOrderHistoryList("Token "+token);
        call.enqueue(new Callback<GetOrderHistoryList_RequestModel>() {
            @Override
            public void onResponse(Call<GetOrderHistoryList_RequestModel> call, Response<GetOrderHistoryList_RequestModel> response) {
                if (response.isSuccessful() && response.body().getStatus() == 200 && response.body().getPayload() != null && response.body().getPayload().size() != 0) {

                    requestStatus_interface.onSuccessResponse(response.body());
                } else {
                    requestStatus_interface.onSuccessResponse(null);
                    requestStatus_interface.onFailureResponse(response.body().getMessge());
                }
            }

            @Override
            public void onFailure(Call<GetOrderHistoryList_RequestModel> call, Throwable t) {
                requestStatus_interface.onFailureResponse(t.getMessage());
                ShowToast.ShowMsgToast(t.getMessage(), context);
                requestStatus_interface.onSuccessResponse(null);
            }
        });




    }

    public void GetOrderDetails_ApiCall(String orderId,RequestStatus_Interface requestStatus_interface) {
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        String token = sharedPreferenceManager.getUserToken();

        Call<GetOrderDetails_RequestModel> call = RetrofitClient.getInstance().getApiService().getOrderDetails("Token "+token,orderId);
       call.enqueue(new Callback<GetOrderDetails_RequestModel>() {
           @Override
           public void onResponse(Call<GetOrderDetails_RequestModel> call, Response<GetOrderDetails_RequestModel> response) {
               if (response.isSuccessful() && response.body().getStatus() == 200 && response.body().getPayload() != null ) {

                   requestStatus_interface.onSuccessResponse(response.body());
               } else {
                   requestStatus_interface.onSuccessResponse(null);
                   requestStatus_interface.onFailureResponse(response.body().getMessge());
               }
           }

           @Override
           public void onFailure(Call<GetOrderDetails_RequestModel> call, Throwable t) {
               requestStatus_interface.onFailureResponse(t.getMessage());
               ShowToast.ShowMsgToast(t.getMessage(), context);
               requestStatus_interface.onSuccessResponse(null);
           }
       });

    }


}
