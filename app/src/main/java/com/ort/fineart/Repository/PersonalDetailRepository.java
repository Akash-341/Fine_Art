package com.ort.fineart.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.Model.Response_Model.PersonalDetailsData;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalDetailRepository {
    private final MutableLiveData<PersonalDetailsData> personalData= new MutableLiveData<>();

    public LiveData<PersonalDetailsData> getPersonalData(String requestBody){
        Call<PersonalDetailsData> call = RetrofitClient.getInstance().getApiService().getPersonalData(requestBody);
        call.enqueue(new Callback<PersonalDetailsData>() {
            @Override
            public void onResponse(Call<PersonalDetailsData> call, Response<PersonalDetailsData> response) {
                if (response.isSuccessful()){
                    PersonalDetailsData personalDetailsDataList=response.body();
                    personalData.setValue(personalDetailsDataList);
                }else{
                    personalData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<PersonalDetailsData> call, Throwable t) {
                personalData.setValue(null);
            }
        });
        return personalData;
    }

}
