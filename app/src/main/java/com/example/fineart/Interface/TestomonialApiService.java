package com.example.fineart.Interface;

import com.example.fineart.Model.TestimonialModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TestomonialApiService {

        @GET("testimonial_master_backend/get_published_customer_testimonials_list")
        Call<List<TestimonialModel>> getTestimonials();

}
