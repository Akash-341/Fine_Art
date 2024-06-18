package com.ort.fineart.Repository;

import android.content.Context;

import com.ort.fineart.Api_Handel.ApiService;
import com.ort.fineart.Api_Handel.RequestStatus_Interface;
import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.Model.Request_Model.PersonalDetails.Update_Personal_Details_RequestModel;
import com.ort.fineart.Model.Response_Model.Home.HomeBottomBanner_ResponseModel;
import com.ort.fineart.Model.Response_Model.PersonalDetails_ResponseModel;
import com.ort.fineart.Utils.SharedPreferenceManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_Repository {
    Context context;

    public Profile_Repository(Context con) {
        context=con;

    }



    public void GetPersonalDetails(String email,RequestStatus_Interface requestStatus_interface){
        Call<PersonalDetails_ResponseModel> call = RetrofitClient.getInstance().getApiService().getPersonalData(email);
        call.enqueue(new Callback<PersonalDetails_ResponseModel>() {
            @Override
            public void onResponse(Call<PersonalDetails_ResponseModel> call, Response<PersonalDetails_ResponseModel> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    requestStatus_interface.onSuccessResponse(response.body());
                }else {
                    requestStatus_interface.onSuccessResponse(null);
                }
            }

            @Override
            public void onFailure(Call<PersonalDetails_ResponseModel> call, Throwable t) {
                requestStatus_interface.onSuccessResponse(null);
            }
        });
    }





    public void UpdatePersonalDetails(String PhoneNumber,String FirstName,String LastName,RequestStatus_Interface requestStatus_interface){

        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        String token = "Token "+sharedPreferenceManager.getUserToken();

        Call<Update_Personal_Details_RequestModel> call = RetrofitClient.getInstance().getApiService().updatePersonalDetails(token,PhoneNumber,FirstName,LastName);
        call.enqueue(new Callback<Update_Personal_Details_RequestModel>() {
            @Override
            public void onResponse(Call<Update_Personal_Details_RequestModel> call, Response<Update_Personal_Details_RequestModel> response) {
                if (response.isSuccessful()&&response.body()!=null&&response.body().getStatus()==200){
                    requestStatus_interface.onSuccessResponse(response.body());
                } else if (response.isSuccessful()&&response.body()!=null&&response.body().getStatus()==201) {

                } else {
                    requestStatus_interface.onSuccessResponse(null);
                }
            }

            @Override
            public void onFailure(Call<Update_Personal_Details_RequestModel> call, Throwable t) {
                requestStatus_interface.onSuccessResponse(null);
            }
        });


    }



}
