package com.ort.fineart.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ort.fineart.Api_Handel.RequestStatus_Interface;
import com.ort.fineart.Model.Request_Model.MyCart.AddInCart_RequestModel;
import com.ort.fineart.Model.Request_Model.MyCart.ChangeProductQuntity_RequestModel;
import com.ort.fineart.Model.Request_Model.MyCart.MyCartList_RequestModel;
import com.ort.fineart.Model.Request_Model.MyCart.RemoveCartProduct_RequestModel;
import com.ort.fineart.Model.Response_Model.MyCart.MyCart_ResponseModel;
import com.ort.fineart.Repository.MyCart_Repository;

import java.util.List;


public class MyCart_ViewModel extends AndroidViewModel {
    public MyCart_Repository myCart_repository;
    public MutableLiveData<List<MyCart_ResponseModel>> myCart_ListLiveData=new MutableLiveData<>();

    public MutableLiveData<AddInCart_RequestModel> addInCart_LiveData=new MutableLiveData<>();






    public MutableLiveData<Boolean> removeCartProduct_LiveData=new MutableLiveData<>();

    public MutableLiveData<Boolean>changeCartProductQuantity_LiveData=new MutableLiveData<>();




    public MyCart_ViewModel(@NonNull Application application) {
        super(application);
        myCart_repository = new MyCart_Repository(application);


    }



    /**
     *
     * All Return
     */
    public LiveData<List<MyCart_ResponseModel>> GetMyCartList() {
        return myCart_ListLiveData;
    }


    public LiveData<Boolean>GetCartChangeQuantity(){
        return changeCartProductQuantity_LiveData;
    }


    public LiveData<AddInCart_RequestModel>addInCart(){
        return addInCart_LiveData;
    }





    /**
     Api Calls
     */





    public void GetMyCartList_Callback(){
        myCart_repository.GetCartList_ApiCall(new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                MyCartList_RequestModel model= (MyCartList_RequestModel) data;
                if (model!=null&&model.getPayload()!=null&&model.getPayload().size()!=0){
                    myCart_ListLiveData.postValue(model.getPayload());
                }
            }

            @Override
            public void onFailureResponse(String message) {
                myCart_ListLiveData.postValue(null);
            }
        });
    }

    public void RemoveCartProduct_ApiCall(Integer productID){

        myCart_repository.RemoveCartProduct(productID, new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                if (data!=null){
                    RemoveCartProduct_RequestModel model= (RemoveCartProduct_RequestModel) data;

                    if (model!=null&&model.getStatus()==200){
                        removeCartProduct_LiveData.postValue(true);
                    }
                }else {
                    removeCartProduct_LiveData.postValue(false);
                }



            }

            @Override
            public void onFailureResponse(String message) {
                removeCartProduct_LiveData.postValue(false);
            }
        });
    }


    public void ChangeProductQuantity_ApiCall(int cart_item_id, int cart_item_quantity){
        myCart_repository.ChangeCartProductQuantity(cart_item_id, cart_item_quantity, new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                if (data!=null){
                    ChangeProductQuntity_RequestModel model= (ChangeProductQuntity_RequestModel) data;
                    if (model.getStatus()==200){
                        Log.e("ChangeCartQuantity",model.getStatus()+":model.getStatus()==200");
                        changeCartProductQuantity_LiveData.postValue(true);
                    }else {
                        changeCartProductQuantity_LiveData.postValue(false);
                    }

                }else {
                    changeCartProductQuantity_LiveData.postValue(false);
                }
            }

            @Override
            public void onFailureResponse(String message) {

            }
        });
    }

    public void AddInCartList_Callback(String Varient_Id,String Quantity){

        myCart_repository.AddInCartList_ApiCall(Varient_Id, Quantity, new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                AddInCart_RequestModel model= (AddInCart_RequestModel) data;
                if (model!=null){
                    addInCart_LiveData.postValue(model);
                    Log.e("AddInCartList_Callback","1");


                }else {
                    addInCart_LiveData.postValue(null);
                    Log.e("AddInCartList_Callback","2");

                }
            }

            @Override
            public void onFailureResponse(String message) {
                addInCart_LiveData.postValue(null);

            }
        });


    }









}
