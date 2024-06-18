package com.ort.fineart.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ort.fineart.Api_Handel.RequestStatus_Interface;
import com.ort.fineart.Model.Request_Model.UserAddress.AddAddress_RequestModel;
import com.ort.fineart.Model.Request_Model.UserAddress.DeleteAddress_RequestModel;
import com.ort.fineart.Model.Request_Model.UserAddress.GetAddressById_RequestModel;
import com.ort.fineart.Model.Request_Model.UserAddress.GetUserAddress_RequestModel;
import com.ort.fineart.Model.Request_Model.UserAddress.UpdateAddress_RequestModel;
import com.ort.fineart.Model.Response_Model.PersonalDetails_ResponseModel;
import com.ort.fineart.Model.Response_Model.UserAddress.GetAddressById_ResponseModel;
import com.ort.fineart.Model.Response_Model.UserAddress.GetUserAddress_ResponseModel;
import com.ort.fineart.Repository.Profile_Repository;
import com.ort.fineart.Repository.UserAddress_Repository;

import java.util.List;

public class UserAddress_ViewModel extends AndroidViewModel {

    private UserAddress_Repository userAddress_repository;
    private MutableLiveData<List<GetUserAddress_ResponseModel>> addressList_LiveData = new MutableLiveData<>();

    private MutableLiveData<AddAddress_RequestModel> addAddress_LiveData = new MutableLiveData<>();
    private MutableLiveData<UpdateAddress_RequestModel> updateAddress_LiveData = new MutableLiveData<>();
    private MutableLiveData<GetAddressById_ResponseModel> addressById_LiveData = new MutableLiveData<>();

    private MutableLiveData<DeleteAddress_RequestModel> deleteAddress_LiveData = new MutableLiveData<>();




    public UserAddress_ViewModel(@NonNull Application application) {
        super(application);

        userAddress_repository = new UserAddress_Repository(application);
    }

    /**
     * Return Data
     */

    public LiveData<List<GetUserAddress_ResponseModel>> getAddressList() {
        return addressList_LiveData;
    }

    public LiveData<AddAddress_RequestModel> addAddress(){
        return addAddress_LiveData;
    }

    public LiveData<UpdateAddress_RequestModel> updateAddress(){
        return updateAddress_LiveData;
    }

    public LiveData<GetAddressById_ResponseModel> getAddressById(){
        return  addressById_LiveData;
    }


    public LiveData<DeleteAddress_RequestModel> deleteAddress(){
        return deleteAddress_LiveData;
    }

    /**
     * Api Call
     */

    public void GetAddressList_Callback() {
        userAddress_repository.GetAddress_ApiCall(new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                GetUserAddress_RequestModel getUserAddress_requestModel = (GetUserAddress_RequestModel) data;
                if (getUserAddress_requestModel != null && getUserAddress_requestModel.getPayload() != null & getUserAddress_requestModel.getPayload().size() != 0) {
                    addressList_LiveData.postValue(getUserAddress_requestModel.getPayload());
                } else {
                    addressList_LiveData.postValue(null);

                }


            }

            @Override
            public void onFailureResponse(String message) {
                addressList_LiveData.postValue(null);
            }
        });


    }

    public void AddAddress_Callback(String AddressLine_1, String AddressLine_2, String Country, String State, String City, String Pincode) {

        userAddress_repository.AddAddress_ApiCall(AddressLine_1, AddressLine_2, Country, State, City, Pincode, new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                AddAddress_RequestModel addAddress_requestModel = (AddAddress_RequestModel) data;
                if (addAddress_requestModel != null && addAddress_requestModel.getPayload() != null) {
                    addAddress_LiveData.postValue(addAddress_requestModel);

                } else {
                    addAddress_LiveData.postValue(null);

                }


            }

            @Override
            public void onFailureResponse(String message) {

                addAddress_LiveData.postValue(null);

            }
        });

    }
    public void UpdateAddress_Callback(Integer Address_Id,String AddressLine_1,String AddressLine_2,String Country,String State,String City,int Pincode) {

        userAddress_repository.UpdateAddress_ApiCall(Address_Id,AddressLine_1,AddressLine_2,Country,State,City, String.valueOf(Pincode), new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                UpdateAddress_RequestModel updateAddress_requestModel = (UpdateAddress_RequestModel) data;
                if (updateAddress_requestModel != null & updateAddress_requestModel.getPayload() != null) {
                    updateAddress_LiveData.postValue(updateAddress_requestModel);


                } else {
                    updateAddress_LiveData.postValue(null);

                }


            }

            @Override
            public void onFailureResponse(String message) {

                updateAddress_LiveData.postValue(null);

            }
        });

    }

    public void GetAddressById_Callback(int AddressId){

        userAddress_repository.GetAddressById_ApiCall(AddressId, new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                GetAddressById_RequestModel getAddressById_requestModel = (GetAddressById_RequestModel) data;
                if (getAddressById_requestModel != null & getAddressById_requestModel.getPayload() != null) {
                    addressById_LiveData.postValue(getAddressById_requestModel.getPayload());

                } else {
                    addressById_LiveData.postValue(null);
                }


            }

            @Override
            public void onFailureResponse(String message) {

                addressById_LiveData.postValue(null);

            }
        });
    }

    public void DeleteAddress_Callback(Integer AddressId){
        userAddress_repository.DeleteAddress_ApiCall(AddressId, new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                DeleteAddress_RequestModel deleteAddress_requestModel= (DeleteAddress_RequestModel) data;
                if (deleteAddress_requestModel!=null&&deleteAddress_requestModel.getStatus()==200){
                    deleteAddress_LiveData.postValue(deleteAddress_requestModel);
                }else {
                    deleteAddress_LiveData.postValue(null);
                }


            }

            @Override
            public void onFailureResponse(String message) {
                deleteAddress_LiveData.postValue(null);
            }
        });
    }






}
