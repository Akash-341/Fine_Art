package com.ort.fineart.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ort.fineart.Api_Handel.RequestStatus_Interface;
import com.ort.fineart.Model.Request_Model.PersonalDetails.Update_Personal_Details_RequestModel;
import com.ort.fineart.Model.Response_Model.Home.HomeTestimonial_ResponseModel;
import com.ort.fineart.Model.Response_Model.PersonalDetails_ResponseModel;
import com.ort.fineart.Repository.Authentication_Repository;
import com.ort.fineart.Repository.HomeScreen_Repository;
import com.ort.fineart.Repository.Profile_Repository;

import java.util.List;

public class Profile_ViewModel extends AndroidViewModel {

    private Profile_Repository profile_repository;
    private MutableLiveData<PersonalDetails_ResponseModel> personalDetails_LiveData=new MutableLiveData<>();

    private MutableLiveData<Update_Personal_Details_RequestModel>updatePersonalDetails_LiveData=new MutableLiveData<>();







    public Profile_ViewModel(@NonNull Application application) {
        super(application);
        profile_repository=new Profile_Repository(application);

    }
    /**
        Return Data
    */

    public LiveData<PersonalDetails_ResponseModel> getProfileDetails(){
        return personalDetails_LiveData;
    }



    public LiveData<Update_Personal_Details_RequestModel>updatePersonalDetails(){
        return updatePersonalDetails_LiveData;
    }


     /**
        Api Call
    */

    public void GetPersonalDetails_Callback(String Email){
        profile_repository.GetPersonalDetails(Email.trim(), new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                PersonalDetails_ResponseModel responseModel= (PersonalDetails_ResponseModel) data;

                if (responseModel!=null){
                    personalDetails_LiveData.postValue(responseModel);
                }else {
                    personalDetails_LiveData.postValue(null);
                }

            }

            @Override
            public void onFailureResponse(String message) {
                personalDetails_LiveData.postValue(null);
            }
        });




    }



    public void UpdatePersonalDetails_Callback(String firstName,String lastName,String mobileNo){

        profile_repository.UpdatePersonalDetails(firstName, lastName, mobileNo, new RequestStatus_Interface() {
            @Override
            public void onSuccessResponse(Object data) {
                Update_Personal_Details_RequestModel model= (Update_Personal_Details_RequestModel) data;

                if (model!=null&&model.getStatus()==200){
                    updatePersonalDetails_LiveData.postValue(model);
                }else {
                    updatePersonalDetails_LiveData.postValue(null);
                }




            }

            @Override
            public void onFailureResponse(String message) {
                updatePersonalDetails_LiveData.postValue(null);
            }
        });






    }

}
