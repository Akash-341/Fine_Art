package com.ort.fineart.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ort.fineart.Model.Response_Model.Testimonial_ResponseModel;
import com.ort.fineart.Repository.TestimonialRepository;

import java.util.List;

public class TestimonialViewModel extends ViewModel {

    private TestimonialRepository testimonialRepository;
    private LiveData<List<Testimonial_ResponseModel>> testimonialListLiveData;

    public TestimonialViewModel() {
        testimonialRepository = new TestimonialRepository();
        testimonialListLiveData = testimonialRepository.getTestimonials();
    }

    public LiveData<List<Testimonial_ResponseModel>> getTestimonialListLiveData() {
        return testimonialListLiveData;
    }
}
