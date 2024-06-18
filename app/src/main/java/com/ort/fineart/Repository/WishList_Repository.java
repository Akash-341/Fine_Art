package com.ort.fineart.Repository;

import android.content.Context;
import android.util.Log;

import com.ort.fineart.Api_Handel.RequestStatus_Interface;
import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.Model.Request_Model.MyCart.MyCartList_RequestModel;
import com.ort.fineart.Model.Request_Model.WishList.AddRemove_WishListProduct_RequestModel;
import com.ort.fineart.Model.Request_Model.WishList.GetWishList_RequestModel;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.Utils.ShowToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishList_Repository {
    Context context;








    public WishList_Repository(Context context) {
        this.context = context;
    }

    public void GetWishList_ApiCall(RequestStatus_Interface requestStatus_interface) {
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        String token = sharedPreferenceManager.getUserToken();

        Call<GetWishList_RequestModel> call = RetrofitClient.getInstance().getApiService().getWishList("Token "+token);
       call.enqueue(new Callback<GetWishList_RequestModel>() {
           @Override
           public void onResponse(Call<GetWishList_RequestModel> call, Response<GetWishList_RequestModel> response) {

               if (response.isSuccessful()&&response.body().getStatus()==200&&response.body().getPayload()!=null&&response.body().getPayload().size()!=0){

                   requestStatus_interface.onSuccessResponse(response.body());

               }else {

                   requestStatus_interface.onSuccessResponse(null);
                  // ShowToast.ShowMsgToast(response.body().getMessge(),context);
                   requestStatus_interface.onFailureResponse(response.body().getMessge());
               }
           }







           @Override
           public void onFailure(Call<GetWishList_RequestModel> call, Throwable t) {

               requestStatus_interface.onSuccessResponse(null);
               requestStatus_interface.onFailureResponse(t.getMessage());
               ShowToast.ShowMsgToast(t.getMessage(),context);
           }
       });




    }

    public void AddRemoveWishListProduct_ApiCall(int varientId,RequestStatus_Interface requestStatus_interface){
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        String token = sharedPreferenceManager.getUserToken();

        Call<AddRemove_WishListProduct_RequestModel> call = RetrofitClient.getInstance().getApiService().addRemoveWishListProduct("Token "+token ,varientId);
        call.enqueue(new Callback<AddRemove_WishListProduct_RequestModel>() {
            @Override
            public void onResponse(Call<AddRemove_WishListProduct_RequestModel> call, Response<AddRemove_WishListProduct_RequestModel> response) {

                if (response.isSuccessful()&&response.body().getStatus()==200){

                    requestStatus_interface.onSuccessResponse(response.body());
                }else {

                    ShowToast.ShowMsgToast(response.body().getMessge(),context);
                    requestStatus_interface.onSuccessResponse(null);

                    requestStatus_interface.onFailureResponse(response.body().getMessge());
                }
            }

            @Override
            public void onFailure(Call<AddRemove_WishListProduct_RequestModel> call, Throwable t) {

                requestStatus_interface.onSuccessResponse(null);
                ShowToast.ShowMsgToast(t.getMessage(),context);
                requestStatus_interface.onFailureResponse(t.getMessage());
            }
        });




    }









}
