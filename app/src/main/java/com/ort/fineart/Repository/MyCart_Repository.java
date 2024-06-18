package com.ort.fineart.Repository;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ort.fineart.Api_Handel.RequestStatus_Interface;
import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.Model.Request_Model.MyCart.AddInCart_RequestModel;
import com.ort.fineart.Model.Request_Model.MyCart.ChangeProductQuntity_RequestModel;
import com.ort.fineart.Model.Request_Model.MyCart.MyCartList_RequestModel;
import com.ort.fineart.Model.Request_Model.MyCart.RemoveCartProduct_RequestModel;
import com.ort.fineart.Ui.Activity.Authentication.Authentication_Activity;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.Utils.ShowToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCart_Repository {
    Context context;


    public MyCart_Repository(Context context) {
        this.context = context;
    }


    public void GetCartList_ApiCall(RequestStatus_Interface requestStatus_interface) {
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        String token = sharedPreferenceManager.getUserToken();

        Call<MyCartList_RequestModel> call = RetrofitClient.getInstance().getApiService().getMyCart("Token "+token);
        call.enqueue(new Callback<MyCartList_RequestModel>() {
            @Override
            public void onResponse(Call<MyCartList_RequestModel> call, Response<MyCartList_RequestModel> response) {
                Log.e("getCartList",response.body().getStatus()+"");

                if (response.isSuccessful() && response.body().getStatus() == 200 && response.body().getPayload() != null && response.body().getPayload().size() != 0) {

                    Log.e("getCartList",response.body().getPayload().size()+"");

                    requestStatus_interface.onSuccessResponse(response.body());
                } else {
                    requestStatus_interface.onSuccessResponse(null);
                   // ShowToast.ShowMsgToast(response.body().getMessge(), context);
                    requestStatus_interface.onFailureResponse(response.body().getMessge());
                }
            }

            @Override
            public void onFailure(Call<MyCartList_RequestModel> call, Throwable t) {
                requestStatus_interface.onSuccessResponse(null);
                requestStatus_interface.onFailureResponse(t.getMessage());
                ShowToast.ShowMsgToast(t.getMessage(), context);
            }
        });
    }


    public void ChangeCartProductQuantity(int cart_item_id, int cart_item_quantity, RequestStatus_Interface requestStatus_interface) {

        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        String token = "Token "+sharedPreferenceManager.getUserToken();


        Call<ChangeProductQuntity_RequestModel> call = RetrofitClient.getInstance().getApiService().changeCartProducatQuntity(token, cart_item_id, cart_item_quantity);

        call.enqueue(new Callback<ChangeProductQuntity_RequestModel>() {
            @Override
            public void onResponse(Call<ChangeProductQuntity_RequestModel> call, Response<ChangeProductQuntity_RequestModel> response) {
                Log.e("ChangeCartQuantity",response.body().getStatus()+"");
                if (response.isSuccessful() && response.body().getStatus() == 200 ) {
                    Log.e("ChangeCartQuantity",response.body().getStatus()+"");
                    requestStatus_interface.onSuccessResponse(response.body());
                } else {
                    requestStatus_interface.onSuccessResponse(null);
                    ShowToast.ShowMsgToast(response.body().getMessge(), context);
                    requestStatus_interface.onFailureResponse(response.body().getMessge());
                }
            }

            @Override
            public void onFailure(Call<ChangeProductQuntity_RequestModel> call, Throwable t) {
                Log.e("ChangeCartQuantity",t.getMessage());

                requestStatus_interface.onSuccessResponse(null);
                ShowToast.ShowMsgToast(t.getMessage(), context);
                requestStatus_interface.onFailureResponse(t.getMessage());
            }
        });

    }


    public void RemoveCartProduct(Integer productId,RequestStatus_Interface requestStatus_interface){

        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        String token = "Token "+sharedPreferenceManager.getUserToken();
        Call<RemoveCartProduct_RequestModel> call = RetrofitClient.getInstance().getApiService().removeCartProducat(token, productId);
        call.enqueue(new Callback<RemoveCartProduct_RequestModel>() {
            @Override
            public void onResponse(Call<RemoveCartProduct_RequestModel> call, Response<RemoveCartProduct_RequestModel> response) {
                if (response.isSuccessful() && response.body().getStatus() == 200 ) {

                    requestStatus_interface.onSuccessResponse(response.body());
                } else {
                    requestStatus_interface.onSuccessResponse(response.body());
                    ShowToast.ShowMsgToast(response.body().getMessge(), context);
                    requestStatus_interface.onFailureResponse(response.body().getMessge());
                }
            }

            @Override
            public void onFailure(Call<RemoveCartProduct_RequestModel> call, Throwable t) {
                requestStatus_interface.onSuccessResponse(null);
                ShowToast.ShowMsgToast(t.getMessage(), context);
                requestStatus_interface.onFailureResponse(t.getMessage());
            }
        });




    }



    public void AddInCartList_ApiCall(String Varient_Id,String Quantity,RequestStatus_Interface requestStatus_interface){
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        String token = "Token "+sharedPreferenceManager.getUserToken();
        Log.e("AddInCartList",Varient_Id+","+Quantity);

        Call<AddInCart_RequestModel> call = RetrofitClient.getInstance().getApiService().addInCartList(token,Varient_Id,Quantity);
        call.enqueue(new Callback<AddInCart_RequestModel>() {
            @Override
            public void onResponse(Call<AddInCart_RequestModel> call, Response<AddInCart_RequestModel> response) {

                Log.e("AddInCartList",response.body().getStatus()+":Test");
                if (response.isSuccessful() && response.body().getStatus() == 201 ) {
                    Intent intent=new Intent(context, Authentication_Activity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }else {

                    requestStatus_interface.onSuccessResponse(response.body());
                }


            }

            @Override
            public void onFailure(Call<AddInCart_RequestModel> call, Throwable t) {

                Log.e("AddToCartList",t.getMessage());
                requestStatus_interface.onSuccessResponse(null);
                ShowToast.ShowMsgToast(t.getMessage(), context);
            }
        });







    }







}
