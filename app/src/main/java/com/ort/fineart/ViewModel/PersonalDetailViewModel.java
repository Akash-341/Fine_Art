package com.ort.fineart.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ort.fineart.Model.Response_Model.PersonalDetailsData;
import com.ort.fineart.Repository.PersonalDetailRepository;
import com.ort.fineart.Utils.SharedPreferenceManager;

import java.util.List;

public class PersonalDetailViewModel extends ViewModel {

    private PersonalDetailRepository personalDetailRepository;
    private LiveData<PersonalDetailsData> personalLiveData;

    public PersonalDetailViewModel(SharedPreferenceManager sharedPreferenceManager){
        personalDetailRepository=new PersonalDetailRepository();
        String email=sharedPreferenceManager.getUserEmail();
        personalLiveData=personalDetailRepository.getPersonalData(email);
    }

    public LiveData<PersonalDetailsData> getPersonalLiveData() {
        return personalLiveData;
    }
}
