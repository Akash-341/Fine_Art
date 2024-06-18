package com.ort.fineart.Repository;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ort.fineart.Api_Handel.RequestStatus_Interface;
import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.Model.Request_Model.Authentication.Login_RequestModel;
import com.ort.fineart.Model.Request_Model.Checkout.ApplyCoupon_RequestModel;
import com.ort.fineart.Model.Request_Model.Checkout.Checkout_RequestModel;
import com.ort.fineart.Model.Request_Model.Checkout.CouponList_RequestModel;
import com.ort.fineart.Model.Request_Model.Checkout.PlaceOrder_RequestModel;
import com.ort.fineart.Ui.Activity.Authentication.Authentication_Activity;
import com.ort.fineart.Ui.Activity.Home_Activity;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.Utils.ShowDialog;
import com.ort.fineart.Utils.ShowToast;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Checkout_Repository {

    Context context;

    public Checkout_Repository(Context context) {
        this.context = context;
    }


    public void GetCheckoutOrderDetails_ApiCall(RequestStatus_Interface requestStatus_interface){
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        String token = sharedPreferenceManager.getUserToken();


        Call<Checkout_RequestModel> call = RetrofitClient.getInstance().getApiService().getCheckoutDetails("Token "+token);
        call.enqueue(new Callback<Checkout_RequestModel>() {
            @Override
            public void onResponse(Call<Checkout_RequestModel> call, Response<Checkout_RequestModel> response) {
                if (response.isSuccessful()&&response.body().getStatus()==200&&response.body().getPayload()!=null){

                    requestStatus_interface.onSuccessResponse(response.body().getPayload());

                }else {

                    requestStatus_interface.onSuccessResponse(null);
                    // ShowToast.ShowMsgToast(response.body().getMessge(),context);
                    requestStatus_interface.onFailureResponse(response.body().getMessge());
                }
            }

            @Override
            public void onFailure(Call<Checkout_RequestModel> call, Throwable t) {
                requestStatus_interface.onSuccessResponse(null);
            }
        });

    }


    public void QuickBuyOrderDetails_ApiCall(String productId,String color,String size,String quantity,RequestStatus_Interface requestStatus_interface){
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        String token = sharedPreferenceManager.getUserToken();


        HashMap<String,String>parms=new HashMap<>();
        parms.put("product_id",productId);
        parms.put("color",color);
        parms.put("size",size);
        parms.put("quantity",quantity);



        Call<Checkout_RequestModel> call = RetrofitClient.getInstance().getApiService().quickBuyCheckoutDetails("Token "+token,parms);
        call.enqueue(new Callback<Checkout_RequestModel>() {
            @Override
            public void onResponse(Call<Checkout_RequestModel> call, Response<Checkout_RequestModel> response) {
                if (response.isSuccessful()&&response.body().getStatus()==200&&response.body().getPayload()!=null){

                    requestStatus_interface.onSuccessResponse(response.body().getPayload());

                }else {

                    requestStatus_interface.onSuccessResponse(null);
                    // ShowToast.ShowMsgToast(response.body().getMessge(),context);
                    requestStatus_interface.onFailureResponse(response.body().getMessge());
                }
            }

            @Override
            public void onFailure(Call<Checkout_RequestModel> call, Throwable t) {
                requestStatus_interface.onSuccessResponse(null);
            }
        });

    }






    public void PlaceOrder_ApiCall(String Order_Products,String Amount,String CouponId,String ShoppingAddress,String TotalPrice,String CouponDiscount,String TotalDiscount,String GiftCharges,String DeliveryCharges,String PaymentMode,RequestStatus_Interface requestStatus_interface){
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        String token = sharedPreferenceManager.getUserToken();
        String firstName=sharedPreferenceManager.getUserFirstName();
        String lastName=sharedPreferenceManager.getUserLastName();
        String email=sharedPreferenceManager.getUserEmail();
        String phoneNo=sharedPreferenceManager.getUserPhone();

        HashMap<String,String>params=new HashMap<>();
        params.put("order_products",Order_Products);
        params.put("amount",Amount);
        params.put("CouponId",CouponId);
        params.put("FirstName",firstName);
        params.put("LastName",lastName);
        params.put("EmailAdderss",email);
        params.put("PhoneNumber",phoneNo);
        params.put("ShippingAddress",ShoppingAddress);
        params.put("TotalPrice",TotalPrice);
        params.put("CouponDiscount",CouponDiscount);
        params.put("TotalDiscount",TotalDiscount);
       // params.put("GiftCharges",GiftCharges);
        params.put("DeliveryCharges",DeliveryCharges);
        params.put("PaymentMode",PaymentMode);

    //    Log.e("PlaceOrderDetails",params.toString());


        Call<PlaceOrder_RequestModel> call = RetrofitClient.getInstance().getApiService().placeOrder("Token "+token,params);
        call.enqueue(new Callback<PlaceOrder_RequestModel>() {
            @Override
            public void onResponse(Call<PlaceOrder_RequestModel> call, Response<PlaceOrder_RequestModel> response) {

                Log.e("PlaceOrderApi",response.body().getMessge()+","+response.body().getStatus());
                if (response.isSuccessful()&&response.body().getStatus()==200&&response.body().getPayload()!=null){

                    requestStatus_interface.onSuccessResponse(response.body());

                } else if (response.isSuccessful()&&response.body().getStatus()==201) {
                    Intent intent=new Intent(context, Authentication_Activity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                } else {

                    requestStatus_interface.onSuccessResponse(null);
                    // ShowToast.ShowMsgToast(response.body().getMessge(),context);
                    requestStatus_interface.onFailureResponse(response.body().getMessge());
                }
            }

            @Override
            public void onFailure(Call<PlaceOrder_RequestModel> call, Throwable t) {
                Log.e("PlaceOrderApi",t.getMessage());
                requestStatus_interface.onSuccessResponse(null);
            }
        });
















    }


    public void GetCouponsList_ApiCall(RequestStatus_Interface requestStatus_interface){
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        String token = sharedPreferenceManager.getUserToken();


        Call<CouponList_RequestModel> call = RetrofitClient.getInstance().getApiService().getCouponList("Token "+token);

        call.enqueue(new Callback<CouponList_RequestModel>() {
            @Override
            public void onResponse(Call<CouponList_RequestModel> call, Response<CouponList_RequestModel> response) {
                if (response.isSuccessful()&&response.body().getStatus()==200&&response.body().getPayload()!=null&&response.body().getPayload().size()!=0){

                    requestStatus_interface.onSuccessResponse(response.body());

                }else {
                    requestStatus_interface.onSuccessResponse(null);
                    requestStatus_interface.onFailureResponse(response.body().getMessge());
                }
            }

            @Override
            public void onFailure(Call<CouponList_RequestModel> call, Throwable t) {
                requestStatus_interface.onSuccessResponse(null);
            }
        });

    }



    public void ApplyCoupon_ApiCall(String Order_Products,String TotalPrice,String CouponId,RequestStatus_Interface requestStatus_interface){

        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        String token = sharedPreferenceManager.getUserToken();

        HashMap<String,String>params=new HashMap<>();
        params.put("checkproducts",Order_Products);
        params.put("TotalPrice",TotalPrice);
        params.put("CouponId",CouponId);
        Log.e("ApplyCouponApiCode",params.toString());


        Call<ApplyCoupon_RequestModel> call = RetrofitClient.getInstance().getApiService().applyCoupon("Token "+token,params);
        call.enqueue(new Callback<ApplyCoupon_RequestModel>() {
            @Override
            public void onResponse(Call<ApplyCoupon_RequestModel> call, Response<ApplyCoupon_RequestModel> response) {

               Log.e("ApplyCouponApiCode",response.body().getStatus()+","+response.body().getMessge());
                if (response.isSuccessful()&&response.body().getStatus()==200&&response.body().getPayload()!=null){

                    ShowToast.ShowMsgToast(response.body().getMessge(),context);
                    requestStatus_interface.onSuccessResponse(response.body().getPayload());

                } else if (response.isSuccessful()&&response.body().getStatus()==201) {
                    ShowToast.ShowMsgToast(response.body().getMessge(),context);
                   /* SharedPreferenceManager.clearSharedPreferences(context);
                    Intent intent=new Intent(context, Authentication_Activity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);*/




                } else {
                    requestStatus_interface.onSuccessResponse(null);
                    requestStatus_interface.onFailureResponse(response.body().getMessge());
                }
            }

            @Override
            public void onFailure(Call<ApplyCoupon_RequestModel> call, Throwable t) {
                requestStatus_interface.onSuccessResponse(null);
                requestStatus_interface.onFailureResponse(t.getMessage());
            }
        });

        



    }



}
