package com.ort.fineart.Repository;

import androidx.lifecycle.MutableLiveData;

import com.ort.fineart.Api_Handel.ApiService;
import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.Model.Response_Model.TestimonialModel_ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestimonialRepository {

    private ApiService testimonialApi;
    private MutableLiveData<List<TestimonialModel_ResponseModel>> testimonialListLiveData;

    public TestimonialRepository() {
        testimonialApi = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        testimonialListLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<TestimonialModel_ResponseModel>> getTestimonials() {
        Call<List<TestimonialModel_ResponseModel>> call = testimonialApi.getTestimonials();
        call.enqueue(new Callback<List<TestimonialModel_ResponseModel>>() {
            @Override
            public void onResponse(Call<List<TestimonialModel_ResponseModel>> call, Response<List<TestimonialModel_ResponseModel>> response) {
                if (response.isSuccessful()) {
                    testimonialListLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<TestimonialModel_ResponseModel>> call, Throwable t) {
                // Handle failure
                testimonialListLiveData.setValue(null);
            }
        });
        return testimonialListLiveData;
    }
}
