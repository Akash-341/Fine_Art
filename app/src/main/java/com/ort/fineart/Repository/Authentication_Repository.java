package com.ort.fineart.Repository;

import android.content.Context;
import android.util.Log;

import com.ort.fineart.Api_Handel.RequestStatus_Interface;
import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.Model.Request_Model.Authentication.ForgotPassword_RequestModel;
import com.ort.fineart.Model.Request_Model.Authentication.Login_RequestModel;
import com.ort.fineart.Model.Request_Model.Authentication.Registration_RequestModel;
import com.ort.fineart.Model.Request_Model.Authentication.ResetPassword_RequestModel;
import com.ort.fineart.Model.Request_Model.MyCart.MyCartList_RequestModel;
import com.ort.fineart.Utils.SharedPreferenceManager;

import com.ort.fineart.Utils.ShowDialog;
import com.ort.fineart.Utils.ShowToast;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Authentication_Repository {

    Context context;

    public Authentication_Repository(Context context) {
        this.context = context;
    }



    public void Login_ApiCall(String email,String password,RequestStatus_Interface requestStatus_interface) {


        HashMap<String,String>loginParms=new HashMap<>();
        loginParms.put("email",email);
        loginParms.put("password",password);

        Call<Login_RequestModel> call = RetrofitClient.getInstance().getApiService().login(loginParms);
        call.enqueue(new Callback<Login_RequestModel>() {
            @Override
            public void onResponse(Call<Login_RequestModel> call, Response<Login_RequestModel> response) {
                if (response.isSuccessful()&&response.body()!=null&&response.body().getStatus()==200){

                    requestStatus_interface.onSuccessResponse(response.body());

                }else {

                    ShowToast.ShowMsgToast(response.body().getMessage(),context);

                    requestStatus_interface.onSuccessResponse(null);
                }
            }

            @Override
            public void onFailure(Call<Login_RequestModel> call, Throwable t) {
                ShowToast.ShowMsgToast(t.getMessage(),context);
                //ShowDialog.hideCustomProgressDialog();
                requestStatus_interface.onSuccessResponse(null);
            }
        });




    }

    public void Registration_ApiCall(String firstName,String lastName,String mobileNo,String email,String password,RequestStatus_Interface requestStatus_interface) {


        HashMap<String, String> registationParams = new HashMap<>();
        registationParams.put("FirstName",firstName);
        registationParams.put("LastName",lastName);
        registationParams.put("Email",email);
        registationParams.put("PhoneNumber",mobileNo);
        registationParams.put("Password",password);





        Call<Registration_RequestModel> call = RetrofitClient.getInstance().getApiService().registerUser(registationParams);
        call.enqueue(new Callback<Registration_RequestModel>() {
            @Override
            public void onResponse(Call<Registration_RequestModel> call, Response<Registration_RequestModel> response) {

                Log.e("Registration_Call",response.message()+":Msg"+response.code());
                if (response.isSuccessful()&&response.body()!=null&&response.body().getStatus()==200){
                    ShowToast.ShowMsgToast(response.body().getMessge(),context);
                    requestStatus_interface.onSuccessResponse(response.body());
                    ShowDialog.hideCustomProgressDialog();
                }else {

                    ShowToast.ShowMsgToast(response.body().getMessge(),context);
                    ShowDialog.hideCustomProgressDialog();
                    requestStatus_interface.onSuccessResponse(null);
                }




            }

            @Override
            public void onFailure(Call<Registration_RequestModel> call, Throwable t) {
                Log.e("Registration_Call",t.getMessage()+":error Message");
                ShowToast.ShowMsgToast(t.getMessage(),context);
                requestStatus_interface.onSuccessResponse(null);
            }
        });



    }

    public void ForgotPassword_ApiCall(String email,RequestStatus_Interface requestStatus_interface) {


        HashMap<String,String>loginParms=new HashMap<>();
        loginParms.put("email",email);

        Call<ForgotPassword_RequestModel> call = RetrofitClient.getInstance().getApiService().forgotPassword(loginParms);

        call.enqueue(new Callback<ForgotPassword_RequestModel>() {
            @Override
            public void onResponse(Call<ForgotPassword_RequestModel> call, Response<ForgotPassword_RequestModel> response) {
                if (response.isSuccessful()&&response.body()!=null&&response.body().getStatus()==200){
                    requestStatus_interface.onSuccessResponse(response.body());

                } else if (response.isSuccessful()&&response.body()!=null&&response.body().getStatus()==201) {

                    requestStatus_interface.onSuccessResponse(response.body());


                } else {

                    requestStatus_interface.onSuccessResponse(null);

                }
            }

            @Override
            public void onFailure(Call<ForgotPassword_RequestModel> call, Throwable t) {
                ShowToast.ShowMsgToast(t.getMessage(),context);
             //   ShowDialog.hideCustomProgressDialog();
                requestStatus_interface.onSuccessResponse(null);
            }
        });

    }

    public void ResetPassword_ApiCall(String oldPass,String newPass,RequestStatus_Interface requestStatus_interface) {

        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        String token = "Token "+sharedPreferenceManager.getUserToken();
        HashMap<String,String>loginParms=new HashMap<>();
        loginParms.put("oldpassword",oldPass);
        loginParms.put("newpassword",newPass);
        Call<ResetPassword_RequestModel> call = RetrofitClient.getInstance().getApiService().resetPassword(token,loginParms);
        call.enqueue(new Callback<ResetPassword_RequestModel>() {
            @Override
            public void onResponse(Call<ResetPassword_RequestModel> call, Response<ResetPassword_RequestModel> response) {
                if (response.isSuccessful()&&response.body()!=null&&response.body().getStatus()==200||response.isSuccessful()&&response.body()!=null&&response.body().getStatus()==201){
                    requestStatus_interface.onSuccessResponse(response.body());
                    ShowDialog.hideCustomProgressDialog();
                }else {

                    ShowDialog.hideCustomProgressDialog();
                    requestStatus_interface.onSuccessResponse(null);
                }
            }

            @Override
            public void onFailure(Call<ResetPassword_RequestModel> call, Throwable t) {
                ShowToast.ShowMsgToast(t.getMessage(),context);
                ShowDialog.hideCustomProgressDialog();
                requestStatus_interface.onSuccessResponse(null);
            }
        });




    }








}
