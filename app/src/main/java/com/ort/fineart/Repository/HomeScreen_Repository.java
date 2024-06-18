package com.ort.fineart.Repository;

import android.util.Log;

import com.ort.fineart.Api_Handel.RequestStatus_Interface;
import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.Model.Request_Model.Home.HomeBestSeller_RequestModel;
import com.ort.fineart.Model.Request_Model.Home.HomeDealOfDay_RequestModel;
import com.ort.fineart.Model.Response_Model.Home.HomeBottomBanner_ResponseModel;
import com.ort.fineart.Model.Response_Model.Home.HomeTestimonial_ResponseModel;
import com.ort.fineart.Model.Response_Model.Home.HomeTopBanner_ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreen_Repository {


    public HomeScreen_Repository(){

    }



    public void GetTestimonials_ApiCall(RequestStatus_Interface requestStatus_interface){

        Call<List<HomeTestimonial_ResponseModel>> call = RetrofitClient.getInstance().getApiService().getHomeTestimonials();
        call.enqueue(new Callback<List<HomeTestimonial_ResponseModel>>() {
            @Override
            public void onResponse(Call<List<HomeTestimonial_ResponseModel>> call, Response<List<HomeTestimonial_ResponseModel>> response) {
                if (response.isSuccessful()&&response.body()!=null) {

                    requestStatus_interface.onSuccessResponse(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<HomeTestimonial_ResponseModel>> call, Throwable t) {

                requestStatus_interface.onSuccessResponse(null);
                requestStatus_interface.onFailureResponse(t.getMessage());
            }
        });




    }


    public void GetTopBanner_ApiCall(RequestStatus_Interface requestStatus_interface){

        Call<List<HomeTopBanner_ResponseModel>> call = RetrofitClient.getInstance().getApiService().getHomeTopBanner();
        call.enqueue(new Callback<List<HomeTopBanner_ResponseModel>>() {
            @Override
            public void onResponse(Call<List<HomeTopBanner_ResponseModel>> call, Response<List<HomeTopBanner_ResponseModel>> response) {
                if (response.isSuccessful()&&response.body()!=null) {

                    requestStatus_interface.onSuccessResponse(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<HomeTopBanner_ResponseModel>> call, Throwable t) {
                requestStatus_interface.onSuccessResponse(null);
                requestStatus_interface.onFailureResponse(t.getMessage());
            }
        });


    }


    public void GetBottomBanner_ApiCall(RequestStatus_Interface requestStatus_interface){

        Call<List<HomeBottomBanner_ResponseModel>> call = RetrofitClient.getInstance().getApiService().getHomeBottomBanner();
        call.enqueue(new Callback<List<HomeBottomBanner_ResponseModel>>() {
            @Override
            public void onResponse(Call<List<HomeBottomBanner_ResponseModel>> call, Response<List<HomeBottomBanner_ResponseModel>> response) {
                if (response.isSuccessful()&&response.body()!=null) {

                    requestStatus_interface.onSuccessResponse(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<HomeBottomBanner_ResponseModel>> call, Throwable t) {
                requestStatus_interface.onSuccessResponse(null);
                requestStatus_interface.onFailureResponse(t.getMessage());
            }
        });


    }


    public void GetDealOfDay_ApiCall(RequestStatus_Interface requestStatus_interface){

        Call<HomeDealOfDay_RequestModel> call = RetrofitClient.getInstance().getApiService().getDealOfTheDay();
        call.enqueue(new Callback<HomeDealOfDay_RequestModel>() {
            @Override
            public void onResponse(Call<HomeDealOfDay_RequestModel> call, Response<HomeDealOfDay_RequestModel> response) {
                if (response.isSuccessful()&&response.body()!=null&&response.body().getStatus()==200) {

                    requestStatus_interface.onSuccessResponse(response.body());

                }else {

                    requestStatus_interface.onSuccessResponse(null);

                }
            }

            @Override
            public void onFailure(Call<HomeDealOfDay_RequestModel> call, Throwable t) {

                requestStatus_interface.onSuccessResponse(null);
                requestStatus_interface.onFailureResponse(t.getMessage());
            }
        });




    }

    public void BestSeller_ApiCall(RequestStatus_Interface requestStatus_interface){

        Call<HomeBestSeller_RequestModel> call = RetrofitClient.getInstance().getApiService().getbestSellerList();
        call.enqueue(new Callback<HomeBestSeller_RequestModel>() {
            @Override
            public void onResponse(Call<HomeBestSeller_RequestModel> call, Response<HomeBestSeller_RequestModel> response) {
                if (response.isSuccessful()&&response.body()!=null&&response.body().getStatus()==200) {

                    requestStatus_interface.onSuccessResponse(response.body());

                }else {
                    requestStatus_interface.onSuccessResponse(null);

                }
            }

            @Override
            public void onFailure(Call<HomeBestSeller_RequestModel> call, Throwable t) {
                requestStatus_interface.onSuccessResponse(null);
                requestStatus_interface.onFailureResponse(t.getMessage());
            }
        });






    }







}
