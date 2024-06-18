package com.ort.fineart.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ort.fineart.Api_Handel.RequestStatus_Interface;
import com.ort.fineart.Model.Request_Model.Hub.GetHubProductListByCategory_RequestModel;
import com.ort.fineart.Model.Response_Model.Home.HomeTestimonial_ResponseModel;
import com.ort.fineart.Model.Response_Model.Hub.GetHubProductListByCategory_ResponseModel;
import com.ort.fineart.Model.Response_Model.Hub.Hub_CategoryList_ResponseModel;
import com.ort.fineart.Model.Response_Model.Hub.Hub_SubCategoryList_ResponseModel;
import com.ort.fineart.Repository.HomeScreen_Repository;
import com.ort.fineart.Repository.Hub_Repository;

import java.util.List;

public class Hub_ViewModel extends AndroidViewModel {


    private MutableLiveData<List<Hub_CategoryList_ResponseModel>> hubCategory_LiveData=new MutableLiveData<>();

    private MutableLiveData<List<Hub_SubCategoryList_ResponseModel>> hubSubCategory_LiveData=new MutableLiveData<>();


    private MutableLiveData<List<GetHubProductListByCategory_ResponseModel>> hubProductList_LiveData=new MutableLiveData<>();



    Hub_Repository hub_repository;
    public Hub_ViewModel(@NonNull Application application) {
        super(application);
        hub_repository=new Hub_Repository();
    }

    /*
        Return Data
    */

    public LiveData<List<Hub_CategoryList_ResponseModel>> getHubCategory(){
        return hubCategory_LiveData;
    }
    public LiveData<List<Hub_SubCategoryList_ResponseModel>> getHubSubCategory(){
        return hubSubCategory_LiveData;
    }


    public LiveData<List<GetHubProductListByCategory_ResponseModel>> getHubProduct_List(){
        return hubProductList_LiveData;
    }




     /*
        Api Call
    */

    public void GetHubCategory_Callback(){

        hub_repository.GetHubCategoryList_ApiCall(new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                List<Hub_CategoryList_ResponseModel>hub_categoryList= (List<Hub_CategoryList_ResponseModel>) data;
                 if (hub_categoryList!=null&&hub_categoryList.size()!=0){
                     hubCategory_LiveData.postValue(hub_categoryList);
                 }else {
                     hubCategory_LiveData.postValue(null);
                 }


            }

            @Override
            public void onFailureResponse(String message) {

                hubCategory_LiveData.postValue(null);
            }
        });





    }
    public void GetHubSubCategory_Callback(String CategoryId){

        hub_repository.GetSubCategoryHubList_ApiCall(CategoryId, new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                List<Hub_SubCategoryList_ResponseModel>hub_categoryList= (List<Hub_SubCategoryList_ResponseModel>) data;
                if (hub_categoryList!=null&&hub_categoryList.size()!=0){
                    hubSubCategory_LiveData.postValue(hub_categoryList);

                }else {
                    hubSubCategory_LiveData.postValue(null);
                }
            }

            @Override
            public void onFailureResponse(String message) {
                hubSubCategory_LiveData.postValue(null);
            }
        });







    }

    public void GetHubListByCategory_Callback(String CategoryId,String subcategory){
        hub_repository.GetHubListByCategory_ApiCall(CategoryId,subcategory, new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                List<GetHubProductListByCategory_ResponseModel>hub_productList= (List<GetHubProductListByCategory_ResponseModel>) data;
                if (hub_productList!=null&&hub_productList.size()!=0){
                    hubProductList_LiveData.postValue(hub_productList);


                }else {

                    hubProductList_LiveData.postValue(null);
                }

            }

            @Override
            public void onFailureResponse(String message) {
                hubProductList_LiveData.postValue(null);
            }
        });

    }




}
