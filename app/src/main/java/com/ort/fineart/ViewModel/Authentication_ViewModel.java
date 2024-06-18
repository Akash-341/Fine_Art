package com.ort.fineart.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ort.fineart.Api_Handel.RequestStatus_Interface;
import com.ort.fineart.Model.Request_Model.Authentication.ForgotPassword_RequestModel;
import com.ort.fineart.Model.Request_Model.Authentication.Login_RequestModel;
import com.ort.fineart.Model.Request_Model.Authentication.Registration_RequestModel;
import com.ort.fineart.Model.Request_Model.Authentication.ResetPassword_RequestModel;
import com.ort.fineart.Model.Response_Model.MyCart.MyCart_ResponseModel;
import com.ort.fineart.Repository.Authentication_Repository;
import com.ort.fineart.Repository.MyCart_Repository;

import java.util.List;

public class Authentication_ViewModel extends AndroidViewModel {
    public Authentication_Repository authentication_repository;
    public MutableLiveData<Login_RequestModel> login_LiveData=new MutableLiveData<>();
    public MutableLiveData<Registration_RequestModel>registration_LiveData=new MutableLiveData<>();
    public MutableLiveData<ResetPassword_RequestModel>resetPass_LiveData=new MutableLiveData<>();
    public MutableLiveData<ForgotPassword_RequestModel>forgotPass_LiveData=new MutableLiveData<>();













    public Authentication_ViewModel(@NonNull Application application) {
        super(application);
        authentication_repository = new Authentication_Repository(application);
    }

    /**
    All Returns
     */


    public LiveData<Login_RequestModel>getLogin(){
        return login_LiveData;
    }

    public LiveData<Registration_RequestModel>getRegistration(){
        return registration_LiveData;
    }


    public LiveData<ResetPassword_RequestModel>getResetPassword(){
        return resetPass_LiveData;
    }


    public LiveData<ForgotPassword_RequestModel>getForgotPassword(){
        return forgotPass_LiveData;
    }






    /**
    All Callback
     */


    public void Login_Callback(String email,String password){

        authentication_repository.Login_ApiCall(email, password, new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {

                if (data!=null){

                    Login_RequestModel model= (Login_RequestModel) data;
                    login_LiveData.postValue(model);


                }else {
                    login_LiveData.postValue(null);
                }


            }

            @Override
            public void onFailureResponse(String message) {
                login_LiveData.postValue(null);
            }
        });


    }
    public void Registration_Callback(String firstName,String lastName,String mobileNo,String email,String password){

        authentication_repository.Registration_ApiCall(firstName, lastName, mobileNo, email, password, new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {

                Registration_RequestModel model= (Registration_RequestModel) data;
                if (model!=null){
                    registration_LiveData.postValue(model);
                }else {
                    registration_LiveData.postValue(null);
                }




            }

            @Override
            public void onFailureResponse(String message) {
                registration_LiveData.postValue(null);
            }
        });

    }

    public void ResetPassword_Callback(String oldPassword,String newPassword){

        authentication_repository.ResetPassword_ApiCall(oldPassword, newPassword, new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                if (data!=null){

                    ResetPassword_RequestModel model= (ResetPassword_RequestModel) data;
                    resetPass_LiveData.postValue(model);


                }else {
                    resetPass_LiveData.postValue(null);
                }

            }

            @Override
            public void onFailureResponse(String message) {
                resetPass_LiveData.postValue(null);
            }
        });



    }

    public void ForgotPassword_Callback(String email){

        authentication_repository.ForgotPassword_ApiCall(email, new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                if (data!=null){

                    ForgotPassword_RequestModel model= (ForgotPassword_RequestModel) data;
                    forgotPass_LiveData.postValue(model);


                }else {
                    forgotPass_LiveData.postValue(null);
                }

            }

            @Override
            public void onFailureResponse(String message) {
                forgotPass_LiveData.postValue(null);
            }
        });

    }




}
