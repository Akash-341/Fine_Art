package com.ort.fineart.Repository;

import androidx.lifecycle.MutableLiveData;

import com.ort.fineart.Api_Handel.ApiService;
import com.ort.fineart.Api_Handel.RequestStatus_Interface;
import com.ort.fineart.Api_Handel.RetrofitClient;
import com.ort.fineart.Model.Response_Model.Testimonial_ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestimonialRepository {

    private ApiService testimonialApi;
    private MutableLiveData<List<Testimonial_ResponseModel>> testimonialListLiveData;

    public TestimonialRepository() {

        testimonialListLiveData = new MutableLiveData<>();
    }





    public MutableLiveData<List<Testimonial_ResponseModel>> getTestimonials() {
        Call<List<Testimonial_ResponseModel>> call = RetrofitClient.getInstance().getApiService().getTestimonials();
        call.enqueue(new Callback<List<Testimonial_ResponseModel>>() {
            @Override
            public void onResponse(Call<List<Testimonial_ResponseModel>> call, Response<List<Testimonial_ResponseModel>> response) {
                if (response.isSuccessful()) {
                    testimonialListLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Testimonial_ResponseModel>> call, Throwable t) {
                // Handle failure
                testimonialListLiveData.setValue(null);
            }
        });
        return testimonialListLiveData;
    }
}
