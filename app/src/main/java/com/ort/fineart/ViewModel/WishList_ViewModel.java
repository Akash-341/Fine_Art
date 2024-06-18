package com.ort.fineart.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ort.fineart.Api_Handel.RequestStatus_Interface;
import com.ort.fineart.Model.Request_Model.WishList.AddRemove_WishListProduct_RequestModel;
import com.ort.fineart.Model.Request_Model.WishList.GetWishList_RequestModel;
import com.ort.fineart.Model.Response_Model.WishList.GetWishList_ResponseModel;
import com.ort.fineart.Repository.WishList_Repository;

import java.util.List;

public class WishList_ViewModel extends AndroidViewModel {
    MutableLiveData<List<GetWishList_ResponseModel>>WishList_LiveData=new MutableLiveData<>();
    MutableLiveData<Boolean>WishListProductAddDelete=new MutableLiveData<>();
    MutableLiveData<AddRemove_WishListProduct_RequestModel>AddToWishList_LiveData=new MutableLiveData<>();



    public WishList_Repository wishList_repository;


    public WishList_ViewModel(@NonNull Application application) {
        super(application);
        wishList_repository=new WishList_Repository(application);


    }

    /*
    Return
     */

    public LiveData<List<GetWishList_ResponseModel>>getWishList(){
        return WishList_LiveData;
    }

    public LiveData<Boolean>wishlistAddRemove(){
        return WishListProductAddDelete;
    }

    public LiveData<AddRemove_WishListProduct_RequestModel>addToWishList(){
        return AddToWishList_LiveData;
    }




    /*
    Calls
     */


    public void GetWishList_Callback(){
        wishList_repository.GetWishList_ApiCall(new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                Log.e("MyWhishlist",":5");

                if (data!=null){
                    GetWishList_RequestModel model= (GetWishList_RequestModel) data;
                    if (model.getPayload()!=null&&model.getPayload().size()!=0){
                        WishList_LiveData.postValue(model.getPayload());
                        Log.e("MyWhishlist",":10");

                    }
                    Log.e("MyWhishlist",":11");
                }else {
                    WishList_LiveData.postValue(null);
                    Log.e("MyWhishlist",":12");
                }


            }

            @Override
            public void onFailureResponse(String message) {
                WishList_LiveData.postValue(null);
            }
        });
    }

    public void AddRemoveWishListProduct_Callback(int varientId){
        wishList_repository.AddRemoveWishListProduct_ApiCall(varientId, new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                if (data!=null){
                    AddRemove_WishListProduct_RequestModel model= (AddRemove_WishListProduct_RequestModel) data;

                    if (model!=null){
                        WishListProductAddDelete.postValue(true);

                    }
                } else {
                    WishListProductAddDelete.postValue(false);
                }

            }

            @Override
            public void onFailureResponse(String message) {
                WishListProductAddDelete.postValue(false);
            }
        });

    }
    public void AddToWishList_Callback(int varientId){
        wishList_repository.AddRemoveWishListProduct_ApiCall(varientId, new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                if (data!=null){
                    AddRemove_WishListProduct_RequestModel model= (AddRemove_WishListProduct_RequestModel) data;

                    if (model!=null){
                        AddToWishList_LiveData.postValue(model);

                    }
                } else {
                    AddToWishList_LiveData.postValue(null);
                }

            }

            @Override
            public void onFailureResponse(String message) {
                AddToWishList_LiveData.postValue(null);
            }
        });

    }

}
