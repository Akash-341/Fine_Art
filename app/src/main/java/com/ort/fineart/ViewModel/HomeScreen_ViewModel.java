package com.ort.fineart.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ort.fineart.Api_Handel.RequestStatus_Interface;
import com.ort.fineart.Model.Request_Model.Home.HomeBestSeller_RequestModel;
import com.ort.fineart.Model.Request_Model.Home.HomeDealOfDay_RequestModel;
import com.ort.fineart.Model.Response_Model.Home.HomeBestSeller_ResponseModel;
import com.ort.fineart.Model.Response_Model.Home.HomeBottomBanner_ResponseModel;
import com.ort.fineart.Model.Response_Model.Home.HomeDealOfDay_ResponseModel;
import com.ort.fineart.Model.Response_Model.Home.HomeTestimonial_ResponseModel;
import com.ort.fineart.Model.Response_Model.Home.HomeTopBanner_ResponseModel;
import com.ort.fineart.Repository.HomeScreen_Repository;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen_ViewModel extends ViewModel {





    private HomeScreen_Repository homeScreen_repository;
    private MutableLiveData<List<HomeTestimonial_ResponseModel>> testimonialList_LiveData=new MutableLiveData<>();
    private MutableLiveData<List<HomeTopBanner_ResponseModel>> topBannerList_LiveData=new MutableLiveData<>();
    private MutableLiveData<List<HomeBottomBanner_ResponseModel>> bottomBannerList_LiveData=new MutableLiveData<>();

    private MutableLiveData<List<HomeDealOfDay_ResponseModel>> deal_of_day_List_LiveData=new MutableLiveData<>();

    private MutableLiveData<List<HomeBestSeller_ResponseModel>> bestSeller_LiveData=new MutableLiveData<>();



    public HomeScreen_ViewModel(){

        homeScreen_repository=new HomeScreen_Repository();
    }



    /*
        Return Data
    */

    public LiveData<List<HomeTestimonial_ResponseModel>> getTestimonial(){
        return testimonialList_LiveData;
    }
    public LiveData<List<HomeTopBanner_ResponseModel>> getTopBanner(){
        return topBannerList_LiveData;
    }


    public LiveData<List<HomeBottomBanner_ResponseModel>>getBottomBanner(){
        return bottomBannerList_LiveData;
    }



    public LiveData<List<HomeDealOfDay_ResponseModel>> get_deal_of_day(){
        return deal_of_day_List_LiveData;
    }

    public LiveData<List<HomeBestSeller_ResponseModel>>getBestSeller(){
        return bestSeller_LiveData;
    }





    /*
        Api Call
    */

    public void GetTestimonial_Callback(){
        homeScreen_repository.GetTestimonials_ApiCall(new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                List<HomeTestimonial_ResponseModel> responseModels= (List<HomeTestimonial_ResponseModel>) data;
                if (responseModels!=null&&!responseModels.isEmpty()){
                    testimonialList_LiveData.postValue(responseModels);
                }else {
                    testimonialList_LiveData.postValue(null);
                }

            }

            @Override
            public void onFailureResponse(String message) {
                testimonialList_LiveData.postValue(null);
            }
        });



    }
    public void GetTopBanner_Callback(){
        homeScreen_repository.GetTopBanner_ApiCall(new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                List<HomeTopBanner_ResponseModel> responseModels= (List<HomeTopBanner_ResponseModel>) data;
                if (responseModels!=null&&!responseModels.isEmpty()){
                    topBannerList_LiveData.postValue(responseModels);
                }else {
                    topBannerList_LiveData.postValue(null);
                }
            }

            @Override
            public void onFailureResponse(String message) {
                topBannerList_LiveData.postValue(null);
            }
        });
    }

    public void GetBottomBanner_Callback(){
        homeScreen_repository.GetBottomBanner_ApiCall(new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                List<HomeBottomBanner_ResponseModel> responseModels= (List<HomeBottomBanner_ResponseModel>) data;
                if (responseModels!=null&&!responseModels.isEmpty()){
                    bottomBannerList_LiveData.postValue(responseModels);
                }else {
                    bottomBannerList_LiveData.postValue(null);
                }
            }

            @Override
            public void onFailureResponse(String message) {
                bottomBannerList_LiveData.postValue(null);
            }
        });
    }


    public void GetDealsOfTheDay_Callback(){
        homeScreen_repository.GetDealOfDay_ApiCall(new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                HomeDealOfDay_RequestModel requestModel= (HomeDealOfDay_RequestModel) data;

                    if (requestModel!=null&&requestModel.getPayload()!=null&&requestModel.getPayload().size()!=0){
                        deal_of_day_List_LiveData.postValue(requestModel.getPayload());
                        Log.e("DealOfTheDay",requestModel.getPayload().size()+":1");
                    }else {
                        Log.e("DealOfTheDay",":Not");
                        deal_of_day_List_LiveData.postValue(null);
                    }


            }

            @Override
            public void onFailureResponse(String message) {
                deal_of_day_List_LiveData.postValue(null);
            }
        });
    }

    public void GetBestSeller_Callback(){
        homeScreen_repository.BestSeller_ApiCall(new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {

                HomeBestSeller_RequestModel requestModel= (HomeBestSeller_RequestModel) data;

                    if (requestModel!=null&&requestModel.getPayload()!=null&&requestModel.getPayload().size()!=0){

                        bestSeller_LiveData.postValue(requestModel.getPayload());
                    }else {
                        bestSeller_LiveData.postValue(null);
                    }



            }

            @Override
            public void onFailureResponse(String message) {
                bestSeller_LiveData.postValue(null);
            }
        });
    }








}
