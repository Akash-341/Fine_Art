package com.example.fineart.Repository;

import androidx.lifecycle.MutableLiveData;

import com.example.fineart.Api_Handel.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestimonialRepository {

    private TestomonialApiService testimonialApi;
    private MutableLiveData<List<TestimonialModel>> testimonialListLiveData;

    public TestimonialRepository() {
        testimonialApi = RetrofitClient.getRetrofitInstance().create(TestomonialApiService.class);
        testimonialListLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<TestimonialModel>> getTestimonials() {
        Call<List<TestimonialModel>> call = testimonialApi.getTestimonials();
        call.enqueue(new Callback<List<TestimonialModel>>() {
            @Override
            public void onResponse(Call<List<TestimonialModel>> call, Response<List<TestimonialModel>> response) {
                if (response.isSuccessful()) {
                    testimonialListLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<TestimonialModel>> call, Throwable t) {
                // Handle failure
            }
        });
        return testimonialListLiveData;
    }
}
