package com.example.fineart.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fineart.Repository.TestimonialRepository;

import java.util.List;

public class TestimonialViewModel extends ViewModel {

    private TestimonialRepository testimonialRepository;
    private LiveData<List<TestimonialModel>> testimonialListLiveData;

    public TestimonialViewModel() {
        testimonialRepository = new TestimonialRepository();
        testimonialListLiveData = testimonialRepository.getTestimonials();
    }

    public LiveData<List<TestimonialModel>> getTestimonialListLiveData() {
        return testimonialListLiveData;
    }
}
