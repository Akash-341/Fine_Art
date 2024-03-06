package com.ort.fineart.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ort.fineart.Model.Response_Model.TestimonialModel_ResponseModel;
import com.ort.fineart.Repository.TestimonialRepository;

import java.util.List;

public class TestimonialViewModel extends ViewModel {

    private TestimonialRepository testimonialRepository;
    private LiveData<List<TestimonialModel_ResponseModel>> testimonialListLiveData;

    public TestimonialViewModel() {
        testimonialRepository = new TestimonialRepository();
        testimonialListLiveData = testimonialRepository.getTestimonials();
    }

    public LiveData<List<TestimonialModel_ResponseModel>> getTestimonialListLiveData() {
        return testimonialListLiveData;
    }
}
