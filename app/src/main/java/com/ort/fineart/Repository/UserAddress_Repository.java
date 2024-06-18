package com.ort.fineart.Repository;

import android.content.Context;
import android.util.Log;

import com.ort.fineart.Api_Handel.RequestStatus_Interface;
import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.Model.Request_Model.MyCart.MyCartList_RequestModel;
import com.ort.fineart.Model.Request_Model.UserAddress.AddAddress_RequestModel;
import com.ort.fineart.Model.Request_Model.UserAddress.DeleteAddress_RequestModel;
import com.ort.fineart.Model.Request_Model.UserAddress.GetAddressById_RequestModel;
import com.ort.fineart.Model.Request_Model.UserAddress.GetUserAddress_RequestModel;
import com.ort.fineart.Model.Request_Model.UserAddress.UpdateAddress_RequestModel;
import com.ort.fineart.Model.Response_Model.UserAddress.GetAddressById_ResponseModel;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.Utils.ShowToast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAddress_Repository {
    Context context;

    public UserAddress_Repository(Context context) {
        this.context = context;
    }

    public void GetAddress_ApiCall(RequestStatus_Interface requestStatus_interface){

        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        String token = sharedPreferenceManager.getUserToken();

        Call<GetUserAddress_RequestModel> call = RetrofitClient.getInstance().getApiService().getUserAddressList("Token "+token);
       call.enqueue(new Callback<GetUserAddress_RequestModel>() {
           @Override
           public void onResponse(Call<GetUserAddress_RequestModel> call, Response<GetUserAddress_RequestModel> response) {
              Log.e("AddressList",response.message());
               if (response.isSuccessful() && response.body().getStatus() == 200 && response.body().getPayload() != null && response.body().getPayload().size() != 0) {


                   requestStatus_interface.onSuccessResponse(response.body());
               } else {
                   requestStatus_interface.onSuccessResponse(null);
                  // ShowToast.ShowMsgToast(response.body().getMessge(), context);
               }




           }

           @Override
           public void onFailure(Call<GetUserAddress_RequestModel> call, Throwable t) {
               requestStatus_interface.onSuccessResponse(null);
           }
       });


    }

    public void AddAddress_ApiCall(String AddressLine_1,String AddressLine_2,String Country,String State,String City,String Pincode ,RequestStatus_Interface requestStatus_interface){

        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        String token = sharedPreferenceManager.getUserToken();






        HashMap <String,String>parms=new HashMap<>();
        parms.put("addressline1",AddressLine_1);
        parms.put("addressline2",AddressLine_2);
        parms.put("country",Country);
        parms.put("state",City);
        parms.put("city",State);
        parms.put("pincode",Pincode);






        Call<AddAddress_RequestModel> call = RetrofitClient.getInstance().getApiService().addAddress("Token "+token,parms);
        call.enqueue(new Callback<AddAddress_RequestModel>() {
            @Override
            public void onResponse(Call<AddAddress_RequestModel> call, Response<AddAddress_RequestModel> response) {
                if (response.isSuccessful() && response.body().getStatus() == 200 && response.body().getPayload() != null ) {

                    requestStatus_interface.onSuccessResponse(response.body());
                } else {
                    requestStatus_interface.onSuccessResponse(null);
                    ShowToast.ShowMsgToast(response.body().getMessge(), context);
                }
            }

            @Override
            public void onFailure(Call<AddAddress_RequestModel> call, Throwable t) {
                requestStatus_interface.onSuccessResponse(null);
            }
        });






    }

    public void UpdateAddress_ApiCall(Integer Address_Id,String AddressLine_1,String AddressLine_2,String Country,String State,String City,String Pincode ,RequestStatus_Interface requestStatus_interface){
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        String token = sharedPreferenceManager.getUserToken();






        HashMap <String,String>parms=new HashMap<>();
        parms.put("addressline1",AddressLine_1);
        parms.put("addressline2",AddressLine_2);
        parms.put("country",Country);
        parms.put("state",City);
        parms.put("city",State);
        parms.put("pincode",Pincode);
        Call<UpdateAddress_RequestModel> call = RetrofitClient.getInstance().getApiService().updateAddress("Token "+token,Address_Id,parms);
        call.enqueue(new Callback<UpdateAddress_RequestModel>() {
            @Override
            public void onResponse(Call<UpdateAddress_RequestModel> call, Response<UpdateAddress_RequestModel> response) {
                if (response.isSuccessful() && response.body().getStatus() == 200 && response.body().getPayload() != null ) {

                    requestStatus_interface.onSuccessResponse(response.body());
                } else {
                    requestStatus_interface.onSuccessResponse(null);
                    ShowToast.ShowMsgToast(response.body().getMessge(), context);
                }
            }

            @Override
            public void onFailure(Call<UpdateAddress_RequestModel> call, Throwable t) {
                requestStatus_interface.onSuccessResponse(null);
            }
        });





    }

    public void GetAddressById_ApiCall(Integer AddressId,RequestStatus_Interface requestStatus_interface){

        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        String token = sharedPreferenceManager.getUserToken();
        Call<GetAddressById_RequestModel> call = RetrofitClient.getInstance().getApiService().getAddressById("Token "+token,AddressId);
        call.enqueue(new Callback<GetAddressById_RequestModel>() {
            @Override
            public void onResponse(Call<GetAddressById_RequestModel> call, Response<GetAddressById_RequestModel> response) {

                if (response.isSuccessful() && response.body().getStatus() == 200 && response.body().getPayload() != null ) {
                    requestStatus_interface.onSuccessResponse(response.body());
                } else {
                    requestStatus_interface.onSuccessResponse(null);
                    ShowToast.ShowMsgToast(response.body().getMessge(), context);
                }
            }

            @Override
            public void onFailure(Call<GetAddressById_RequestModel> call, Throwable t) {
                requestStatus_interface.onSuccessResponse(null);
            }
        });


    }


    public void DeleteAddress_ApiCall(int AddressId,RequestStatus_Interface requestStatus_interface){

        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        String token = sharedPreferenceManager.getUserToken();
        Call<DeleteAddress_RequestModel> call = RetrofitClient.getInstance().getApiService().deleteAddress("Token "+token,AddressId);
        call.enqueue(new Callback<DeleteAddress_RequestModel>() {
            @Override
            public void onResponse(Call<DeleteAddress_RequestModel> call, Response<DeleteAddress_RequestModel> response) {
                if (response.isSuccessful()&&response.body().getStatus()==200){
                    requestStatus_interface.onSuccessResponse(response.body());
                }else {
                    requestStatus_interface.onSuccessResponse(null);
                    ShowToast.ShowMsgToast(response.body().getMessge(), context);
                }
            }

            @Override
            public void onFailure(Call<DeleteAddress_RequestModel> call, Throwable t) {
                requestStatus_interface.onSuccessResponse(null);
            }
        });




    }





}
